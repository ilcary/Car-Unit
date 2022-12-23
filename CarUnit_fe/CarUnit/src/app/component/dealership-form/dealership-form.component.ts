import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from 'src/app/models/Address';
import { Comune } from 'src/app/models/Comune';
import { Dealership } from 'src/app/models/Dealership';
import { IDealership } from 'src/app/models/IDealership';
import { ComuneService } from 'src/app/service/comune.service';
import { DealCarService } from 'src/app/service/deal-car.service';
import { esito } from '../car-adv-card/car-adv-card.component';

@Component({
  selector: 'app-dealership-form',
  templateUrl: './dealership-form.component.html',
  styleUrls: ['./dealership-form.component.scss']
})
export class DealershipFormComponent implements OnInit {

  @Input() dealership!: Dealership;

  @Output() showSuccess = new EventEmitter<esito>();

  form!: FormGroup;

  address!: Address;

  dirty!: boolean;

  comuni: Comune[] = [];

  constructor(private comuneServ: ComuneService, private dealServ: DealCarService, private adressServ: ComuneService) { }

  ngOnInit(): void {


    this.form = new FormGroup({
      name: new FormControl(this.dealership.name,),
      address: new FormGroup({
        id: new FormControl(this.dealership.address.id,),
        street: new FormControl(this.dealership.address.street, Validators.required),
        streetNum: new FormControl(this.dealership.address.streetNum, Validators.required),
        municipality: new FormControl(this.dealership.address.municipality, Validators.required),
      })
    });

    this.form.get('address')?.valueChanges.subscribe(
      res => {
        this.dirty = true;
      }
    )


    this.comuneServ.getAllComuni()
      .subscribe(com => {
        this.comuni = com;
      })
  }

  submit() {

    if(this.dirty)
    this.form.value.address.id=null

    let data_oldAddress: IDealership = {
      name: this.form.value.name,
      address: this.form.value.address,
    }

    console.log(data_oldAddress);

    if (this.dealership.id)
      this.dealServ.updateDealership(this.dealership.id, data_oldAddress)
        .subscribe({
          next: (value) => {
            console.log(value);
            this.showSuccess.emit({ esito: true, title: "Aggiornamento note", description: "Aggiornamento andato a buon fine" });
          },
          error: (err: HttpErrorResponse) => {
            this.showSuccess.emit({ esito: false, title: "Aggiornamento note", description: "l'aggiornamento fallito status: " + err.status });

          },
        })

  }

}

