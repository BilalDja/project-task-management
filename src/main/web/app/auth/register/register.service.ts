import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Register} from "./register.model";
import {HttpClient} from "@angular/common/http";
import {EntityResponseType} from "../../dashboard/task/service/task.service";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  protected resourceUrl = 'http://localhost:8080/api/auth/register';

  constructor(private http: HttpClient) {
  }

  register(register: Register): Observable<EntityResponseType> {
    return this.http.post<any>(this.resourceUrl, register, {observe: 'response'});
  }

}
