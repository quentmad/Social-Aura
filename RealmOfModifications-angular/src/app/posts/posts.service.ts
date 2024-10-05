import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../auth/token.service';

export interface PostResponse {
  id: number;
  userId : number;
  content: string;
  createdAt: string;
  userName: string;
  userLastName : string;
}

//tempo ici
export interface UserResponse {
  id: number;
  name: string;
  lastName: string;
  birth: number;
  email: string;
}


export interface CommentResponse {
  id: number;
  postId : number;
  userId : number
  content: string;
  createdAt: string;   
  userName : string;
  userLastName : string
}

export interface FavoriteResponse {
  id: number;
  userId: number;
  name : string;
  lastName : string;
}


@Injectable({
    providedIn: 'root'
})
export class PostsService {
    private postsApiUrl = 'http://localhost:8080/api/posts/all';
    private commentsApiUrl = 'http://localhost:8080/api/comments/post';
    private favoriteQuantityApiUrl = 'http://localhost:8080/api/favorites/post/nb';

    constructor(private http: HttpClient, private tokenService: TokenService) {}


    getPosts(): Observable<PostResponse[]> {
      const token = this.tokenService.getToken(); // Récupération du token du stockage local
      console.log('Token récupéré pour getPosts:', token); // Ajoutez cette ligne
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Création des en-têtes

      return this.http.get<PostResponse[]>(this.postsApiUrl, { headers }); // Envoi des en-têtes avec la requête
  }

    getCommentsByPost(postId: number): Observable<CommentResponse[]> {
      const token = this.tokenService.getToken(); 
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      const url = `${this.commentsApiUrl}/${postId}`;

      return this.http.get<CommentResponse[]>(url, { headers });
  }

  getFavoriteCountByPost(postId: number): Observable<number> {
      const token = this.tokenService.getToken();
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      const url = `${this.favoriteQuantityApiUrl}/${postId}`;

      return this.http.get<number>(url, { headers }); 
  }
    

}
