import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component'; // Ton composant principal
import { routes } from './app.routes';

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes) // Configurer le routeur
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
