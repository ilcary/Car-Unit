import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Comune } from 'src/app/models/Comune';
import { ICarAdv } from 'src/app/models/icaradv';
import { ISearch } from 'src/app/models/iricerca';
import { AuthService } from 'src/app/service/auth.service';
import { CarAdvService } from 'src/app/service/car-adv.service';
import { ComuneService } from 'src/app/service/comune.service';
import { MessageService } from 'primeng/api';
import { severity } from 'src/app/models/severityEnum';
import { HttpErrorResponse } from '@angular/common/http';

type esito = {
  esito: boolean,
  title: string,
  description: string
}

@Component({
  selector: 'app-crawler',
  templateUrl: './crawler.component.html',
  styleUrls: ['./crawler.component.scss'],
  providers: [MessageService]
})
export class CrawlerComponent implements OnInit {

  loadingModels!: boolean;

  openAdvacedSearch: boolean = false;
  openSearch: boolean = true;

  username_: string = String(this.auth.getLoggedUser()?.username)

  form!: FormGroup;

  searchName!: string;

  formAvanzato!: FormGroup;

  fasciaAnniImmatricolazione: string[] = this.anniImmatricolazioneBuiler();

  modelliAuto: string[] = ["selezionare marca"];

  marcheSubito: string[] = ["Abarth", "Ac", "Acm", "Aiways", "Aixam", "Alfa romeo", "Alpina-bmw", "Alpine", "Amg", "Apal", "Ariel", "Aro", "Asia motors", "Aston martin", "Austin rover", "Autobianchi", "Auverland", "Bellier", "Bentley", "Bertone", "Biagini", "Bmw", "Boxel", "Bugatti", "Buick", "Cadillac", "Carletti", "Casalini", "Caterham", "Chatenet", "Chevrolet", "Chrysler", "Citroen", "Citycar", "Cmc (carletti)", "Corvette", "Cupra", "Dacia", "Daewoo", "Daihatsu", "Daimler", "Dallara", "De la chapelle", "De tomaso", "Dfsk", "Dodge", "Donkervoort", "Dr", "Ds", "Ducati energia", "Effedi", "Eli", "Emc", "Epocar", "Evo", "Feab", "Ferrari", "Fiat", "Fisker", "Ford", "Fso", "Gem", "Ginetta", "Giotti victoria", "Giottiline", "Goupil", "Great wall motor", "Grecav", "Green company", "Haval", "Honda", "Hummer", "Hyundai", "Iato", "Ineos", "Infiniti", "Innocenti", "Iso", "Isuzu", "Italcar", "Iveco", "Jaguar", "Jdm", "Jeep", "Kia", "Lada", "Lamborghini", "Lancia", "Land rover", "Lexus", "Ligier", "Lotus", "Luaz (volin)", "Lynk&co", "Mahindra", "Marcos", "Martin motors", "Maruti", "Maserati", "Maybach", "Mazda", "Mazzanti", "Mazzieri", "Mclaren", "Mega", "Melex", "Mercedes", "Meta", "Mg", "Mia electric", "Micro vett", "Microcar", "Middlebridge", "Militem", "Minauto", "Mini", "Mitsubishi", "Moke", "Moretti", "Morgan", "Mpm motors", "Mustang", "Nanocar", "Nissan", "Nissan spagna", "Noble", "Oltcit", "Omai", "Opel", "Oto melara", "P.g.o.", "Pagani", "Panther", "Peugeot", "Piaggio", "Polestar", "Pontiac", "Porsche", "Puma italia", "Qvale", "Rayton fissore", "Regis", "Renault", "Rolls royce", "Romeo ferraris", "Saab", "Saleen", "Santana", "Savel-erad", "Seat", "Seca", "Secma", "Seres", "Shuanghuan", "Skoda", "Smart", "Ssangyong", "Start lab", "Suzuki", "Talbot", "Tasso", "Tata", "Tazzari ev", "Tesla", "Today sunshine", "Torpedo", "Town life", "Toyota", "Tvr", "Uaz", "Umm", "Valentini", "Venturi", "Volga", "Volkswagen", "Volkswagen messico", "Volvo", "Xev", "Xindayang", "Yugo", "Zaz", "Zd"];

  fasciaKmDaSubito: string[] = ["Km 0", "0", "5.000", "10.000", "15.000", "20.000", "25.000", "30.000", "35.000", "40.000", "45.000", "50.000", "55.000", "60.000", "65.000", "70.000", "75.000", "80.000", "85.000", "90.000", "95.000", "100.000", "110.000", "120.000", "130.000", "140.000", "150.000", "160.000", "170.000", "180.000", "190.000", "200.000", "250.000", "300.000", "350.000", "400.000", "450.000", "500.000"];

  fasciaKmASubito: string[] = ["km 0", "4.999", "9.999", "14.999", "19.999", "24.999", "29.999", "34.999", "39.999", "44.999", "49.999", "54.999", "59.999", "64.999", "69.999", "74.999", "79.999", "84.999", "89.999", "94.999", "99.999", "109.999", "119.999", "129.999", "139.999", "149.999", "159.999", "169.999", "179.999", "189.999", "199.999", "249.999", "299.999", "349.999", "399.999", "449.999", "499.999"];

  fasciaPrezziSubito: string[] = ["500", "1.000", "2.000", "3.000", "4.000", "5.000", "6.000", "7.000", "8.000", "9.000", "10.000", "11.000", "12.000", "13.000", "14.000", "15.000", "16.000", "17.000", "18.000", "19.000", "20.000", "21.000", "22.000", "23.000", "24.000", "25.000", "26.000", "27.000", "28.000", "29.000", "30.000", "35.000", "40.000", "45.000", "50.000", "60.000", "70.000", "80.000", "90.000", "120.000", "150.000", "250.000", "500.000", "1.000.000", "2.500.000",];

  tipologiaAuto: string[] = ["Utilitaria", "Berlina", "Station Wagon", "Monovolume", "SUV/Fuoristrada", "Cabrio", "Coupè", "City Car", "Altro"];

  tipologiaAnnuncio: string[] = ["Privato", "Azienda"];

  tipologiaCarburante: string[] = ["Benzina", "Diesel", "Gpl", "Elettrica", "Ibrida", "Metano", "Altro"];

  tipologiaColore: string[] = ["bianco", "grigio", "marrone", "rosso", "giallo", "verde", "blu"];

  classiDiEmissone: string[] = ["euro 6", "euro 5", "euro 4", "euro 3", "euro 2", "euro 1", "pre-euro"];

  tipoCambio: string[] = ["Manuale", "Automatico", "Sequenziale", "Altro"];

  numPorte: string[] = ["2/3", "4/5", "6/7"];

  comuni: Comune[] = [];
  countries: string[] = [];

  starredSearches: ISearch[]= [];

  selectedSearch!: ISearch;


  annunci!: ICarAdv[];

  constructor(private comuneServ: ComuneService, private modelServ: CarAdvService, private auth: AuthService, private messageService: MessageService) {
    this.form = new FormGroup({
      marca: new FormControl(null,),
      modello: new FormControl(null,),
      prezzoA: new FormControl(null,),
      annoImmatricolazioneDa: new FormControl(null,),
      citta: new FormControl(null,),
    });

    this.formAvanzato = new FormGroup({
      marca: new FormControl(this.form.value.marca,),
      modello: new FormControl(this.form.value.modello,),
      prezzoA: new FormControl(this.form.value.prezzoA,),
      annoImmatricolazioneDa: new FormControl(this.form.value.annoImmatricolazioneDa,),
      citta: new FormControl(this.form.value.citta,),
      tipoAnnuncio: new FormControl(null,),
      annoImmatricolazioneA: new FormControl(null, []),
      prezzoDa: new FormControl(null,),
      kmDa: new FormControl(null,),
      kmA: new FormControl(null,),
      tipologiaAuto: new FormControl(null,),
      carburante: new FormControl(null,),
      cambio: new FormControl(null,),
      porte: new FormControl(null,),
      colore: new FormControl(null,),
      classeDiEmissone: new FormControl(null,),
    });

  }

  ngOnInit(): void {

    this.modelServ.getSearchesAdvByUserId(String(this.auth.getLoggedUser()?.id))
    .subscribe((res) => {
      this.starredSearches = res;
    })

    console.log("popoipipi");

    this.comuneServ.getAllComuni()
      .subscribe(com => {
        this.comuni = com;
      })

  }

  avanzata(e: Event): void {
    this.formAvanzato.get('marca')?.setValue(this.form.value.marca);
    this.formAvanzato.get('modello')?.setValue(this.form.value.modello);
    this.formAvanzato.get('prezzoA')?.setValue(this.form.value.prezzoA);
    this.formAvanzato.get('annoImmatricolazioneDa')?.setValue(this.form.value.annoImmatricolazioneDa);
    this.formAvanzato.get('citta')?.setValue(this.form.value.citta);

  }

  submit() {

    let searchForm: ISearch = {
      marca: this.form.value.marca,
      modello: this.form.value.modello,
      prezzoA: this.form.value.prezzoA,
      annoImmatricolazioneDa: this.form.value.annoImmatricolazioneDa,
      citta: this.form.value.citta,
    };

    console.log(searchForm);

    this.modelServ.getAdvBySearch(searchForm)
      .subscribe(res => {
        console.log(res)
        this.annunci = res;

      })
  }

  submitAvanzata(): void {

    let searchForm: ISearch = {
      marca: this.formAvanzato.value.marca,
      modello: this.formAvanzato.value.modello,
      prezzoA: this.formAvanzato.value.prezzoA,
      annoImmatricolazioneDa: this.formAvanzato.value.annoImmatricolazioneDa,
      citta: this.formAvanzato.value.citta,
      inserzionista: this.formAvanzato.value.tipoAnnuncio,
      annoImmatricolazioneA: this.formAvanzato.value.annoImmatricolazioneA,
      prezzoDa: this.formAvanzato.value.prezzoDa,
      kmDa: this.formAvanzato.value.kmDa,
      kmA: this.formAvanzato.value.kmA,
      tipologiaAuto: this.formAvanzato.value.tipologiaAuto,
      carburante: this.formAvanzato.value.carburante,
      cambio: this.formAvanzato.value.cambio,
      porte: this.formAvanzato.value.porte,
      colore: this.formAvanzato.value.colore,
      classeDiEmissone: this.formAvanzato.value.classeDiEmissone,
    };

    console.log(searchForm);

    this.modelServ.getAdvBySearch(searchForm)
      .subscribe(res => {
        console.log(res)
        this.annunci = res;

      })

  }

  anniImmatricolazioneBuiler(): string[] {
    let max = new Date().getFullYear()
    let years: string[] = [];
    for (let i = max; i >= 1980; i--) {
      years.push(i.toString());
    }
    return years;
  }

  getModelliByMake(e: Event): void {
    this.loadingModels = true;
    let make = this.form.value.marca;
    if (make == null) {
      make = this.formAvanzato.value.marca;
    };
    if (make == null) return;

    console.log(make);
    this.modelServ.getModelsOfMake(make)//fino a che carica la chiamata mettere un loading al dropdown dei modelli
      .subscribe(models => {
        this.modelliAuto = models;
        this.loadingModels = false;
      })

  }

  saveSearchAdv(): void {

    let searchForm: ISearch = {
      marca: this.formAvanzato.value.marca,
      modello: this.formAvanzato.value.modello,
      prezzoA: this.formAvanzato.value.prezzoA,
      annoImmatricolazioneDa: this.formAvanzato.value.annoImmatricolazioneDa,
      citta: this.formAvanzato.value.citta,
      inserzionista: this.formAvanzato.value.tipoAnnuncio,
      annoImmatricolazioneA: this.formAvanzato.value.annoImmatricolazioneA,
      prezzoDa: this.formAvanzato.value.prezzoDa,
      kmDa: this.formAvanzato.value.kmDa,
      kmA: this.formAvanzato.value.kmA,
      tipologiaAuto: this.formAvanzato.value.tipologiaAuto,
      carburante: this.formAvanzato.value.carburante,
      cambio: this.formAvanzato.value.cambio,
      porte: this.formAvanzato.value.porte,
      colore: this.formAvanzato.value.colore,
      classeDiEmissone: this.formAvanzato.value.classeDiEmissone,
    };


    console.log(searchForm);

    this.modelServ.saveSearchAdv(this.username_, searchForm)
      .subscribe(data => {
        console.log(data);
        this.showNot("Perfetto", "Ricerca salvata con successo");
        this.starredSearches.push(data)
        console.log(this.starredSearches);
      })
  }

  deleteSearch(search: ISearch){
    if(search.id)
    this.modelServ.deleteSearch(search.id)
    .subscribe({
      next: (value) => {
        console.log(value);
        this.messageService.add({ severity: 'success', summary: "Ricerca eliminata", detail: 'La ricerca: '+value.nameSearch+' è stata eliminata con successo' });
        this.starredSearches = this.starredSearches.filter(s => s.id !== value.id);
        console.log(this.starredSearches);

      },
      error: (err: HttpErrorResponse) => {
        this.messageService.add({ severity: 'error', summary: "Impossibile recuperare gli utenti di CARUNIT", detail: err.message });

      },
    })
  }

  showNot(title: string, message: string) {
    this.messageService.add({ severity: 'success', summary: title, detail: message });
  }

  showSuccess(x: esito) {

    if (x.esito) {
      this.messageService.add({ severity: 'success', summary: x.title, detail: x.description });
    } else {
      this.messageService.add({ severity: 'error', summary: x.title, detail: x.description });
    }

  }

  showConfirm() {
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'info', summary: 'Vuoi salvare  la ricerca?', detail: 'Inserire un nome e confermare' });
  }

  onConfirm() {



    let searchForm: ISearch = {
      nameSearch: this.searchName,
      marca: this.form.value.marca ? this.formAvanzato.value.marca : this.form.value.marca ,
      modello: this.form.value.modello ? this.formAvanzato.value.modello : this.form.value.modello ,
      prezzoA: this.form.value.prezzoA ? this.formAvanzato.value.prezzoA : this.form.value.prezzoA ,
      annoImmatricolazioneDa: this.form.value.annoImmatricolazioneDa ? this.formAvanzato.value.annoImmatricolazioneDa : this.form.value.annoImmatricolazioneDa ,
      citta: this.form.value.citta ? this.formAvanzato.value.citta : this.form.value.citta ,
      inserzionista: this.formAvanzato.value.tipoAnnuncio,
      annoImmatricolazioneA: this.formAvanzato.value.annoImmatricolazioneA,
      prezzoDa: this.formAvanzato.value.prezzoDa,
      kmDa: this.formAvanzato.value.kmDa,
      kmA: this.formAvanzato.value.kmA,
      tipologiaAuto: this.formAvanzato.value.tipologiaAuto,
      carburante: this.formAvanzato.value.carburante,
      cambio: this.formAvanzato.value.cambio,
      porte: this.formAvanzato.value.porte,
      colore: this.formAvanzato.value.colore,
      classeDiEmissone: this.formAvanzato.value.classeDiEmissone,
    };

    console.log(searchForm);

    this.modelServ.saveSearchAdv(this.username_, searchForm)
      .subscribe(data => {
        console.log(data);
        this.showNot("Perfetto", "Ricerca salvata con successo");
        this.starredSearches.push(data)
        console.log(this.starredSearches);
      })

    this.messageService.clear('c');
  }

  onReject() {
    this.messageService.clear('c');
  }

  checkInputNameSearch(): boolean {
    if (this.searchName !== undefined && this.searchName !== null) {
      return true;
    }
    return false;
  }


   onRowSelect(e: Event) {
        this.messageService.add({severity: 'info', summary: 'Product Selected'});
    }

    selectSearch(search:ISearch){

      console.log(search);

      this.openSearch=false;
      this.openAdvacedSearch= true;

      this.formAvanzato.get('marca')?.setValue(search.marca);
      this.formAvanzato.get('modello')?.setValue(search.modello);
      this.formAvanzato.get('prezzoA')?.setValue(search.prezzoA);
      this.formAvanzato.get('annoImmatricolazioneDa')?.setValue(search.annoImmatricolazioneDa);
      this.formAvanzato.get('citta')?.setValue(search.citta);
      this.formAvanzato.get('tipoAnnuncio')?.setValue(search.inserzionista);
      this.formAvanzato.get('annoImmatricolazioneA')?.setValue(search.annoImmatricolazioneA);
      this.formAvanzato.get('prezzoDa')?.setValue(search.prezzoDa);
      this.formAvanzato.get('kmDa')?.setValue(search.kmDa);
      this.formAvanzato.get('kmA')?.setValue(search.kmA);
      this.formAvanzato.get('tipologiaAuto')?.setValue(search.tipologiaAuto);
      this.formAvanzato.get('carburante')?.setValue(search.carburante);
      this.formAvanzato.get('cambio')?.setValue(search.cambio);
      this.formAvanzato.get('porte')?.setValue(search.porte);
      this.formAvanzato.get('colore')?.setValue(search.colore);
      this.formAvanzato.get('classeDiEmissone')?.setValue(search.classeDiEmissone);



    }

}
