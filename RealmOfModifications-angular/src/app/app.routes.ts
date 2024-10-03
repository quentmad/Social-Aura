import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { CreatePostComponent } from './posts/create-post/create-post.component';
import { FavoritesComponent } from './posts/favorites/favorites.component';
import { PostDetailComponent } from './posts/post-detail/post-detail.component';
import { PostsListComponent } from './posts/posts-list/posts-list.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

  export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'posts', component: PostsListComponent },
    { path: 'posts/:id', component: PostDetailComponent },
    { path: 'create-post', component: CreatePostComponent },
    { path: 'favorites', component: FavoritesComponent },
    { path: 'user-profile', component: UserProfileComponent },
    { path: '', redirectTo: '/posts', pathMatch: 'full' } // Redirection vers la page d'accueil (liste des posts)
  
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}