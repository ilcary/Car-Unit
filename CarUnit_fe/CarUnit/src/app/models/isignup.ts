import { Address } from "./Address";

export interface ISignup {
    username: string;
    name: string;
    lastname: string;
    email: string;
    password: string;
    address: Address;
}
