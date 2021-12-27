import { Injectable, isDevMode } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { AccountService } from './account.service';
import { StateStorageService } from './state-storage.service';

@Injectable({ providedIn: 'root' })
export class UserRouteAccessService implements CanActivate {
  constructor(private router: Router, private accountService: AccountService, private stateStorageService: StateStorageService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.accountService.identity().pipe(
      map(account => {
        if (account) {
          const roles= route.data['authorities'];

          if (roles && this.accountService.hasRole(roles)) {
            return true;
          }

          this.router.navigate(['dashboard/account']);
          return false;
        }
        this.stateStorageService.storeUrl(state.url);
        this.router.navigate(['login']);
        return false;
      })
    );
  }
}
