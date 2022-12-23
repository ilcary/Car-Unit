import { Comune } from "./Comune";

export class Address {
  id?: number;
  street: string;
  streetNum: string;
  municipality: Comune;

  constructor(street: string, streetNum: string,municipality:Comune) {
    this.street = street;
    this.streetNum = streetNum;
    this.municipality = municipality;
  }

}
