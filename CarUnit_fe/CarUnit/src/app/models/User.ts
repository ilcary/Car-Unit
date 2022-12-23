import { Address } from "./Address";
import { Dealership } from "./Dealership";
import { ILogin } from "./ilogin";
import { ITask } from "./iTask";

export class User implements ILogin {
  id!:number | undefined;
  username: string;
  name: string;
  lastname: string;
  email: string;
  password: string;
  address: Address;
  _dealership!:Dealership;
  ceo!:boolean;
  tasks!:ITask[];


  constructor(name: string,username: string, lastname: string, email: string, password: string,address: Address){
    this.username = username;
    this.name = name;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.address = address;
  }

  public set dealership(dealership:Dealership){
    this.dealership = dealership;
  }



}
