package realmofmodifications.aura.spring.RealmOfModifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realmofmodifications.aura.spring.RealmOfModifications.mappers.UserMapper;
import realmofmodifications.aura.spring.RealmOfModifications.model.Favorite;
import realmofmodifications.aura.spring.RealmOfModifications.model.User;
import realmofmodifications.aura.spring.RealmOfModifications.repository.UserRepository;
import realmofmodifications.aura.spring.RealmOfModifications.requests.UserAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.responses.UserGetResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Chargé de fournir les données métier (ici les données propre aux users)
 * @Service: permet de dire à spring que cette classe est un service
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    UserMapper userMapper;


    public List<UserGetResponse> getAll() {
        return userMapper.usersToUsersGetResponses((List<User>) repository.findAll());
    }

    public UserGetResponse getResponseById(int id) {
        return userMapper.userToUserGetResponse(repository.findById(id).orElse(null));
    }

    public UserGetResponse getByEmail(String email) {
        return userMapper.userToUserGetResponse(repository.findByEmail(email));
    }

    public User getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void add(UserAddRequest userAddRequest) {
        repository.save(userMapper.userAddRequestToUser(userAddRequest));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}
