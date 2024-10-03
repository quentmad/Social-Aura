import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create-post',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent {
  isSubmitted = false; // Pour afficher le message de confirmation

  constructor(private http: HttpClient) {} // Injection du service HttpClient

  onSubmit(createPostForm: NgForm) {
    if (createPostForm.valid) {
      const postData = {
        userId: 1,//Par defaut en attendant l'auth
        content: createPostForm.value.content
      };

      // Envoi du post via une requête POST à l'API
      this.http.post('http://localhost:8080/api/posts/add', postData)
        .subscribe({
          next: response => {
            console.log('Post créé avec succès !', response);
            this.isSubmitted = true;
            createPostForm.reset();
          },
          error: err => {
            console.error('Erreur lors de la création du post :', err);
          }
        });
    }
  }
}
