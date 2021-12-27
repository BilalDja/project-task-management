import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from '@angular/forms';
import {RegisterService} from './register.service';
import {Router} from "@angular/router";
import App from "../../config/app.constants";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  title = App.name;
  submitted = false;
  authenticationError = false;
  registerForm = this.fb.group({
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    username: [null, [Validators.required]],
    email: [null, [Validators.required, Validators.email]],
    password: [null, [Validators.required]],
  });

  constructor(
    private registerService: RegisterService,
    private fb: FormBuilder,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  get f(): { [key: string]: AbstractControl } {
    return this.registerForm.controls;
  }

  register(): void {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }

    this.registerService
      .register({
        firstName: this.registerForm.get('firstName')!.value,
        lastName: this.registerForm.get('lastName')!.value,
        username: this.registerForm.get('username')!.value,
        email: this.registerForm.get('email')!.value,
        password: this.registerForm.get('password')!.value
      })
      .subscribe(
        () => {
          alert("successfully register, you account will be activate by the admin, one it will be activated you can access");
          this.onReset();
        },
        () => {
          alert("something went wrong please try later")
          this.authenticationError = true;
          this.onReset();
        }
      );
  }

  onReset(): void {
    this.submitted = false;
    this.registerForm.reset();
  }

}
