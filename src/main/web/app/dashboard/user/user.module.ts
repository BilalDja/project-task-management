import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserComponent} from './list/user.component';
import {UserRoutingModule} from "./route/user-routing.module";
import {DataTablesModule} from "angular-datatables";
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
  declarations: [
    UserComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    DataTablesModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class UserModule {
}
