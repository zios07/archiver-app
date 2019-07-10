import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user.service';
import { DocumentService } from 'src/app/services/document.service';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.scss']
})
export class DocumentComponent implements OnInit {

  form: FormGroup;
  submitted = false;
  document;
  content;
  mode = 'ADD';
  id;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private documentService: DocumentService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    // check if we're in edit mode
    this.loadDocumentToEdit();
    this.form = this.formBuilder.group({
      name: [null, Validators.required],
      designation: [null, Validators.required],
      description: [null, Validators.required]
    });
  }

  selectFile(event) {
    this.content = event.target.files[0];
  }

  onSubmit() {
    this.submitted = true;
    this.document = this.form.value;
    let observable;
    if (this.mode === 'ADD') {
      observable = this.documentService.saveDocument(this.document, this.content);
    } else if (this.mode === 'EDIT') {
      this.document.id = this.id;
      observable = this.documentService.updateDocument(this.document);
    }
    observable.subscribe(resp => {
      this.toastr.success('Document enregistré avec succès');
      this.submitted = false;
    }, error => {
      this.submitted = false;
      const message = error.error ? error.error.message ? error.error.message : 'Erreur inconnue' : 'Erreur inconnue';
      this.toastr.error(message);
    });
  }

  removeDocument(id) {
    this.documentService.delete(id).subscribe(resp => {
      this.toastr.info('Succès de suppression');
      this.ngOnInit();
    }, error => {
      this.toastr.error('Erreur inconnue');
    })
  }


  loadDocumentToEdit() {
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.mode = 'EDIT';
      this.documentService.findById(this.id).subscribe((result: any) => {
        this.fillForm(result);
      }, error => {
        this.toastr.error(String(error));
      });
    }
  }

  fillForm(document) {
    if (document) {
      this.form.get('name').setValue(document.name);
      this.form.get('designation').setValue(document.designation);
      this.form.get('description').setValue(document.description);
    }
  }
}
