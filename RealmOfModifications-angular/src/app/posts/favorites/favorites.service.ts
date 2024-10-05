import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostResponse, CommentResponse } from '../posts.service';
import { TokenService } from '../../auth/token.service';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {
  private postsFavApiUrl = 'http://localhost:8080/api/posts/user/favorites';
  private commentsApiUrl = 'http://localhost:8080/api/comments/post';
  private favoriteQuantityApiUrl = 'http://localhost:8080/api/favorites/post/nb';

  constructor(private http: HttpClient, private tokenService : TokenService) {}

  getPostsFavoriteByUser(userId: number): Observable<PostResponse[]> {
    const url = `${this.postsFavApiUrl}/${userId}`;
    const token = this.tokenService.getToken(); // Récupération du token du stockage local
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Création des en-têtes

    return this.http.get<PostResponse[]>(url, { headers }); // Envoi des en-têtes avec la requête
  }


  getCommentsByPost(postId: number): Observable<CommentResponse[]> {
    const url = `${this.commentsApiUrl}/${postId}`;
    const token = this.tokenService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<CommentResponse[]>(url, { headers });
  }


  getFavoriteCountByPost(postId: number): Observable<number> {
    const url = `${this.favoriteQuantityApiUrl}/${postId}`;
    const token = this.tokenService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<number>(url, { headers });
  }
}
