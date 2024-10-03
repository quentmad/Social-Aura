package realmofmodifications.aura.spring.RealmOfModifications.repository;

import org.springframework.data.repository.CrudRepository;
import realmofmodifications.aura.spring.RealmOfModifications.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    void deleteById(int id);
    User findByEmail(String email);

}
