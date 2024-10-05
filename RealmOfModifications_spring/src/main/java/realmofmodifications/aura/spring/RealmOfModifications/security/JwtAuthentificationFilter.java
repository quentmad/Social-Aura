package realmofmodifications.aura.spring.RealmOfModifications.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Le filtre JwtAuthentificationFilter intercepte chaque requête HTTP pour vérifier si elle contient un token JWT valide.
 * Il s'exécute une fois par requête, d'où l'héritage de la classe OncePerRequestFilter.
 * Son rôle est de valider les tokens JWT et de configurer le contexte de sécurité si l'utilisateur est authentifié.
 */
@Component
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    /**
     * Utilitaire pour les opérations liées aux JWT (génération, validation, extraction d'informations).
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Service qui permet de charger les détails d'un utilisateur (UserDetails) à partir de son nom d'utilisateur.
     */
    @Autowired
    private CustomUserDetailsService service;

    /**
     * Cette méthode est appelée à chaque requête HTTP. Elle intercepte les requêtes pour vérifier l'en-tête d'autorisation,
     * extraire le token JWT, et, si valide, configurer le contexte de sécurité.
     *
     * @param httpServletRequest La requête HTTP reçue.
     * @param httpServletResponse La réponse HTTP à envoyer.
     * @param filterChain La chaîne de filtres qui gère les autres filtres et la suite de la requête.
     * @throws ServletException Si une erreur survient au niveau de la gestion du filtre.
     * @throws IOException Si une erreur d'entrée/sortie survient.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String token = null;
        String userName = null;

        // Vérification que l'en-tête commence par "Bearer " (format attendu pour les tokens JWT).
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extraction du token JWT (en enlevant le préfixe "Bearer ").
            token = authorizationHeader.substring(7);
            // Extraction du nom d'utilisateur à partir du token JWT.
            userName = jwtUtil.extractUsername(token);
        }

        // Si un nom d'utilisateur est extrait et que le contexte de sécurité n'a pas encore d'authentification en cours.
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = service.loadUserByUsername(userName);

            if (jwtUtil.validateToken(token, userDetails)) {

                // Création d'un objet UsernamePasswordAuthenticationToken pour configurer le contexte de sécurité.
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
