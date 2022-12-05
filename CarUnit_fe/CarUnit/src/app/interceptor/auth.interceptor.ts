import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private auth: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(this.auth.isUserLogged() ){

      let token = this.auth.getAccessToken();
      if(token){
        let requestClone = request.clone({
          headers: request.headers.append('Authorization', 'Bearer ' + token)
        });
        return next.handle(requestClone);
      }
    }
    return next.handle(request);
  }
}
