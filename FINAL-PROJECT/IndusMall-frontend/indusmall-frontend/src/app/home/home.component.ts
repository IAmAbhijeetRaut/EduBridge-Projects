import { Component, OnInit } from '@angular/core';
import { ProductService } from "../_services/product.service";
import { Product } from "../_model/product.model";
import { map } from "rxjs";
import { ImageProcessingServiceService } from './../image-processing-service.service';
import { HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  pageNumber: number = 0;

  showLoadButton = false;

  productDetails = [];
  constructor(
    private productService:ProductService, 
    private imageProcessingServiceService: ImageProcessingServiceService,
    private router: Router){

  }
  ngOnInit(): void {
    this.getAllProducts();
  }

  public searchByKeyword(searchKeyword){
    console.log("SK : ",searchKeyword);
    this.pageNumber = 0;
    this.productDetails = []
    this.getAllProducts(searchKeyword);
}

  public getAllProducts(searchKey:string =""){
    this.productService.getAllProducts(this.pageNumber, searchKey).pipe(
      map((x: Product[], i) => x.map((product: Product)=> this.imageProcessingServiceService.createImages(product)))
    )
    .subscribe(
      (resp : Product[]) => {
        if(resp.length == 8){
          this.showLoadButton = true;
        }else{
          this.showLoadButton = false;
        }
        resp.forEach((p) => this.productDetails.push(p));
      }, (error: HttpErrorResponse) => {
        console.log(error)
      }
    );
  }

  public loadMoreProduct(){
    this.pageNumber = this.pageNumber + 1;
    this.getAllProducts();
  }

  public showProductDetails(productId : number){
      this.router.navigate(['/productViewDetails', {productId: productId}])
  }

  

}
