import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/common/home/home.component';
import { LoginComponent } from './components/common/login/login.component';
import { RegisterComponent } from './components/common/register/register.component';
import { DocumentListComponent } from './components/supervisor/document-list/document-list.component';
import { RequestsComponent } from './components/supervisor/requests/requests.component';
import { DocumentComponent } from './components/user/document/document.component';
import { MyDocumentsComponent } from './components/user/my-documents/my-documents.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'signup', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'document/create', component: DocumentComponent },
  { path: 'document/list', component: DocumentListComponent },
  { path: 'my-documents', component: MyDocumentsComponent },
  { path: 'requests', component: RequestsComponent },
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
