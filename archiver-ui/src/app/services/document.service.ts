import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {
  url = environment.API_URL + '/documents';

  constructor(private http: HttpClient, private router: Router, private tokenService: TokenService) { }

  saveDocument(document, content) {
    const fd = new FormData();
    const blob = new Blob([content], { type: 'application/json' });
    fd.append('document', blob, content.name);
    fd.append('metadata', JSON.stringify(document));
    return this.http.post(this.url, fd);
  }

  updateDocument(document, content) {
    const fd = new FormData();
    const blob = new Blob([content], { type: 'application/json' });
    fd.append('document', blob, content.name);
    fd.append('metadata', JSON.stringify(document));
    return this.http.post(this.url, fd);
  }

  delete(id) {
    return this.http.delete(this.url + '/' + id);
  }

  findById(id) {
    return this.http.get(this.url + '/' + id);
  }

  download(id) {
    window.location.href = this.url + '/download/' + id + '?access_token=' + this.tokenService.getToken();
  }

  getMyDocuments() {
    return this.http.get(this.url + '/my-documents');
  }

  findAll() {
    return this.http.get(this.url);
  }
}
