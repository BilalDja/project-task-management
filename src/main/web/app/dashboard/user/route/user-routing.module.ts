import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from "../list/user.component";
import {UserRouteAccessService} from "../../../core/auth/user-route-access.service";

const userRoute: Routes = [
  {
    path: '',
    component: UserComponent,
   // canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(userRoute)],
  exports: [RouterModule],
})
export class UserRoutingModule {
}
