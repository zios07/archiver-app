import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { DocumentService } from 'src/app/services/document.service';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.scss']
})
export class RequestsComponent implements OnInit {



  headElements = ['Document name', 'Document description', 'Size', 'Last modification', 'Status'];
  docs = [];

  constructor(private documentService: DocumentService, private toastr: ToastrService) { }

  ngOnInit() {
    this.getMyDocuments();
  }

  getMyDocuments() {
    this.documentService.getNonArchivedDocuments().subscribe((resp: any) => {
      this.docs = resp;
    }, error => {
      this.toastr.error('Could not get documents');
    });
  }

  acceptRequest(doc) {
    doc.request.status = 'Archived';
    this.documentService.updateDocument(doc).subscribe(resp => {
      this.toastr.success('Document mis a jour');
    }, error => {
      this.toastr.error('Erreur inconnue');
    });
  }

  declineRequest(doc) {
    doc.request.status = 'Declined';
    this.documentService.updateDocument(doc).subscribe(resp => {
      this.toastr.success('Document mis a jour');
    }, error => {
      this.toastr.error('Erreur inconnue');
    });
  }
}
