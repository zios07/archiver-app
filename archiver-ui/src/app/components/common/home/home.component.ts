import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RoleService } from 'src/app/services/role.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  connectedRole;
  constructor(private auth: AuthenticationService, private roleService: RoleService) { }

  ngOnInit() {
    this.connectedRole = this.roleService.getConnectedRole();
  }

}
