import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DashboardRoutingModule} from './dashboard-routing.module';

import {DashboardComponent} from './view/dashboard.component';
import {OverviewComponent} from "./overview/overview.component";
import {AccountComponent} from "./account/account.component";
import {ReactiveFormsModule} from "@angular/forms";
import { TaskImportantItemComponent } from './overview/task-important-item/task-important-item.component';
import { TaskItemComponent } from './overview/task-item/task-item.component';

@NgModule({
  declarations: [DashboardComponent, OverviewComponent, AccountComponent, TaskImportantItemComponent, TaskItemComponent],
  imports: [
    CommonModule,
    RouterModule,
    DashboardRoutingModule,
    ReactiveFormsModule,
  ]
})
export class DashboardModule {
}
