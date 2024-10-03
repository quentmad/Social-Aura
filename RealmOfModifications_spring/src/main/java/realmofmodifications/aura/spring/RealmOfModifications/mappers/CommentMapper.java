package realmofmodifications.aura.spring.RealmOfModifications.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import realmofmodifications.aura.spring.RealmOfModifications.model.Comment;
import realmofmodifications.aura.spring.RealmOfModifications.requests.CommentAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.CommentGetResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "user.id",target = "userId")
    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "user.name",target = "userName")
    @Mapping(source = "user.lastName",target = "userLastName")
    CommentGetResponse commentToCommentGetResponse(Comment comment);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "postId", target = "post.id")
    Comment commentAddRequestToComment(CommentAddRequest commentAddRequest);

    List<CommentGetResponse> commentsToCommentsGetResponses(List<Comment> comments);
}
