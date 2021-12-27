import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DataTablesModule} from "angular-datatables";



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    DataTablesModule
  ],
  exports: [
    DataTablesModule
  ]
})
export class SharedModule { }
