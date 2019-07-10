import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { DocumentService } from 'src/app/services/document.service';

@Component({
  selector: 'app-my-documents',
  templateUrl: './my-documents.component.html',
  styleUrls: ['./my-documents.component.scss']
})
export class MyDocumentsComponent implements OnInit {

  headElements = ['Document name', 'Document description', 'Size', 'Last modification'];
  docs = [];

  constructor(private documentService: DocumentService, private toastr: ToastrService) { }

  ngOnInit() {
    this.getMyDocuments();
  }

  getMyDocuments() {
    this.documentService.getMyDocuments().subscribe((resp: any) => {
      this.docs = resp;
    }, error => {
      this.toastr.error('Could not get documents');
    });
  }

  download(id) {
    this.documentService.download(id);
  }

  delete(id) {
    this.documentService.delete(id).subscribe((resp: any) => {
      this.toastr.info('Suppression effectuée avec succés');
      this.ngOnInit();
    }, error => {
      this.toastr.error('Erreur de suppression');
    });
  }

}
