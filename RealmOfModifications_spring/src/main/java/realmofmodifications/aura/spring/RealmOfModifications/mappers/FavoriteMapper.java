package realmofmodifications.aura.spring.RealmOfModifications.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import realmofmodifications.aura.spring.RealmOfModifications.model.Favorite;
import realmofmodifications.aura.spring.RealmOfModifications.requests.FavoriteAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.FavoriteGetResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name",target = "name")
    @Mapping(source = "user.lastName",target = "lastName")
    FavoriteGetResponse favoriteToFavoriteGetResponse(Favorite favorite);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "postId", target = "post.id")
    Favorite favoriteAddRequestToFavorite(FavoriteAddRequest favoriteAddRequest);

    List<FavoriteGetResponse> favoritesToFavoritesGetResponse(List<Favorite> favorites);


}
