package realmofmodifications.aura.spring.RealmOfModifications.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import realmofmodifications.aura.spring.RealmOfModifications.requests.UserAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.UserGetResponse;
import realmofmodifications.aura.spring.RealmOfModifications.service.UserService;

import java.util.List;

/**
 * (api rest): façade qui reçoit toutes les requêtes http (interface entre l'application et le serveur)
 * @RestController: permet de dire à spring que cette classe est un controller
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UsersController {


    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserGetResponse>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> getById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getResponseById(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UserAddRequest userAddRequest) {
        userService.add(userAddRequest);
        return new ResponseEntity<>("User Added", HttpStatus.CREATED);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}


