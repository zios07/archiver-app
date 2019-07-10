import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { RoleService } from 'src/app/services/role.service';
import { TokenService } from 'src/app/services/token.service';
import { AuthenticationService } from '../../../services/authentication.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent {

  loginPage = true;
  connectedRole = null;
  connectedUser: User = null;
  username: string;
  authenticated = false;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private tokenService: TokenService,
    private roleService: RoleService
  ) {
    this.username = this.tokenService.getToken();
    router.events.subscribe((val) => {
      if (val instanceof NavigationEnd) {
        this.authenticated = this.authService.isAuthenticated();
        this.authService.getConnectedUser().then(user => {
          this.connectedUser = user;
          if (this.authenticated && this.connectedUser) {
            this.connectedRole = this.connectedUser.role.roleCode;
            this.roleService.setConnectedRole(this.connectedRole);
          }
        });
      }
    });
    this.initMenu();
  }

  initMenu() {
    this.router.events.subscribe(val => {
      if (val instanceof NavigationEnd) {
        if (this.router.routerState.snapshot.url === '/signup') {
          this.loginPage = false;
        } else {
          this.loginPage = true;
        }
      }
    })
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
