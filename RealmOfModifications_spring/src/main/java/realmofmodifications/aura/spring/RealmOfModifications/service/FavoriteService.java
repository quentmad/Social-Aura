package realmofmodifications.aura.spring.RealmOfModifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realmofmodifications.aura.spring.RealmOfModifications.mappers.FavoriteMapper;
import realmofmodifications.aura.spring.RealmOfModifications.repository.FavoriteRepository;
import realmofmodifications.aura.spring.RealmOfModifications.requests.FavoriteAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.FavoriteGetResponse;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteMapper favoriteMapper;

    public List<FavoriteGetResponse> getAllByPost(int postId) {
        return favoriteMapper.favoritesToFavoritesGetResponse(favoriteRepository.findAllByPost_Id(postId));
    }

    public List<FavoriteGetResponse> getAllByUser(int userId) {
        return favoriteMapper.favoritesToFavoritesGetResponse(favoriteRepository.findAllByUser_Id(userId));
    }

    public boolean isInFavorite(int userId, int postId) {
        return favoriteRepository.findByUser_IdAndPost_Id(userId, postId).isPresent();
    }

    public void add(FavoriteAddRequest favoriteAddRequest) {
        if (!isInFavorite(favoriteAddRequest.getUserId(), favoriteAddRequest.getPostId())) {
            favoriteRepository.save(favoriteMapper.favoriteAddRequestToFavorite(favoriteAddRequest));
        }
    }

    public void delete(int favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }


}

