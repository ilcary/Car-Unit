export class Comune {
  id:number;
  comune:string;
  provincia:string;
  cap:string;
  regione:string;

  constructor(id:number,comune:string,provincia:string,cap:string,regione:string){
    this.id = id;
    this.comune = comune;
    this.provincia = provincia;
    this.cap = cap;
    this.regione = regione;
  }
}
