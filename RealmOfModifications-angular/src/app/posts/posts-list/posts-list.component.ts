import { Component, OnInit } from '@angular/core';
import { PostsService, PostResponse, CommentResponse } from '../posts.service';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-posts-list',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './posts-list.component.html',
    styleUrls: ['./posts-list.component.css']
})
export class PostsListComponent implements OnInit {
    posts: PostResponse[] = []; // Stocke les posts
    commentsByPostId: { [postId: number]: CommentResponse[] } = {}; // Stocke les commentaires par postId
    favoritesCountByPostId: { [postId: number]: number } = {}; // Stocke le nombre de favoris par postId

    constructor(private postsService: PostsService) {}

    ngOnInit(): void {
        this.loadPosts();
    }

    loadPosts(): void {
        this.postsService.getPosts().subscribe(
            (data) => {
                this.posts = data; // Stocke les posts récupérés
                this.posts.forEach(post => {
                    this.loadCommentsForPost(post.id); // Charge les commentaires pour chaque post
                    this.loadFavoriteCountForPost(post.id); // Charge le nombre de favoris pour chaque post
                });
            },
            (error) => {
                console.error('Erreur lors de la récupération des posts', error);
            }
        );
    }

    loadCommentsForPost(postId: number): void {
        this.postsService.getCommentsByPost(postId).subscribe(
            (comments) => {
                this.commentsByPostId[postId] = comments; // Associe les commentaires au post correspondant
            },
            (error) => {
                console.error('Erreur lors de la récupération des commentaires pour le post ' + postId, error);
            }
        );
    }

    loadFavoriteCountForPost(postId: number): void {
        this.postsService.getFavoriteCountByPost(postId).subscribe(
            (count) => {
                console.log(`Post ID: ${postId}, Favorites Count: ${count}`); // Ajoute un log ici
                this.favoritesCountByPostId[postId] = count; // Stocke le nombre de favoris pour chaque post
            },
            (error) => {
                console.error('Erreur lors de la récupération du nombre de favoris pour le post ' + postId, error);
            }
        );
    }
}
