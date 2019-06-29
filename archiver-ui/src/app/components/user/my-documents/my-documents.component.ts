import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-documents',
  templateUrl: './my-documents.component.html',
  styleUrls: ['./my-documents.component.scss']
})
export class MyDocumentsComponent implements OnInit {

  elements: any = [
    {
      id: 1, heading1: '1',
      heading4: 'My resume',
      heading5: 'This is my resume',
      heading6: '2MB',
      heading7: 'PDF',
      heading8: '03-04-2019',

    },
    {
      id: 2, heading1: '',
      heading4: 'Spring boot slides',
      heading5: 'Slides for spring boot training',
      heading6: '5.1MB',
      heading7: 'PDF',
      heading8: '11-03-2019',

    },
    {
      id: 3, heading1: '',
      heading4: 'SOA architecture presentation',
      heading5: 'Document containing slides for SOA presentation',
      heading6: '4.0MB',
      heading7: 'pptx',
      heading8: '22-01-2019',

    },
    {
      id: 4, heading1: '',
      heading4: 'Empty document',
      heading5: 'This is an empty document',
      heading6: '0.0MB',
      heading7: 'txt',
      heading8: '03-06-2019',

    },
  ];
  headElements = ['Document name', 'Document description', 'Size', 'Type', 'Upload date'];


  constructor() { }

  ngOnInit() {
  }

}
