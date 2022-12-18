import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { MessageService } from 'primeng/api';
import { Dealership } from 'src/app/models/Dealership';
import { FileHandle } from 'src/app/models/file_handle.model';
import { IDealCar } from 'src/app/models/iDealCar';
import { CarAdvService } from 'src/app/service/car-adv.service';
import { DealCarService } from 'src/app/service/deal-car.service';

@Component({
  selector: 'app-form-deal-car',
  templateUrl: './form-deal-car.component.html',
  styleUrls: ['./form-deal-car.component.scss'],
  providers: [MessageService]
})
export class FormDealCarComponent implements OnInit {

  @Input() dealership!: Dealership;

  dataCar:IDealCar = {photos: []};

  uploadedFiles: any[] = [];

  marcheSubito: string[] = ["Abarth", "Ac", "Acm", "Aiways", "Aixam", "Alfa romeo", "Alpina-bmw", "Alpine", "Amg", "Apal", "Ariel", "Aro", "Asia motors", "Aston martin", "Austin rover", "Autobianchi", "Auverland", "Bellier", "Bentley", "Bertone", "Biagini", "Bmw", "Boxel", "Bugatti", "Buick", "Cadillac", "Carletti", "Casalini", "Caterham", "Chatenet", "Chevrolet", "Chrysler", "Citroen", "Citycar", "Cmc (carletti)", "Corvette", "Cupra", "Dacia", "Daewoo", "Daihatsu", "Daimler", "Dallara", "De la chapelle", "De tomaso", "Dfsk", "Dodge", "Donkervoort", "Dr", "Ds", "Ducati energia", "Effedi", "Eli", "Emc", "Epocar", "Evo", "Feab", "Ferrari", "Fiat", "Fisker", "Ford", "Fso", "Gem", "Ginetta", "Giotti victoria", "Giottiline", "Goupil", "Great wall motor", "Grecav", "Green company", "Haval", "Honda", "Hummer", "Hyundai", "Iato", "Ineos", "Infiniti", "Innocenti", "Iso", "Isuzu", "Italcar", "Iveco", "Jaguar", "Jdm", "Jeep", "Kia", "Lada", "Lamborghini", "Lancia", "Land rover", "Lexus", "Ligier", "Lotus", "Luaz (volin)", "Lynk&co", "Mahindra", "Marcos", "Martin motors", "Maruti", "Maserati", "Maybach", "Mazda", "Mazzanti", "Mazzieri", "Mclaren", "Mega", "Melex", "Mercedes", "Meta", "Mg", "Mia electric", "Micro vett", "Microcar", "Middlebridge", "Militem", "Minauto", "Mini", "Mitsubishi", "Moke", "Moretti", "Morgan", "Mpm motors", "Mustang", "Nanocar", "Nissan", "Nissan spagna", "Noble", "Oltcit", "Omai", "Opel", "Oto melara", "P.g.o.", "Pagani", "Panther", "Peugeot", "Piaggio", "Polestar", "Pontiac", "Porsche", "Puma italia", "Qvale", "Rayton fissore", "Regis", "Renault", "Rolls royce", "Romeo ferraris", "Saab", "Saleen", "Santana", "Savel-erad", "Seat", "Seca", "Secma", "Seres", "Shuanghuan", "Skoda", "Smart", "Ssangyong", "Start lab", "Suzuki", "Talbot", "Tasso", "Tata", "Tazzari ev", "Tesla", "Today sunshine", "Torpedo", "Town life", "Toyota", "Tvr", "Uaz", "Umm", "Valentini", "Venturi", "Volga", "Volkswagen", "Volkswagen messico", "Volvo", "Xev", "Xindayang", "Yugo", "Zaz", "Zd"];

  tipologiaCarburante: string[] = ["Benzina", "Diesel", "Gpl", "Elettrica", "Ibrida", "Metano", "Altro"];

  classiDiEmissone: string[] = ["euro 6", "euro 5", "euro 4", "euro 3", "euro 2", "euro 1", "pre-euro"];

  tipoCambio: string[] = ["Manuale", "Automatico", "Sequenziale", "Altro"];

  loadingModels!: boolean;

  modelliAuto: string[] = ["selezionare marca"];

  fasciaAnniImmatricolazione: string[] = this.anniImmatricolazioneBuiler();

  form!: FormGroup;

  constructor(
    private dealServ: DealCarService,
    private modelServ: CarAdvService,
    private messageService: MessageService,
    private httpClient: HttpClient,
    private sanitizer: DomSanitizer
    ) { }

  ngOnInit(): void {

    this.form = new FormGroup({
      make: new FormControl(null,),
      model: new FormControl(null,),
      priceCop: new FormControl(null,),
      priceSell: new FormControl(null,),
      year: new FormControl(null,),
      km: new FormControl(null,),
      powerSupply: new FormControl(null,),
      gearbox: new FormControl(null,),
      emissionClass: new FormControl(null,),
      photo: new FormControl(null,),
    });

  }

  getModelliByMake(e: Event): void {
    this.loadingModels = true;
    let make = this.form.value.make;
    console.log(make);

    if (make == null) return;

    console.log(make);
    this.modelServ.getModelsOfMake(make)//fino a che carica la chiamata mettere un loading al dropdown dei modelli
      .subscribe(models => {
        this.modelliAuto = models;
        this.loadingModels = false;
      })

  }

  onUpload(event: any) {
    for (let file of event.files) {
      console.log(file);

      this.uploadedFiles.push(file);
    }
    this.messageService.add({ severity: 'info', summary: 'File Uploaded', detail: '' });
  }

  anniImmatricolazioneBuiler(): string[] {
    let max = new Date().getFullYear()
    let years: string[] = [];
    for (let i = max; i >= 1900; i--) {
      years.push(i.toString());
    }
    return years;
  }

  submit() {

    if(this.dealership.id){


      this.dataCar.make =this.form.value.make;
      this.dataCar.model =this.form.value.model;
      this.dataCar.priceCop =this.form.value.priceCop;
      this.dataCar.year =this.form.value.year;
      this.dataCar.powerSupply =this.form.value.powerSupply;
      this.dataCar.km =this.form.value.km;
      this.dataCar.gearbox =this.form.value.gearbox;
      this.dataCar.emissionClass =this.form.value.emissionClass;

      // this.dataCar = {
        //   make: this.form.value.make,
        //   model: this.form.value.model,
        //   priceCop: this.form.value.priceCop,
        //   year: this.form.value.year,
        //   km: this.form.value.km,
        //   powerSupply: this.form.value.powerSupply,
        //   gearbox: this.form.value.gearbox,
        //   emissionClass: this.form.value.emissionClass,
        // };

        console.log(this.dataCar);

        const productFormData = this.prepareFormData(this.dataCar)

        console.log(productFormData);

        this.dealServ.saveDealCar(productFormData, this.dealership.id ).subscribe({
          next: (response: IDealCar)=>{
            console.log(response);
            this.form.reset();
          },
          error: (error: HttpErrorResponse)=>{
            console.log(error);
          }
        });

      }
  }

  prepareFormData(dealCar: IDealCar):FormData{
    const formData = new FormData();

    formData.append(
      'dealCar',
      new Blob([JSON.stringify(dealCar)], {type:"application/json"})
    );

    for(let i = 0; i < dealCar.photos?.length; i++){
      formData.append(
        'imageFile',
        dealCar.photos[i].file,
        dealCar.photos[i].file.name
      );
    }
    return formData;
  }

  // l'evento restituito contiene la proprietà currentFiles la quale presenta all'interno i nostri file con name e url
  onFileSelected(event: any) {
    console.log(event);
    if (event.currentFiles) {
      const file_ = event.currentFiles[0];

      const fileHandle: FileHandle = {
        file: file_,
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file_)
        )
      }
      this.dataCar.photos.push(fileHandle)
    }

  }



}
