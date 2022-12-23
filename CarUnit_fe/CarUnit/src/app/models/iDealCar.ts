import { Dealership } from "./Dealership";
import { FileHandle } from "./file_handle.model";

export interface IDealCar{

     id?:string;
     make?:string;
     model?:string;
     priceCop?:string;
     priceSell?:string;
     year?:string;
     km?:string;
     powerSupply?:string;
     gearbox?:string;
     emissionClass?:string;
     link?:string;
     productImages:FileHandle[];
     dealership?:Dealership;
}
