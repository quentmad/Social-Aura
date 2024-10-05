import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { TokenService } from '../token.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email!: string;
  password!: string;
  errorMessage: string = '';

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private router: Router
  ) {}

  login() {
    this.authService.login(this.email, this.password).subscribe(
      (token: string) => {
        this.tokenService.setToken(token);
        // Redirection vers la page des posts après une connexion réussie
        this.router.navigate(['/posts']);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 401) {
          this.errorMessage = 'Identifiants invalides, veuillez réessayer.';
        } else {
          this.errorMessage = 'Une erreur est survenue, veuillez réessayer plus tard.';
        }
        console.error('Erreur de connexion', error);
      }
    );
  }
}
