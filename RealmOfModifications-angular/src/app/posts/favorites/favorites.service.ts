import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostResponse, CommentResponse } from '../posts.service';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {
  private postsFavApiUrl = 'http://localhost:8080/api/posts/user/favorites';
  private commentsApiUrl = 'http://localhost:8080/api/comments/post';
  private favoriteQuantityApiUrl = 'http://localhost:8080/api/favorites/post/nb';

  constructor(private http: HttpClient) {}

  getPostsFavoriteByUser(userId: number): Observable<PostResponse[]> {
    const url = `${this.postsFavApiUrl}/${userId}`;
    return this.http.get<PostResponse[]>(url);
  }

  getCommentsByPost(postId: number): Observable<CommentResponse[]> {
    const url = `${this.commentsApiUrl}/${postId}`;
    return this.http.get<CommentResponse[]>(url);
  }

  getFavoriteCountByPost(postId: number): Observable<number> {
    const url = `${this.favoriteQuantityApiUrl}/${postId}`;
    return this.http.get<number>(url);
  }
}
