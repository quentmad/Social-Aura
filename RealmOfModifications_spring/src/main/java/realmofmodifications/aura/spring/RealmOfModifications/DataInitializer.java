package realmofmodifications.aura.spring.RealmOfModifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import realmofmodifications.aura.spring.RealmOfModifications.controller.CommentController;
import realmofmodifications.aura.spring.RealmOfModifications.controller.FavoriteController;
import realmofmodifications.aura.spring.RealmOfModifications.controller.PostController;
import realmofmodifications.aura.spring.RealmOfModifications.controller.UsersController;
import realmofmodifications.aura.spring.RealmOfModifications.requests.CommentAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.requests.FavoriteAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.requests.PostAddRequest;
import realmofmodifications.aura.spring.RealmOfModifications.requests.UserAddRequest;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsersController usersController;
    @Autowired
    private PostController postController;
    @Autowired
    private CommentController commentController;
    @Autowired
    private FavoriteController favoriteController;

    @Override
    public void run(String... args) throws Exception {
        // Créer 5 utilisateurs
        usersController.add(new UserAddRequest("Alice", "Dupont", 280291, "alice.dupont@example.com", "password1"));
        usersController.add(new UserAddRequest("Bob", "Martin", 300602, "bob.martin@example.com", "password2"));
        usersController.add(new UserAddRequest("Claire", "Bernard", 240389, "claire.bernard@example.com", "password3"));
        usersController.add(new UserAddRequest("David", "Leroy", 120995, "david.leroy@example.com", "password4"));
        usersController.add(new UserAddRequest("Eva", "Richard", 290799, "eva.richard@example.com", "password5"));

        // Créer 10 posts
        postController.add(new PostAddRequest(1, "Lancement de notre nouveau projet !"));
        postController.add(new PostAddRequest(1, "Retour sur notre dernier événement !"));
        postController.add(new PostAddRequest(2, "Comment améliorer la productivité au travail"));
        postController.add(new PostAddRequest(3, "Top 10 des outils de développement web"));
        postController.add(new PostAddRequest(3, "Les meilleures pratiques de codage en 2024"));
        postController.add(new PostAddRequest(4, "Pourquoi l'apprentissage automatique est essentiel"));
        postController.add(new PostAddRequest(4, "Créer une API RESTful avec Spring Boot"));
        postController.add(new PostAddRequest(5, "Les tendances technologiques de cette année"));
        postController.add(new PostAddRequest(5, "Comment gérer le stress au travail"));
        postController.add(new PostAddRequest(1, "Développer des applications mobiles en 2024"));

        // Ajouter des commentaires
        commentController.add(new CommentAddRequest(3, 1, "Super article, j'ai hâte de voir le projet !"));
        commentController.add(new CommentAddRequest(2, 2, "Bravo pour l'événement, c'était très bien organisé !"));
        commentController.add(new CommentAddRequest(3, 3, "Des conseils très utiles, merci Alice !"));
        commentController.add(new CommentAddRequest(1, 4, "Excellente liste d'outils, je vais les essayer."));
        commentController.add(new CommentAddRequest(4, 6, "L'apprentissage automatique change vraiment la donne."));
        commentController.add(new CommentAddRequest(5, 8, "Je suis impatient de voir ces tendances en action."));
        commentController.add(new CommentAddRequest(1, 7, "J'aimerais en savoir plus sur la création d'API !"));
        commentController.add(new CommentAddRequest(2, 9, "Gérer le stress est crucial, merci pour cet article."));
        commentController.add(new CommentAddRequest(3, 8, "Développer des applications mobiles est l'avenir !"));
        commentController.add(new CommentAddRequest(1, 3, "Merci pour les conseils sur la productivité !"));

        // Ajouter des favoris
        favoriteController.add(new FavoriteAddRequest(1, 3));
        favoriteController.add(new FavoriteAddRequest(1, 2));
        favoriteController.add(new FavoriteAddRequest(2, 2));
        favoriteController.add(new FavoriteAddRequest(3, 4));
        favoriteController.add(new FavoriteAddRequest(4, 5));
        favoriteController.add(new FavoriteAddRequest(5, 1));
        favoriteController.add(new FavoriteAddRequest(6, 2));
        favoriteController.add(new FavoriteAddRequest(6, 3));
        favoriteController.add(new FavoriteAddRequest(6, 4));
        favoriteController.add(new FavoriteAddRequest(8, 5));
        favoriteController.add(new FavoriteAddRequest(8, 1));
    }
}
