import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Address } from '../models/Address';
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

  getAddressById(id:number):Observable<Address>{
    return this.http.get<Address>(apiUrl+"/addresses/"+id);
  }

}
