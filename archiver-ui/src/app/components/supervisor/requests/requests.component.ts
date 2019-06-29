import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.scss']
})
export class RequestsComponent implements OnInit {


  elements: any = [
    {
      id: 1, heading1: '1',
      heading2: 'User 1',
      heading4: 'My resume',
      heading5: '20-01-2019',
    },
    {
      id: 2, heading1: '',
      heading2: 'User 2',
      heading4: 'Spring boot slides',
      heading5: '03-03-2019',
    },
    {
      id: 3, heading1: '',
      heading2: 'User 3',
      heading4: 'SOA architecture presentation',
      heading5: '01-04-2019',
    },
    {
      id: 4, heading1: '',
      heading2: 'User 4',
      heading4: 'Empty document',
      heading5: '10-12-2018',
    },
  ];
  headElements = ['Username', 'Document description', 'Size', 'Upload date'];

  constructor() { }

  ngOnInit() {
  }

}
