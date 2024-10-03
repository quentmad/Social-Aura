package realmofmodifications.aura.spring.RealmOfModifications.repository;

import org.springframework.data.repository.CrudRepository;
import realmofmodifications.aura.spring.RealmOfModifications.model.Favorite;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
    List<Favorite> findAllByUser_Id(int userId);
    List<Favorite> findAllByPost_Id(int postId);
    Optional<Favorite> findByUser_IdAndPost_Id(int userId, int postId);

    void deleteById(int id);

}
