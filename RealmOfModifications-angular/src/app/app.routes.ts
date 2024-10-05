import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { CreatePostComponent } from './posts/create-post/create-post.component';
import { FavoritesComponent } from './posts/favorites/favorites.component';
import { PostDetailComponent } from './posts/post-detail/post-detail.component';
import { PostsListComponent } from './posts/posts-list/posts-list.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AuthGuard } from './auth/auth.guard';
import { RegisterComponent } from './auth/register/register/register.component';

  export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    {path: 'register', component: RegisterComponent},
    { path: 'posts', component: PostsListComponent, canActivate: [AuthGuard] },
    { path: 'posts/:id', component: PostDetailComponent, canActivate: [AuthGuard] },
    { path: 'create-post', component: CreatePostComponent, canActivate: [AuthGuard] },
    { path: 'favorites', component: FavoritesComponent, canActivate: [AuthGuard] },
    { path: 'user-profile', component: UserProfileComponent, canActivate: [AuthGuard] },
    { path: '', redirectTo: '/register', pathMatch: 'full' } // Redirection vers la page d'accueil (liste des posts)
  
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}