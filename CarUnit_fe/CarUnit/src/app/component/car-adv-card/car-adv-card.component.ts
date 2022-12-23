import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ICarAdv } from 'src/app/models/icaradv';
import { enumStateElab } from 'src/app/models/enumStateElab';
import { IStateAdv } from 'src/app/models/stateAdv';
import { AuthService } from 'src/app/service/auth.service';
import { CarAdvService } from 'src/app/service/car-adv.service';
import { MessageService } from 'primeng/api';
import { HttpErrorResponse } from '@angular/common/http';

export type esito = {
  esito: boolean,
  title: string,
  description: string
}

@Component({
  selector: 'app-car-adv-card',
  templateUrl: './car-adv-card.component.html',
  styleUrls: ['./car-adv-card.component.scss'],
  providers: [MessageService]
})


export class CarAdvCardComponent implements OnInit {

  @Input() adv!: ICarAdv;

  @Output() showSuccess = new EventEmitter<esito>();

  showIconCardState: boolean = false;

  isMyNote: boolean = false;

  display: boolean = false;

  formCarState!: FormGroup;

  advState!: IStateAdv;

  username_: string = String(this.auth.getLoggedUser()?.username)

  isSave: boolean = true;

  statiDiElaborazione: string[] = [
    enumStateElab.CONTACTED_EMAIL,
    enumStateElab.CONTACTED_WHATSAPP,
    enumStateElab.NEGOTIATION,
    enumStateElab.PREFERRED,
    enumStateElab.DISCARDED
  ];

  constructor(private auth: AuthService, private carAdvService: CarAdvService, private messageService: MessageService) { }

  ngOnInit(): void {
    console.log("username " + this.username_);

    this.formCarState = new FormGroup({
      id: new FormControl(this.adv.link),
      username: new FormControl({ value: this.username_, disabled: true }),
      state: new FormControl(null, Validators.required),
      note: new FormControl(),
    });

    if (this.adv.link) {
      this.carAdvService.getNoteAdvById(this.adv.link)
        .subscribe(res => {
          console.log(res)
          this.advState = res;
          this.checkActualForm();
        })

    }


  }

  checkActualForm(): void {
    //se esiste una nota la trasferisco direttamente al form
    if (this.advState !== null) {
      this.showIconCardState = true;
      this.isSave = false;
      console.log(this.isSave);

      if (this.advState.username == this.username_) {
        console.log("username uguale a quello loggato");
        this.isMyNote = true;
      }else{
        //se l'utente loggato non è lo stesso di quello che ha fatto la note disabilito il form e non lo rendo modificabile
        this.formCarState.disable();
      }

      this.formCarState.get('id')?.setValue(this.advState.id)
      this.formCarState.get('username')?.setValue(this.advState.username)
      this.formCarState.get('state')?.setValue(this.advState.state)
      this.formCarState.get('note')?.setValue(this.advState.note)

    }
  }

  getDropdownIcon(): string {

    switch (this.formCarState.value.state) {
      case enumStateElab.CONTACTED_EMAIL:
        return "pi pi-envelope"
      case enumStateElab.CONTACTED_WHATSAPP:
        return "pi pi-whatsapp"
      case enumStateElab.NEGOTIATION:
        return "pi pi-shopping-bag"
      case enumStateElab.PREFERRED:
        return "pi pi-star-fill"
      case enumStateElab.DISCARDED:
        return "pi pi-minus-circle"
      default:
        return "pi pi-pencil"
    }

  }

  showDialog() {
    this.display = true;
  }


  save(): void {

    if (this.adv.link !== undefined) {

      let stateData: IStateAdv = {
        id: this.adv.link,
        username: this.username_,
        state: this.fromStateToEnum(this.formCarState.value.state),
        note: this.formCarState.value.note,
      }

      this.carAdvService.saveNoteAdv(stateData)
        .subscribe(res => {
          console.log(res);
          this.display = false;
        });

    } else {
      throw new Error("il link è undefined, controllare l'annuncio nel db")
    }
  }

  update(): void {


    let stateData: IStateAdv = {
      id: this.advState.id,
      username: this.advState.username,
      state: this.fromStateToEnum(this.formCarState.value.state),
      note: this.formCarState.value.note,
    }

    this.carAdvService.updateNoteAdv(stateData)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.display = false;
          this.showSuccess.emit({ esito: true, title: "Aggiornamento note", description: "Aggiornamento andato a buon fine" });
        },
        error: (err) => {
          this.showSuccess.emit({ esito: false, title: "Aggiornamento note", description: "l'aggiornamento fallito status: " + err.status });
        }
      });


  }

  fromStateToEnum(str: string): string {
    switch (str) {
      case "Preferito":
        return "PREFERRED";

      case "Contattato Whatsapp":
        return "CONTACTED_WHATSAPP";

      case "Contattato email":
        return "CONTACTED_EMAIL";

      case "In trattativa":
        return "NEGOTIATION"

      case "Scartato":
        return "DISCARDED";

      default:
        return "";
    }
  }

  showNot(title: string, message: string) {
    this.messageService.add({ severity: 'success', summary: title, detail: message });
  }

}
