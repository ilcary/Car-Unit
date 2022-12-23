import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { ICarAdv } from '../models/icaradv';
import { ISearch } from '../models/iricerca';
import { IStateAdv } from '../models/stateAdv';

@Injectable({
  providedIn: 'root'
})
export class CarAdvService {

  constructor(private http:HttpClient) { }

  getModelsOfMake(make:string ):Observable<string[]>{
    return this.http.get<string[]>(apiUrl + '/CarModels/'+make);
  }

  getAdvBySearch(search:ISearch ):Observable<ICarAdv[]>{
    return this.http.post<ICarAdv[]>(apiUrl + '/CarAdv/search',search);
  }

  // note adv crude

  getNoteAdvById(id:string):Observable<IStateAdv>{
    return this.http.get<IStateAdv>(apiUrl + '/stateAdv/getStateByid' , {params: {"id": id}});
  }

  saveNoteAdv(noteAdv:IStateAdv):Observable<IStateAdv>{
    console.log(noteAdv)
    return this.http.post<IStateAdv>(apiUrl + '/stateAdv', noteAdv);
  }

  updateNoteAdv(noteAdv:IStateAdv):Observable<IStateAdv>{
    console.log(noteAdv.id.toString());
    console.log(noteAdv);

    return this.http.put<IStateAdv>(apiUrl + '/stateAdv/update',noteAdv );
  }

  deleteNoteAdvById(id:string):Observable<IStateAdv>{
    return this.http.delete<IStateAdv>(apiUrl + '/stateAdv' , {params: {"id": id}});
  }

  // crude starred search

  saveSearchAdv(username:string, search:ISearch):Observable<ISearch>{
    return this.http.post<ISearch>(apiUrl + '/starredSearches/'+username , search);
  }

  deleteSearch(id:number):Observable<ISearch>{
    return this.http.delete<ISearch>(apiUrl + '/starredSearches/'+id);
  }

  getAllSearchAdv():Observable<ISearch[]>{
    return this.http.get<ISearch[]>(apiUrl + '/starredSearches');
  }

  getSearchAdvById(id:number):Observable<ISearch>{
    return this.http.get<ISearch>(apiUrl + '/starredSearches/' + id);
  }

  getSearchesAdvByUserId(id:string):Observable<ISearch[]>{
    return this.http.get<ISearch[]>(apiUrl + "/starredSearches/getAllByUserId/"+id);
  }

  deleteSearchAdvById(id:string):Observable<ISearch>{
    return this.http.delete<ISearch>(apiUrl + '/starredSearches/' + id);
  }



}
