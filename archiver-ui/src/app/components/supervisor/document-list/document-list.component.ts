import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { DocumentService } from 'src/app/services/document.service';

@Component({
  selector: 'app-document-list',
  templateUrl: './document-list.component.html',
  styleUrls: ['./document-list.component.scss']
})
export class DocumentListComponent implements OnInit {


  headElements = ['Document name', 'Document description', 'Size', 'Last modification', 'Status'];
  docs = [];

  constructor(private documentService: DocumentService, private toastr: ToastrService) { }

  ngOnInit() {
    this.getMyDocuments();
  }

  getMyDocuments() {
    this.documentService.findAll().subscribe((resp: any) => {
      this.docs = resp;
    }, error => {
      this.toastr.error('Could not get documents');
    });
  }

  download(id) {
    this.documentService.download(id);
  }
}
