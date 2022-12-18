import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Dealership } from '../models/Dealership';
import { IDealCar } from '../models/iDealCar';

@Injectable({
  providedIn: 'root'
})
export class DealCarService {

  constructor(private http: HttpClient) { }

  getAllDealCar(dealership_id : number): Observable<IDealCar[]> {
    return this.http.get<IDealCar[]>(apiUrl + '/DealCar/getAllDealCar/'+dealership_id);
  }

  getById(id: number): Observable<IDealCar> {
    return this.http.get<IDealCar>(apiUrl + '/DealCar/' + id);
  }


  saveDealCar(dealCar: FormData,id: number): Observable<IDealCar> {
    console.log(dealCar)
    return this.http.post<IDealCar>(apiUrl + '/DealCar/addNewDealCar/'+id, dealCar);
  }


  updateDealCar(dealCar: IDealCar): Observable<IDealCar> {
    return this.http.put<IDealCar>(apiUrl + '/DealCar/' + dealCar.id, dealCar);
  }

  //DEALERSHIP CRUD

  getDealershipByCeoId(id: number): Observable<Dealership> {
    return this.http.get<Dealership>(apiUrl + '/dealerships/byCeoId/' + id);
  }

  getDealershipByEmployeesId(id:number): Observable<Dealership> {
    return this.http.get<Dealership>(apiUrl + '/dealerships/DealershipByEmployeesId/'+id);
  }

  addUserToDealership(user_id: number, deal_id: number): Observable<Dealership> {
    return this.http.get<Dealership>(apiUrl + '/dealerships/userToDealership/' + user_id + '/' + deal_id);
  }




}
