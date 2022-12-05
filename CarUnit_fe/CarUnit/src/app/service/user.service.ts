import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { ISignup } from '../models/isignup';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(apiUrl + '/users');
  }

  getById(id:number):Observable<User>{
    return this.http.get<User>(apiUrl + '/users/' + id);
  }

  saveUser(user:ISignup):Observable<ISignup>{
    console.log(user)
    return this.http.post<ISignup>(apiUrl + '/users', user);
  }

  updateUser(user:User):Observable<User>{
    return this.http.put<User>(apiUrl + '/users/'+user.id, user);
  }


}
