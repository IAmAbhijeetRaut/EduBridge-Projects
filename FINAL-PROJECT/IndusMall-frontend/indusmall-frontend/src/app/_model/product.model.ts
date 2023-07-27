import { FileHandle } from "./file_handle.model";

export class Product{
    productId : number;
    productName : string;
    productDescription : string;
    productActualPrice : number;
    productDiscountedPrice : number;
    productImages: FileHandle[];
}