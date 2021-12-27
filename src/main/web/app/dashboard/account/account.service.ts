import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {IAccount} from "./account.model";
import {Observable} from "rxjs";

export type EntityResponseType = HttpResponse<IAccount>;

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  protected resourceUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {
  }

  update(account: IAccount): Observable<EntityResponseType> {
    return this.http.post<IAccount>(this.resourceUrl + "/me", account, {observe: 'response'});
  }

  updatePassword(oldPassword: string, newPassword: string): Observable<EntityResponseType> {
    return this.http.post<IAccount>(this.resourceUrl + "/change-password", {
      oldPassword: oldPassword,
      newPassword: newPassword
    }, {observe: 'response'});
  }
}
