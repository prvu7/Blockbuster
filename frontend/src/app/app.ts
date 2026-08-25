import { Component, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '@auth0/auth0-angular';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.html',
})
export class App {
  auth = inject(AuthService);
  private http = inject(HttpClient);
  pingResult = '';
  
  login() {
    this.auth.loginWithRedirect();
  }

  logout() {
    this.auth.logout({ logoutParams: { returnaTo: window.location.origin }});
  }

  testSecurePing() {
    this.http.get(`${environment.apiBaseUrl}/secure-ping`, { responseType: 'text' })
      .subscribe({
        next: (res) => this.pingResult = res,
        error: (err) => this.pingResult = `Error: ${err.status}`
      });
  }
}
