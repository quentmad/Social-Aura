package realmofmodifications.aura.spring.RealmOfModifications.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import realmofmodifications.aura.spring.RealmOfModifications.model.Post;
import realmofmodifications.aura.spring.RealmOfModifications.requests.PostAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.PostGetResponse;

import java.util.List;

/**
 * Interface responsable de la conversion des objets entre les entités
 * de domaine (Post) et les objets de réponse (PostGetResponse) et de requête (PostAddRequest).
 *
 * Les mappings sont utilisés pour transformer les objets du modèle en objets de
 * réponse, avec des correspondances explicites entre certains champs.
 */
@Mapper(componentModel = "spring")
public interface PostMapper {

    /**
     * Méthode pour mapper un objet {@link Post} vers un objet {@link PostGetResponse}.
     *
     * @param post l'objet Post à mapper.
     * @return l'objet PostGetResponse correspondant.
     *
     * <p>Les mappings spécifiques :</p>
     * <ul>
     *   <li>Le champ "user.id" du Post est mappé au champ "userId" du PostGetResponse.</li>
     *   <li>Le champ "user.lastName" du Post est mappé au champ "userLastName" du PostGetResponse.</li>
     *   <li>Le champ "user.name" du Post est mappé au champ "userName" du PostGetResponse.</li>
     * </ul>
     */
    @Mapping(source = "user.id",target = "userId")
    @Mapping(source = "user.lastName",target = "userLastName")
    @Mapping(source = "user.name",target = "userName")
    PostGetResponse postToPostGetResponse(Post post);


    /**
     * Méthode pour mapper un objet {@link PostAddRequest} vers un objet {@link Post}.
     *
     * @param postAddRequest l'objet PostAddRequest à mapper.
     * @return l'objet Post correspondant.
     *
     * <p>Les mappings spécifiques :</p>
     * <ul>
     *   <li>Le champ "userId" du PostAddRequest est mappé au champ "user.id" du Post.</li>
     * </ul>
     */
    @Mapping(source = "userId",target = "user.id")
    Post postAddRequestToPost(PostAddRequest postAddRequest);

    /**
     * Méthode pour mapper une liste d'objets {@link Post} vers une liste
     * d'objets {@link PostGetResponse}.
     *
     * @param posts la liste d'objets Post à mapper.
     * @return la liste d'objets PostGetResponse correspondant.
     *
     * <p>Utilise le mapping défini dans {@link #postToPostGetResponse(Post)}
     * pour chaque élément de la liste.</p>
     */
    List<PostGetResponse> postsToPostsGetResponses(List<Post> posts);



}
