package realmofmodifications.aura.spring.RealmOfModifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import realmofmodifications.aura.spring.RealmOfModifications.requests.CommentAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.CommentGetResponse;
import realmofmodifications.aura.spring.RealmOfModifications.service.CommentService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/all")
    public ResponseEntity<List<CommentGetResponse>> getAll() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentGetResponse>> getAllByPost(@PathVariable int postId) {
        return new ResponseEntity<>(commentService.getAllByPost(postId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentGetResponse>> getAllByUser(@PathVariable int userId) {
        return new ResponseEntity<>(commentService.getAllByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody CommentAddRequest commentAddRequest) {
        commentService.add(commentAddRequest);
        return new ResponseEntity<>("Comment Added", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

