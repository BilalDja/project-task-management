import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(
    private localStorageService: LocalStorageService,
    private sessionStorageService: SessionStorageService,
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if (request.url && request.url.startsWith('http://localhost:8080/')) {
      const token = this.localStorageService.retrieve("authenticationToken") ?? this.sessionStorageService.retrieve("authenticationToken");
      if (token) {
        request = request.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`
          }
        })
      }
    }
    return next.handle(request);
  }
}
