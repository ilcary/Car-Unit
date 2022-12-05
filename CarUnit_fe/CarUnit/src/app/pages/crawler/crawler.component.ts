import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-crawler',
  templateUrl: './crawler.component.html',
  styleUrls: ['./crawler.component.scss']
})
export class CrawlerComponent implements OnInit {

  form!: FormGroup;

  formAvanzato!: FormGroup;

  constructor() {
    this.form = new FormGroup({
      marca: new FormControl(null, Validators.required),
      modello: new FormControl(null, Validators.required),
      prezzoA: new FormControl(null, [Validators.required, Validators.email]),
      annoImmatricolazioneDa: new FormControl(null, Validators.required),
      citta: new FormControl(null, Validators.required),
    });

    this.formAvanzato = new FormGroup({
      marca: new FormControl(this.form.value.marca, Validators.required),
      modello: new FormControl(this.form.value.modello, Validators.required),
      prezzoA: new FormControl(this.form.value.prezzoA, [Validators.required, Validators.email]),
      annoImmatricolazioneDa: new FormControl(this.form.value.annoImmatricolazioneDa, Validators.required),
      citta: new FormControl(this.form.value.citta, Validators.required),
      tipoDiVeicolo: new FormControl(null, Validators.required),
      inserzionista: new FormControl(null, Validators.required),
      annoImmatricolazioneA: new FormControl(null, [Validators.required]),
      prezzoDa: new FormControl(null, Validators.required),
      kmDa: new FormControl(null, Validators.required),
      kmA: new FormControl(null, Validators.required),
      tipologiaAuto: new FormControl(null, Validators.required),
      carburante: new FormControl(null, Validators.required),
      cambio: new FormControl(null, Validators.required),
      porte: new FormControl(null, Validators.required),
      colore: new FormControl(null, Validators.required),
      classeDiEmissone: new FormControl(null, Validators.required),
    });

  }

  ngOnInit(): void {
  }

  avanzata(e:Event): void {
    this.formAvanzato.get('marca')?.setValue(this.form.value.marca);
    this.formAvanzato.get('modello')?.setValue(this.form.value.modello);
    this.formAvanzato.get('prezzoA')?.setValue(this.form.value.prezzoA);
    this.formAvanzato.get('annoImmatricolazioneDa')?.setValue(this.form.value.annoImmatricolazioneDa);
    this.formAvanzato.get('citta')?.setValue(this.form.value.citta);

  }

  submit() { }

}
