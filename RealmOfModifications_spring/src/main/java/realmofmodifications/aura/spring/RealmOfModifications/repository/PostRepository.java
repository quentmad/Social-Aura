package realmofmodifications.aura.spring.RealmOfModifications.repository;

import org.springframework.data.repository.CrudRepository;
import realmofmodifications.aura.spring.RealmOfModifications.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    List<Post> findByUserIdOrderByCreatedAtAsc(int userId);
    void deleteById(int id);
}
