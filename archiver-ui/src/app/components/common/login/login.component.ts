import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subject } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { TokenService } from 'src/app/services/token.service';
import { UserService } from 'src/app/services/user.service';
import { delay } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  title = 'User login';
  form: FormGroup;
  submitted = false;
  authenticated = false;
  connectedRole;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private authService: AuthenticationService,
    private tokenService: TokenService,
    private toastr: ToastrService) {
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])]
    });
  }

  signup() {
    this.router.navigate(['/signup']);
  }

  onSubmit() {
    this.submitted = true;
    this.authService.login(this.form.value).pipe(delay(1000)).subscribe((resp: any) => {
      this.tokenService.saveToken(resp);
      this.userService.findByUsername(this.form.value.username).subscribe(resp => {
        this.authService.setConnectedUser(resp);
        if (this.returnUrl) {
          this.router.navigate([this.returnUrl]);
        } else {
          this.router.navigate(['home']);
        }
      });
    }, resp => {
      this.submitted = false;
      if (resp.status === 401 || resp.status === 403) {
        resp.error ? this.toastr.error(resp.error) : this.toastr.error('Incorrect credentials');
      } else {
        this.toastr.error('Login error. Try again later');
      }
    });
  }
}
