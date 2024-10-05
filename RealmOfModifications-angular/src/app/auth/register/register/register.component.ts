import { Component } from '@angular/core';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';
import { TokenService } from '../../token.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  email!: string;
  password!: string;
  name!: string;
  lastName!: string;
  birthDate!: number;
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, private tokenService: TokenService) {}

  register() {
    //console.log(this.email + ' ' + this.password + ' ' + this.name + ' ' + this.lastName + ' ' + this.birthDate);
    //Si l'email est vide ou du mauvais format ou de moins de 3 caractères
    if (!this.email || !this.email.includes('@') || this.email.length < 3) {
      this.errorMessage = 'Veuillez saisir un email valide.';
      return;
    }
    //Si le mot de passe est vide ou de moins de 6 caractères
    if (!this.password || this.password.length < 6) {
      this.errorMessage = 'Veuillez saisir un mot de passe valide (6 caractères minimum).';
      return;
    }
    //Si le nom est vide ou de moins de 3 caractères
    if (!this.name || this.name.length < 3) {
      this.errorMessage = 'Veuillez saisir un nom valide (3 caractères minimum).';
      return;
    }
    //Si le prénom est vide ou de moins de 3 caractères
    if (!this.lastName || this.lastName.length < 3) {
      this.errorMessage = 'Veuillez saisir un prénom valide (3 caractères minimum).';
      return;
    }
    //Si la date de naissance est vide ou n'est pas un nombre a 8 chiffres
    if (!this.birthDate || this.birthDate.toString().length !== 8) {
      this.errorMessage = 'Veuillez saisir une date de naissance valide (8 chiffres).';
      return;
    }
    this.authService.register(this.email, this.password, this.name, this.lastName, this.birthDate).subscribe(
      (token: string) => {
        //console.log('Inscription réussie, token reçu :', token);
        // Enregistre le token dans le service de gestion du token local
        this.tokenService.setToken(token);
        //console.log('Token enregistré dans le service de gestion du token', this.tokenService.getToken());
        // Redirection vers la page des posts après une inscription réussie
        this.router.navigate(['/posts']);
      },
      (error) => {
        console.error('Erreur d\'inscription', error);
      }
    );
  }
}
