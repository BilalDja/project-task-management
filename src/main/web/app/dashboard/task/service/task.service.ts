import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {getTaskIdentifier, ITask} from "../task.model";
import {Observable} from "rxjs";

export type EntityResponseType = HttpResponse<ITask>;

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  protected resourceUrl = 'http://localhost:8080/api/tasks';

  constructor(private http: HttpClient) {
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITask>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  create(task: ITask): Observable<EntityResponseType> {
    return this.http.post<ITask>(this.resourceUrl, task, {observe: 'response'});
  }

  update(task: ITask): Observable<EntityResponseType> {
    return this.http.put<ITask>(`${this.resourceUrl}/${getTaskIdentifier(task) as number}`, task, {observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

}
