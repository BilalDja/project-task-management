import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { LoginService } from './login.service';
import {Router} from "@angular/router";
import App from "../../config/app.constants";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  title = App.name;
  submitted = false;
  authenticationError = false;
  loginForm = this.fb.group({
    email: [null, [Validators.required, Validators.email]],
    password: [null, [Validators.required]],
    rememberMe: [false],
  });

  constructor(
    private loginService: LoginService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  get f(): { [key: string]: AbstractControl } {
    return this.loginForm.controls;
  }

  login(): void {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.loginService
      .login({
        login: this.loginForm.get('email')!.value,
        password: this.loginForm.get('password')!.value,
        rememberMe: this.loginForm.get('rememberMe')!.value,
      })
      .subscribe(
        () => {
          this.authenticationError = false;
          this.router.navigate(['dashboard/overview']);
        },
        () => {
          this.authenticationError = true;
          this.onReset();
        }
      );
  }

  onReset(): void {
    this.submitted = false;
    this.loginForm.reset();
  }

}
