package realmofmodifications.aura.spring.RealmOfModifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realmofmodifications.aura.spring.RealmOfModifications.mappers.CommentMapper;
import realmofmodifications.aura.spring.RealmOfModifications.model.Comment;
import realmofmodifications.aura.spring.RealmOfModifications.repository.CommentRepository;
import realmofmodifications.aura.spring.RealmOfModifications.requests.CommentAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.CommentGetResponse;

import java.util.List;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;

    public List<CommentGetResponse> getAll() {
        return commentMapper.commentsToCommentsGetResponses((List<Comment>) commentRepository.findAll());
    }

    public CommentGetResponse getResponseById(int id) {
        return commentMapper.commentToCommentGetResponse(commentRepository.findById(id).orElse(null));
    }

    public Comment getById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<CommentGetResponse> getAllByUser(int userId) {
        List<Comment> comments = commentRepository.findAllByUser_Id(userId);
        return commentMapper.commentsToCommentsGetResponses(comments);
    }

    public List<CommentGetResponse> getAllByPost(int postId) {
        List<Comment> comments = commentRepository.findAllByPost_Id(postId);
        return commentMapper.commentsToCommentsGetResponses(comments);
    }

    public void add(CommentAddRequest commentAddRequest) {
        commentRepository.save(commentMapper.commentAddRequestToComment(commentAddRequest));
    }

    public void delete(int commentId) {
        commentRepository.deleteById(commentId);
    }



}


