import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

/**
 * Service pour gérer les requêtes de connexion et d'inscription.
 */
export class AuthService {
  private apiUrl = `http://localhost:8080/api/auth`;

  constructor(private http: HttpClient) { }

  /**
   * Connexion d'un utilisateur.
   * @returns Un Observable contenant le token JWT.
   */
  login(email: string, password: string): Observable<string> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, { email, password }).pipe(
      map(response => response.token), // Extrait le token de la réponse
      catchError((error) => {
        console.error('Erreur lors de la connexion', error);
        throw error; // Lève l'erreur pour être gérée par le composant appelant
      })
    );
  }

  /**
   * Inscription d'un nouvel utilisateur.
   * @returns Un Observable contenant le token JWT.
   */
  register(email: string, password: string, name: string, lastName: string, birthDate: number): Observable<string> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/register`, { email, password, name, lastName, birthDate }).pipe(
      map(response => response.token), // Extrait le token de la réponse
      catchError((error) => {
        console.error('Erreur lors de l\'inscription', error);
        throw error; // Lève l'erreur pour être gérée par le composant appelant
      })
    );
  }
}
