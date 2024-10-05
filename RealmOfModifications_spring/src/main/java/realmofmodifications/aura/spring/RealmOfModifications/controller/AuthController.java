package realmofmodifications.aura.spring.RealmOfModifications.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import realmofmodifications.aura.spring.RealmOfModifications.model.User;
import realmofmodifications.aura.spring.RealmOfModifications.repository.UserRepository;
import realmofmodifications.aura.spring.RealmOfModifications.requests.LoginRequest;
import realmofmodifications.aura.spring.RealmOfModifications.requests.RegisterRequest;
import realmofmodifications.aura.spring.RealmOfModifications.security.JwtUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthController est responsable des opérations d'authentification (login et registrement des utilisateurs).
 * Ce contrôleur gère les endpoints pour la connexion et l'inscription des utilisateurs.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

    /**
     * Gère l'authentification des utilisateurs. Il est utilisé pour authentifier les credentials (email et mot de passe).
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Outil pour générer des tokens JWT après une authentification réussie.
     */
    private final JwtUtil jwtUtil;

    /**
     * Outil pour encoder les mots de passe lors de l'inscription afin de les stocker de manière sécurisée.
     */
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    /**
     * Constructeur de la classe AuthController.
     *
     * @param authenticationConfiguration Configuration qui permet d'obtenir le gestionnaire d'authentification.
     * @param jwtUtil Utilitaire pour la gestion des tokens JWT.
     * @param passwordEncoder Permet d'encoder les mots de passe avant de les stocker dans la base de données.
     * @param userRepository Accès aux données des utilisateurs dans la base de données.
     * @throws Exception Si l'obtention de l'AuthenticationManager échoue.
     */
    public AuthController(@Lazy AuthenticationConfiguration authenticationConfiguration, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserRepository userRepository) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Endpoint pour la connexion d'un utilisateur. L'utilisateur fournit un email et un mot de passe.
     * Si les informations sont correctes, un token JWT est généré et retourné.
     *
     * @param loginRequest Objet contenant l'email et le mot de passe de l'utilisateur.
     * @return Un token JWT sous format JSON si la connexion est réussie, ou un statut UNAUTHORIZED si la connexion échoue.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authentifie l'utilisateur
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            User user = userRepository.findByEmail(loginRequest.getEmail());

            if (user == null) {
                return new ResponseEntity<>(Collections.singletonMap("message", "User not found"), HttpStatus.UNAUTHORIZED);
            }

            String token = jwtUtil.generateToken(
                    loginRequest.getEmail(),
                    user.getId(),
                    user.getName() + " " + user.getLastName()
            );

            // Renvoie un objet JSON contenant le token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK); // Retourne un objet JSON au lieu d'une chaîne brute

        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Authentication failed"), HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * Endpoint pour l'inscription d'un nouvel utilisateur. Les informations de l'utilisateur sont stockées
     * dans la base de données après avoir encodé son mot de passe.
     * L'utilisateur est automatiquement authentifié après son inscription.
     *
     * @param registerRequest Objet contenant les informations nécessaires pour créer un nouvel utilisateur.
     * @return Un token JWT sous format JSON si l'inscription est réussie, ou un message d'erreur si l'email existe déjà.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest registerRequest) {

        // Vérifie si un utilisateur avec cet email existe déjà.
        if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Email already exists"), HttpStatus.BAD_REQUEST);
        }

        // Création d'un nouvel utilisateur avec les informations fournies et un mot de passe encodé.
        User user = new User(registerRequest.getEmail(), registerRequest.getName(), registerRequest.getLastName(), passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);

        // Authentification automatique après inscription.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword())
        );

        String token = jwtUtil.generateToken(
                registerRequest.getEmail(),
                userRepository.findByEmail(registerRequest.getEmail()).getId(),
                registerRequest.getName() + " " + registerRequest.getLastName()
        );

        // Renvoie le token dans un objet JSON.
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
