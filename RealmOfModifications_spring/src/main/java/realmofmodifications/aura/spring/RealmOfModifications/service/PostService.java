package realmofmodifications.aura.spring.RealmOfModifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realmofmodifications.aura.spring.RealmOfModifications.mappers.PostMapper;
import realmofmodifications.aura.spring.RealmOfModifications.model.Favorite;
import realmofmodifications.aura.spring.RealmOfModifications.model.Post;
import realmofmodifications.aura.spring.RealmOfModifications.model.User;
import realmofmodifications.aura.spring.RealmOfModifications.repository.PostRepository;
import realmofmodifications.aura.spring.RealmOfModifications.requests.PostAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.PostGetResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostMapper postMapper;


    public List<PostGetResponse> getAll() {
        return postMapper.postsToPostsGetResponses((List<Post>) postRepository.findAll());
    }

    public PostGetResponse getResponseById(int id) {
        return postMapper.postToPostGetResponse(postRepository.findById(id).orElse(null));
    }

    public Post getById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<PostGetResponse> getAllByUser(int userId) {
        List<Post> posts = postRepository.findByUserIdOrderByCreatedAtAsc(userId);
        return postMapper.postsToPostsGetResponses(posts);
    }

    public int add(PostAddRequest postAddRequest) {
        Post post = postRepository.save(postMapper.postAddRequestToPost(postAddRequest));
        postRepository.save(post);
        return post.getId();
    }

    public void delete(int postId) {
        postRepository.deleteById(postId);
    }

    public List<PostGetResponse> getAllFavoritesByUser(int userId) {
        User user = userService.getById(userId);
        List<Post> posts = new ArrayList<>();
        for (Favorite favorite : user.getFavorites()) {
            posts.add(favorite.getPost());
        }
        return postMapper.postsToPostsGetResponses(posts);
    }
}

