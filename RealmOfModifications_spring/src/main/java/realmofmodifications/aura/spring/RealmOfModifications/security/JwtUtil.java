package realmofmodifications.aura.spring.RealmOfModifications.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import realmofmodifications.aura.spring.RealmOfModifications.responses.UserJwtGetResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * La classe JwtUtil fournit des méthodes utilitaires pour la manipulation des tokens JWT (JSON Web Tokens).
 * Elle permet d'extraire des informations d'un token, de le générer et de le valider.
 */
@Service
public class JwtUtil {

    /**
     * La clé secrète utilisée pour signer et vérifier les tokens JWT.
     * Elle est injectée depuis les propriétés de configuration de l'application.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Extrait le nom d'utilisateur (sujet) d'un token JWT.
     *
     * @param token Le token JWT à partir duquel extraire le nom d'utilisateur.
     * @return Le nom d'utilisateur extrait du token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait la date d'expiration d'un token JWT.
     *
     * @param token Le token JWT à partir duquel extraire la date d'expiration.
     * @return La date d'expiration du token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrait une réclamation spécifique d'un token JWT à l'aide d'un résolveur de réclamations.
     *
     * @param token           Le token JWT à partir duquel extraire la réclamation.
     * @param claimsResolver  La fonction qui permet d'extraire la réclamation souhaitée.
     * @param <T>            Le type de la réclamation à extraire.
     * @return La réclamation extraite du token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrait toutes les réclamations d'un token JWT.
     *
     * @param token Le token JWT à partir duquel extraire toutes les réclamations.
     * @return Les réclamations extraites sous forme d'objet Claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret) // Définissez la clé de signature
                .build()
                .parseClaimsJws(token) // Analysez le token
                .getBody(); // Obtenez le corps des réclamations
    }

    /**
     * Vérifie si un token JWT a expiré.
     *
     * @param token Le token JWT à vérifier.
     * @return True si le token est expiré, false sinon.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Génère un token JWT pour un utilisateur spécifique.
     *
     * @param username Le nom d'utilisateur pour lequel le token est généré.
     * @param userId   L'identifiant de l'utilisateur.
     * @param fullName Le nom complet de l'utilisateur.
     * @return Le token JWT généré.
     */
    public String generateToken(String username, int userId, String fullName) {
        Map<String, Object> claims = new HashMap<>();
        UserJwtGetResponse userJwtResponse = new UserJwtGetResponse();
        userJwtResponse.setId(userId);
        userJwtResponse.setEmail(username);
        userJwtResponse.setFullName(fullName);
        claims.put("user", userJwtResponse);
        return createToken(claims, username);
    }

    /**
     * Crée un token JWT à partir des réclamations et du sujet.
     *
     * @param claims  Les réclamations à inclure dans le token.
     * @param subject Le sujet (nom d'utilisateur) du token.
     * @return Le token JWT créé.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims) // Ajout des réclamations
                .setSubject(subject) // Ajout du sujet
                .setIssuedAt(new Date(System.currentTimeMillis())) // Date d'émission du token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Date d'expiration (10 heures)
                .signWith(SignatureAlgorithm.HS256, secret) // Signature du token avec l'algorithme HS256 et la clé secrète
                .compact(); // Création du token
    }

    /**
     * Valide un token JWT en vérifiant son nom d'utilisateur et sa date d'expiration.
     *
     * @param token      Le token JWT à valider.
     * @param userDetails Les détails de l'utilisateur pour la validation.
     * @return True si le token est valide, false sinon.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extraction du nom d'utilisateur du token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); // Validation du token
    }
}
