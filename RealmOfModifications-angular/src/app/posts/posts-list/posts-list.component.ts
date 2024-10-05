import { Component, OnInit } from '@angular/core';
import { CommentResponse, PostResponse, PostsService } from '../posts.service';
import { CommonModule } from '@angular/common';

/**
 * Composant Angular pour afficher une liste de posts.
 * 
 * Ce composant récupère les posts, les commentaires associés et le nombre de favoris pour chaque post
 * en utilisant le service `PostsService`. Les données sont stockées dans les propriétés `posts`, 
 * `commentsByPostId` et `favoritesCountByPostId`.
 * 
 * @class
 * @implements {OnInit}
 * 
 * @property {PostResponse[]} posts - Stocke les posts récupérés.
 * @property {{ [postId: number]: CommentResponse[] }} commentsByPostId - Stocke les commentaires par ID de post.
 * @property {{ [postId: number]: number }} favoritesCountByPostId - Stocke le nombre de favoris par ID de post.
 * 
 * @constructor
 * @param {PostsService} postsService - Service utilisé pour récupérer les posts, les commentaires et les favoris.
 * 
 * @method
 * @name ngOnInit
 * @description Méthode du cycle de vie Angular appelée lors de l'initialisation du composant. Elle charge les posts.
 * 
 * @method
 * @name loadPosts
 * @description Charge les posts en utilisant le service `postsService` et appelle les méthodes pour charger les commentaires et les favoris pour chaque post.
 * 
 * @method
 * @name loadCommentsForPost
 * @description Charge les commentaires pour un post donné en utilisant le service `postsService`.
 * @param {number} postId - ID du post pour lequel charger les commentaires.
 * 
 * @method
 * @name loadFavoriteCountForPost
 * @description Charge le nombre de favoris pour un post donné en utilisant le service `postsService`.
 * @param {number} postId - ID du post pour lequel charger le nombre de favoris.
 */
@Component({
    selector: 'app-posts-list',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './posts-list.component.html',
    styleUrls: ['./posts-list.component.css']
})

export class PostsListComponent implements OnInit {
    posts: PostResponse[] = []; // Stocke les posts
    commentsByPostId: { [postId: number]: CommentResponse[] } = {};
    favoritesCountByPostId: { [postId: number]: number } = {};

    constructor(private postsService: PostsService) {}

    ngOnInit(): void {
        this.loadPosts();
    }

    loadPosts(): void {
        this.postsService.getPosts().subscribe(
            (data) => {
                this.posts = data;
                this.posts.forEach(post => {
                    this.loadCommentsForPost(post.id);
                    this.loadFavoriteCountForPost(post.id);
                });
            },
            (error) => {
                console.error('Erreur lors de la récupération des posts', error);
            }
        );
    }

    /**
     * Charge les commentaires pour un post donné en utilisant le service `postsService`.
     * 
     * @param postId ID du post pour lequel charger les commentaires.
     */
    loadCommentsForPost(postId: number): void {
        this.postsService.getCommentsByPost(postId).subscribe(
            (comments) => {
                this.commentsByPostId[postId] = comments;
            },
            (error) => {
                console.error('Erreur lors de la récupération des commentaires pour le post ' + postId, error);
            }
        );
    }

    /**
     * Charge le nombre de favoris pour un post donné en utilisant le service `postsService`.
     * 
     * @param postId ID du post pour lequel charger le nombre de favoris.
     */
    loadFavoriteCountForPost(postId: number): void {
        this.postsService.getFavoriteCountByPost(postId).subscribe(
            (count) => {
                console.log(`Post ID: ${postId}, Favorites Count: ${count}`);
                this.favoritesCountByPostId[postId] = count;
            },
            (error) => {
                console.error('Erreur lors de la récupération du nombre de favoris pour le post ' + postId, error);
            }
        );
    }
}
