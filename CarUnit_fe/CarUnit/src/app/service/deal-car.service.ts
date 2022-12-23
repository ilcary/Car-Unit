import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Dealership } from '../models/Dealership';
import { GasPrices } from '../models/gasPrice';
import { IDealCar } from '../models/iDealCar';
import { IDealership } from '../models/IDealership';
import { User } from '../models/User';
import { res } from '../pages/misc/misc.component';

@Injectable({
  providedIn: 'root'
})
export class DealCarService {

  constructor(private http: HttpClient) {
    const headers = new HttpHeaders().set('Authorization', 'apikey 7DjgGYUMSULDG9aBBZecVu:1JYGlVZWaXrL9FiBB0BmQQ').set('Content-Type', 'application/json');
  }

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

  getAllEmployeesByDealershipId(id:number): Observable<User[]> {
    return this.http.get<User[]>(apiUrl + '/dealerships/Employees/'+id);
  }

  addUserToDealership(user_id: number, deal_id: number): Observable<Dealership> {
    return this.http.get<Dealership>(apiUrl + '/dealerships/userToDealership/' + user_id + '/' + deal_id);
  }

  updateDealership(deal_id:number, dealership:IDealership){
    return this.http.put<Dealership>(apiUrl + '/dealerships/' + deal_id, dealership)
  }

  dismissEmployee(user_id: number, deal_id: number): Observable<Dealership> {
    return this.http.delete<Dealership>(apiUrl + '/dealerships/dismissEmployee/' + deal_id + '/' + user_id);
  }


  getGasPrices(): Observable<res>{
    const options = {
      headers: new HttpHeaders({
        'Authorization': 'apikey 7DjgGYUMSULDG9aBBZecVu:1JYGlVZWaXrL9FiBB0BmQQ',
        'Content-Type': 'application/json'
      })
    }
    return this.http.get<res>('https://api.collectapi.com/gasPrice/europeanCountries',options)
  }




}
