import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TaskComponent} from './list/task.component';
import {TaskRoutingModule} from "./route/task-routing.module";
import {DataTablesModule} from "angular-datatables";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../../shared/shared.module";
import {NgSelectModule} from "@ng-select/ng-select";

@NgModule({
  declarations: [
    TaskComponent
  ],
  imports: [
    CommonModule,
    TaskRoutingModule,
    DataTablesModule,
    ReactiveFormsModule,
    SharedModule,
    NgSelectModule,
    FormsModule
  ]
})
export class TaskModule {
}
