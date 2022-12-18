export interface ISearch{
  id?:number;
  nameSearch?:string;
  citta?: string;
  tipoDiVeicolo?:string;//nuovo, usato o km0
  inserzionista?:string;//azienda o privato
  marca?:string;
  modello?:string;
  annoImmatricolazioneDa?:string;
  annoImmatricolazioneA?:string;
  prezzoDa?:string;
  prezzoA?:string;
  kmDa?:string;
  kmA?:string;
  tipologiaAuto?:string;//berlina station ecc..
  carburante?:string;
  cambio?:string;
  porte?:string;
  colore?:string;
  classeDiEmissone?:string;

}
