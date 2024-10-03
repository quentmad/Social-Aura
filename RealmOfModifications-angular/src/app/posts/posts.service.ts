import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';

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

    constructor(private http: HttpClient) {}

    getPosts(): Observable<PostResponse[]> {
        return this.http.get<PostResponse[]>(this.postsApiUrl);
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
