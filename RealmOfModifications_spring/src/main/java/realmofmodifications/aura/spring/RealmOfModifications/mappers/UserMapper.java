package realmofmodifications.aura.spring.RealmOfModifications.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import realmofmodifications.aura.spring.RealmOfModifications.model.User;
import realmofmodifications.aura.spring.RealmOfModifications.requests.UserAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.UserGetResponse;

import java.util.List;

/**
 * Interface responsable de la conversion des objets entre les entités
 * de domaine (User) et les objets de réponse (UserGetResponse) et de requête (UserAddRequest).
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserGetResponse userToUserGetResponse(User user);

    User userAddRequestToUser(UserAddRequest userAddRequest);

    List<UserGetResponse> usersToUsersGetResponses(List<User> users);


}
