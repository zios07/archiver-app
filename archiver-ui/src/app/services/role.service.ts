import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  url = environment.API_URL;

  connectedRole;

  constructor(private http: HttpClient) { }

  loadRoles() {
    return this.http.get(this.url + '/api/v1/roles');
  }

  setConnectedRole(role) {
    this.connectedRole = role;
  }

  getConnectedRole() {
    return this.connectedRole;
  }

}
