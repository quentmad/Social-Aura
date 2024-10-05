import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { TokenService } from './token.service';

/**
 * @class AuthGuard
 * @classdesc Garde d'authentification pour protéger les routes.
 * @implements {CanActivate}
 * @description Vérifie si un jeton d'authentification est présent. Si oui, permet l'accès à la route, sinon redirige vers la page de connexion.
 */
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private tokenService: TokenService, private router: Router) { }

  /**
   * @method canActivate
   * @description Méthode pour vérifier si l'utilisateur peut activer la route.
   * @returns {boolean} - Retourne vrai si le jeton est présent, sinon redirige vers la page de connexion et retourne faux.
   */
  canActivate(): boolean {
    if (this.tokenService.getToken()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
