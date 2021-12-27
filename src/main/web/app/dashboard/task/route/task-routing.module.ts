import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TaskComponent} from "../list/task.component";
import {UserRouteAccessService} from "../../../core/auth/user-route-access.service";

const taskRoute: Routes = [
  {
    path: '',
    component: TaskComponent,
   // canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(taskRoute)],
  exports: [RouterModule],
})
export class TaskRoutingModule {
}
