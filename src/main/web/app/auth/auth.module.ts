import {NgModule} from "@angular/core";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthRoutingModule} from "./auth-routing.module";
import {ResetComponent} from "./reset/reset.component";

@NgModule({
  declarations: [LoginComponent, RegisterComponent, ResetComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AuthRoutingModule
  ],
  exports: []
})
export class AuthModule {
}
