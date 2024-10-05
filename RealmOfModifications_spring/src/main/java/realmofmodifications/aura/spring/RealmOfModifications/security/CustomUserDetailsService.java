package realmofmodifications.aura.spring.RealmOfModifications.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import realmofmodifications.aura.spring.RealmOfModifications.model.User;
import realmofmodifications.aura.spring.RealmOfModifications.repository.UserRepository;

import java.util.ArrayList;

/**
 * Permet de charger un utilisateur par son email, utilisé pour l'authentification.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}

