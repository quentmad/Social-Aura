package realmofmodifications.aura.spring.RealmOfModifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import realmofmodifications.aura.spring.RealmOfModifications.requests.PostAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.PostGetResponse;
import realmofmodifications.aura.spring.RealmOfModifications.service.PostService;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/all")
    public ResponseEntity<List<PostGetResponse>> getAll() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostGetResponse> getById(@PathVariable int id) {
        return new ResponseEntity<>(postService.getResponseById(id),HttpStatus.OK);
    }

    //Recupère les posts favoris d'un utilisateur
    @GetMapping("/user/favorites/{userId}")
    public ResponseEntity<List<PostGetResponse>> getAllFavoritesByUser(@PathVariable int userId) {
        return new ResponseEntity<>(postService.getAllFavoritesByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostGetResponse>> getAllByUser(@PathVariable int userId) {
        return new ResponseEntity<>(postService.getAllByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> add(@RequestBody PostAddRequest postAddRequest) {
        int postId = postService.add(postAddRequest);
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
