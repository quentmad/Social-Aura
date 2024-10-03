package realmofmodifications.aura.spring.RealmOfModifications.repository;

import org.springframework.data.repository.CrudRepository;
import realmofmodifications.aura.spring.RealmOfModifications.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findAllByPost_Id(int postId);
    List<Comment> findAllByUser_Id(int userId);
    void deleteById(int id);

}
