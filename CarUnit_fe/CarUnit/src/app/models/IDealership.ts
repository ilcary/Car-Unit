import { Address } from "./Address";
import { User } from "./User";

export interface IDealership{
  id?:number;
  name: string;
  ceo?: User;
  address: Address;
  employees?: User[];
}
