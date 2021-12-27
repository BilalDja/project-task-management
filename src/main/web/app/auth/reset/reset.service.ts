import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Reset} from "./reset.model";
import {HttpClient} from "@angular/common/http";
import {EntityResponseType} from "../../dashboard/task/service/task.service";

@Injectable({
  providedIn: 'root'
})
export class ResetService {

  protected resourceUrlResetInit = 'http://localhost:8080/api/auth/reset-password/init';
  protected resourceUrlResetFinish = 'http://localhost:8080/api/auth/reset-password/finish';

  constructor(private http: HttpClient) {
  }

  resetInit(email: String): Observable<EntityResponseType> {
    return this.http.post<any>(this.resourceUrlResetInit, { email: email}, {observe: 'response'});
  }

  resetFinish(reset: Reset): Observable<EntityResponseType> {
    return this.http.post<any>(this.resourceUrlResetFinish, reset, {observe: 'response'});
  }

}
