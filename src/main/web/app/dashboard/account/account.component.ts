import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";
import {AccountService} from "./account.service";
import {AccountService as AuthService} from "../../core/auth/account.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-overview',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  isSubmitted = false;
  isFormPasswordSubmitted = false;

  imageSrc: string = "";
  formFile = this.fb.group({
    file: [null, [Validators.required]],
    fileSource: [null, [Validators.required]]
  });

  form = this.fb.group({
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    email: [null, [Validators.required]],
    username: [null, [Validators.required]],
    photoName: [null],
  });
  formErrors = {
    firstName: [],
    lastName: [],
    email: [],
    username: [],
  };

  formPassword = this.fb.group({
    oldPassword: [null, [Validators.required]],
    newPassword: [null, [Validators.required]],
  });
  formPasswordErrors = {
    oldPassword: [],
    newPassword: [],
  };

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private accountService: AccountService,
    private authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.getAuthenticationState().subscribe(account => {
      this.form.setValue({
        firstName: account?.firstName,
        lastName: account?.lastName,
        email: account?.email,
        username: account?.username,
        photoName: account?.photoName
      })
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  get fp(): { [key: string]: AbstractControl } {
    return this.formPassword.controls;
  }

  get ff(): { [key: string]: AbstractControl } {
    return this.formFile.controls;
  }

  onFileChange(event: any) {
    const reader = new FileReader();
    if (event.target && event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      // @ts-ignore
      const f = (event.target as HTMLInputElement).files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.imageSrc = reader.result as string;
        this.formFile.patchValue({
          fileSource: f//reader.result

        });
        this.submitFile();
      };
    }
  }

  update(): void {
    this.formErrors = {
      firstName: [],
      lastName: [],
      email: [],
      username: [],
    };
    this.isSubmitted = true;

    if (this.form.invalid) {
      return;
    }

    this.accountService.update({
      firstName: this.form.get('firstName')!.value,
      lastName: this.form.get('lastName')!.value,
      email: this.form.get('email')!.value,
      username: this.form.get('username')!.value,
      photoName: this.form.get('photoName')!.value
    }).subscribe(
      () => {
        alert("info updated successfully")
      },
      (res) => {
        this.formErrors = res.error;
      }
    );
  }

  updatePassword(): void {
    this.formPasswordErrors = {
      oldPassword: [],
      newPassword: []
    };
    this.isFormPasswordSubmitted = true;
    if (this.formPassword.invalid) {
      return;
    }
    this.accountService.updatePassword(this.formPassword.get('oldPassword')!.value, this.formPassword.get('newPassword')!.value).subscribe(
      () => {
        alert("password updated successfully")
        this.formPassword.reset();
        this.isFormPasswordSubmitted = false;
      },
      (res) => {
        this.formErrors = res.error;
      }
    );
  }


  submitFile() {
    if (this.formFile.invalid) {
      return;
    }
    const formData = new FormData();
    formData.append('file', this.formFile.get('fileSource')?.value);
    this.http.post('http://localhost:8080/api/files', formData)
      .subscribe(data => {
          this.form.patchValue({photoName: Object.values(data)[0]})
          this.update();
        },
        error => {
          console.log(error);
        }
      );

  }
}
