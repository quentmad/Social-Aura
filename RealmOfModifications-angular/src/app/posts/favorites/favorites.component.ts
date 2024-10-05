import { Component, OnInit } from '@angular/core';
import {PostResponse, CommentResponse } from '../posts.service';
import { FavoritesService } from './favorites.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-favorites',
  standalone: true,
  imports: [CommonModule],//NG FOR ET IF
  templateUrl: './favorites.component.html',
  styleUrl: './favorites.component.css'
})
export class FavoritesComponent {
  postsFav: PostResponse[] = []; 
  commentsByPostId: { [postId: number]: CommentResponse[] } = {}; 
  favoritesCountByPostId: { [postId: number]: number } = {}; 

  constructor(private favoritesService: FavoritesService) {}

  ngOnInit(): void {
      this.loadPostsFavoriteByUser();
  }

  loadPostsFavoriteByUser(): void {
      this.favoritesService.getPostsFavoriteByUser(1).subscribe(//Default A CHANGER USER 1
          (data) => {
              this.postsFav = data; // Stocke les posts récupérés
              this.postsFav.forEach(post => {
                  this.loadCommentsForPost(post.id);
                  this.loadFavoriteCountForPost(post.id); 
              });
          },
          (error) => {
              console.error('Erreur lors de la récupération des posts', error);
          }
      );
  }

  loadCommentsForPost(postId: number): void {
      this.favoritesService.getCommentsByPost(postId).subscribe(
          (comments) => {
              this.commentsByPostId[postId] = comments; // Associe les commentaires au post correspondant
          },
          (error) => {
              console.error('Erreur lors de la récupération des commentaires pour le post ' + postId, error);
          }
      );
  }

  loadFavoriteCountForPost(postId: number): void {
      this.favoritesService.getFavoriteCountByPost(postId).subscribe(
          (count) => {
              //console.log(`Post ID: ${postId}, Favorites Count: ${count}`);
              this.favoritesCountByPostId[postId] = count;
          },
          (error) => {
              console.error('Erreur lors de la récupération du nombre de favoris pour le post ' + postId, error);
          }
      );
  }
}
