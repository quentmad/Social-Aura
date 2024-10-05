import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

/**
 * @class NavbarComponent
 * @classdesc Composant de la barre de navigation.
 * 
 * @description
 * Ce composant représente la barre de navigation de l'application. Il utilise le module de routage d'Angular pour naviguer entre les différentes routes.
 * 
 * @example
 * <app-navbar></app-navbar>
 * 
 * @selector app-navbar
 * @standalone true
 * @imports [RouterModule]
 * @templateUrl ./navbar.component.html
 * @styleUrl ./navbar.component.css
 */
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private router: Router) {}

  /**
   * Navigue vers une route spécifiée.
   * 
   * @method
   * @name navigateTo
   * @param {string} route - La route vers laquelle naviguer.
   * @returns {void}
   */
  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
