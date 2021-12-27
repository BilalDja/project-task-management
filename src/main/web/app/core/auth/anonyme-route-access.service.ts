import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {AccountService} from './account.service';
import {StateStorageService} from './state-storage.service';

@Injectable({providedIn: 'root'})
export class AnonymeRouteAccessService implements CanActivate {
  constructor(private router: Router, private accountService: AccountService, private stateStorageService: StateStorageService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.accountService.identity().pipe(
      map(account => {
        if (this.accountService.isAuthenticated()) {
          this.router.navigate(['dashboard/overview']);
          return false;
        }
        return true;
      }));
  }
}
