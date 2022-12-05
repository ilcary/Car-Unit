import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Comune } from '../models/Comune';

@Injectable({
  providedIn: 'root'
})
export class ComuneService {

  constructor(private http:HttpClient) { }

  getAllComuni():Observable<Comune[]>{
    return this.http.get<Comune[]>(apiUrl + '/comuni');
  }

  getComuneById(id:number):Observable<Comune>{
    return this.http.get<Comune>(apiUrl + '/comuni/' + id);
  }

}
