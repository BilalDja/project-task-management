import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {IOverview} from "./overview.model";

export type EntityResponseType = HttpResponse<IOverview>;

@Injectable({
  providedIn: 'root'
})
export class OverviewService {

  protected resourceUrl = 'http://localhost:8080/api/taskStatistic';

  constructor(private http: HttpClient) {
  }

  all(): Observable<EntityResponseType> {
    return this.http.get<IOverview>(this.resourceUrl, {observe: 'response'});
  }

}
