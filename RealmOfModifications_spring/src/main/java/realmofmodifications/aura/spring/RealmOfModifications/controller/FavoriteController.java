package realmofmodifications.aura.spring.RealmOfModifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import realmofmodifications.aura.spring.RealmOfModifications.requests.FavoriteAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.FavoriteGetResponse;
import realmofmodifications.aura.spring.RealmOfModifications.service.FavoriteService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<FavoriteGetResponse>> getAllByPost(@PathVariable int postId) {
        return new ResponseEntity<>(favoriteService.getAllByPost(postId), HttpStatus.OK);
    }

    @GetMapping("/post/nb/{postId}")
    public ResponseEntity<Integer> getQuantityByPost(@PathVariable int postId) {
        return new ResponseEntity<>(favoriteService.getAllByPost(postId).size(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<FavoriteGetResponse>> getAllByUser(@PathVariable int userId) {
        return new ResponseEntity<>(favoriteService.getAllByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteGetResponse>> getByUser(@PathVariable int userId) {
        return new ResponseEntity<>(favoriteService.getAllByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/isInFavorite")
    public ResponseEntity<Boolean> isInFavorite(@RequestParam int userId, @RequestParam int postId) {
        return new ResponseEntity<>(favoriteService.isInFavorite(userId, postId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody FavoriteAddRequest favoriteAddRequest) {
        favoriteService.add(favoriteAddRequest);
        return new ResponseEntity<>("Favorite Added", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        favoriteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
