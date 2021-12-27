import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {getUserIdentifier, IUser, IUserShort} from "../user.model";
import {Observable} from "rxjs";

export type EntityResponseType = HttpResponse<IUser>;

class List<T> {
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  protected resourceUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {
  }

  all(): Observable<HttpResponse<IUserShort[]>> {
    return this.http.get<IUserShort[]>(this.resourceUrl + 'Short', {observe: 'response'});
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUser>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  create(user: IUser): Observable<EntityResponseType> {
    return this.http.post<IUser>(this.resourceUrl, user, {observe: 'response'});
  }

  update(user: IUser): Observable<EntityResponseType> {
    return this.http.put<IUser>(`${this.resourceUrl}/${getUserIdentifier(user) as number}`, user, {observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

}
