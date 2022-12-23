import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { ISignup } from '../models/isignup';
import { ITask } from '../models/iTask';
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

  makeItCeo(id:number):Observable<User>{
    return this.http.get<User>(apiUrl + '/users/makeItCeo/ '+ id);
  }

  saveUser(user:ISignup):Observable<ISignup>{
    console.log(user)
    return this.http.post<ISignup>(apiUrl + '/users', user);
  }

  getAllTaskByUserId(user_id:number):Observable<Task[]>{
    return this.http.get<Task[]>(apiUrl + '/tasks/findByUserId/'+user_id);
  }

  addTaskToUser(task:ITask,user_id:number):Observable<Task>{
    return this.http.post<Task>(apiUrl + '/tasks/addTaskToUser/'+user_id, task);
  }

  updateStatusTask(task_id:string,status:boolean):Observable<Task>{
    return this.http.put<Task>(apiUrl+ '/tasks/updateState/'+task_id, status)
  }

  updateUser(user:User):Observable<User>{
    return this.http.put<User>(apiUrl + '/users/'+user.id, user);
  }


}
