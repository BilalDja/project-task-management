import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './view/dashboard.component';
import {OverviewComponent} from "./overview/overview.component";
import {AccountComponent} from "./account/account.component";
import {Authority} from "../config/authority.constants";
import {UserRouteAccessService} from "../core/auth/user-route-access.service";


const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    children: [
      {
        path: "overview",
        data: {
          authorities: [Authority.ADMIN],
        },
        canActivate: [UserRouteAccessService],
        component: OverviewComponent
      },
      {
        path: 'task',
        data: {
          authorities: [Authority.ADMIN, Authority.USER],
        },
        canActivate: [UserRouteAccessService],
        loadChildren: () => import('./task/task.module').then(m => m.TaskModule),
      },
      {
        path: 'user',
        data: {
          authorities: [Authority.ADMIN],
        },
        canActivate: [UserRouteAccessService],
        loadChildren: () => import('./user/user.module').then(m => m.UserModule),
      },
      {
        path: 'account',
        data: {
          authorities: [Authority.ADMIN, Authority.USER],
        },
        canActivate: [UserRouteAccessService],
        component: AccountComponent
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {
}
