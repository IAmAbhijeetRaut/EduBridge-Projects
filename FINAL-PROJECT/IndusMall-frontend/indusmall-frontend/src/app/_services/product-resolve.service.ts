import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable, map, of } from 'rxjs';
import { Product } from "../_model/product.model";
import { ProductService } from "./product.service";
import { ImageProcessingServiceService } from "../image-processing-service.service";

@Injectable({
  providedIn: 'root'
})
export class ProductResolveService implements Resolve<Product>{

  constructor(private productService: ProductService, private imageProcessingService: ImageProcessingServiceService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Observable<Product> {
    const id = route.paramMap.get("productId");
    if(id){
        return this.productService.getProductDetailsById(id).pipe(
          map(p => this.imageProcessingService.createImages(p))
        );
    }else{
      return of(this.getProductDetails())
    }
  }

  getProductDetails(){
    console.log("Empty Product Sent")
    return {
    productId:null,
    productName: "",
    productDescription: "",
    productActualPrice : 0,
    productDiscountedPrice: 0,
    productImages:[]
    }
  }
}
