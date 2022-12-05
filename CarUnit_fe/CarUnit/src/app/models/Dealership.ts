
import { Address } from "./Address";
import {User} from "./User";

export class Dealership {
  name: string;
  ceo: User;
  address: Address;
  employees: User[];

  constructor(name: string, address: Address, employees: User[], ceo:User){
    this.address = address;
    this.employees = employees;
    this.ceo = ceo;
    this.name = name;
  }
}
