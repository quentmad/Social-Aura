import { Injectable } from '@angular/core';

/**
 * @class TokenService
 * @description Service pour gérer les opérations liées aux tokens d'authentification.
 */
@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private tokenKey = 'auth_tokenYU89DNZI';

  setToken(token: string): void {
    sessionStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    return sessionStorage.getItem(this.tokenKey);
  }

  removeToken(): void {
    sessionStorage.removeItem(this.tokenKey);
  }
}
