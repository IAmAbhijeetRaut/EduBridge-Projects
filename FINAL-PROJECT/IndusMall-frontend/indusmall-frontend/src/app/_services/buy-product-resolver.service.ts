import { Injectable } from '@angular/core';
import { Product } from "../_model/product.model";
import { map } from "rxjs/operators";
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductService } from "./product.service";
import { ImageProcessingServiceService } from './../image-processing-service.service';

@Injectable({
  providedIn: 'root'
})
export class BuyProductResolverService implements Resolve<Product[]>{
  
  constructor(private productService: ProductService, private imageProcessingServiceService: ImageProcessingServiceService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Product[] | Observable<Product[]> | Promise<Product[]>{
    const id = route.paramMap.get("id");
    const isSingleProductCheckout = route.paramMap.get("isSingleProductCheckout");
    return this.productService.getProductDetails(isSingleProductCheckout, id)
    .pipe(
      map(
        (x:Product[],i)=>x.map((product:Product)=>this.imageProcessingServiceService.createImages(product))
      )
    );
  }

}
