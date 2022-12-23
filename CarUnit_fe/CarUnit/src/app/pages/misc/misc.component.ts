import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { GasPrices } from 'src/app/models/gasPrice';
import { DealCarService } from 'src/app/service/deal-car.service';

export type res = {
  results:GasPrices[],
  success: boolean
}

@Component({
  selector: 'app-misc',
  templateUrl: './misc.component.html',
  styleUrls: ['./misc.component.scss']
})
export class MiscComponent implements OnInit {

  gasPrices!:GasPrices[];

  constructor(private gasPriceServ:DealCarService) { }

  ngOnInit(): void {
    this.gasPriceServ.getGasPrices()
    .subscribe({
      next: (response) => {
        console.log(response);
        this.gasPrices = response.results;
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      }
    })
  }

}
