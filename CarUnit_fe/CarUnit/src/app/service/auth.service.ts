import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrl } from 'src/environments/environment';
import { AuthResponse } from '../models/auth-response';
import { ILogin } from '../models/ilogin';
import { IRegister } from '../models/iregister';
import { User } from '../models/User';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }



  register(registerData:IRegister){
    return this.http.post<AuthResponse>(apiUrl+'/register', registerData)
  }

  login(loginData:ILogin){
    return this.http.post<AuthResponse>(apiUrl+'/login', loginData)
  }

  isUserLogged():boolean{
    return localStorage.getItem('access') != null
  }

  getLoggedUser():User{
    let db = localStorage.getItem('access')
    return db ? JSON.parse(db) : null
  }
  getAccessToken():string{
    let db = localStorage.getItem('access')
    console.log(db);

    return db ? JSON.parse(db).accessToken : null
  }

  saveAccessData(data:AuthResponse):void{
    localStorage.setItem('access',JSON.stringify(data))
  }

  logOut(): void{
    localStorage.removeItem('access')
  }

}
