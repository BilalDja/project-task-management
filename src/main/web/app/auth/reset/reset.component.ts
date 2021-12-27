import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from '@angular/forms';
import {ResetService} from './reset.service';
import {Router} from "@angular/router";
import App from "../../config/app.constants";
import {timer} from "rxjs";

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.scss']
})
export class ResetComponent implements OnInit {

  title = App.name;
  submitted = false;
  isInit = true;
  authenticationError = false;
  counter = 120;
  resetInitForm = this.fb.group({
    email: [null, [Validators.required, Validators.email]],
  });

  resetFinishForm = this.fb.group({
    code: [null, [Validators.required]],
    password: [null, [Validators.required]]
  });

  constructor(
    private resetService: ResetService,
    private fb: FormBuilder,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    const that = this;
    setInterval(function(){
      if(that.counter == 0)
      {
        that.router.navigate(['login']);
      }
      that.counter -=1;
    }, 1000);
  }

  get fi(): { [key: string]: AbstractControl } {
    return this.resetInitForm.controls;
  }

  get ff(): { [key: string]: AbstractControl } {
    return this.resetFinishForm.controls;
  }

  reset(): void {

    if (this.isInit) {

      this.submitted = true;
      if (this.resetInitForm.invalid) {
        return;
      }

      this.resetService
        .resetInit(this.resetInitForm.get('email')!.value)
        .subscribe(
          () => {
            alert("an email have been send, use that code to reinitialise you password")
            this.authenticationError = false;
            this.submitted = false;
            this.isInit = false;
          },
          () => {
            this.authenticationError = true;
            this.submitted = false;
            this.resetInitForm.reset();
          }
        );

    } else {

      this.submitted = true;
      if (this.resetFinishForm.invalid) {
        return;
      }

      this.resetService
        .resetFinish({
          key: this.resetFinishForm.get('code')!.value,
          password: this.resetFinishForm.get('password')!.value,
        })
        .subscribe(
          () => {
            alert("You password have been successfully updated")
            this.authenticationError = false;
            this.router.navigate(['login']);
          },
          () => {
            this.authenticationError = true;
            this.submitted = false;
            this.resetFinishForm.reset();
          }
        );

    }

  }

  sendAgain() {
    this.authenticationError = true;
    this.submitted = false;
    this.isInit = true;
    this.resetInitForm.reset();
    this.resetFinishForm.reset();
  }
}
