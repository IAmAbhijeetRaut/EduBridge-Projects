import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from "../_model/product.model";
import { ProductService } from "../_services/product.service";

@Component({
  selector: 'app-product-view-details',
  templateUrl: './product-view-details.component.html',
  styleUrls: ['./product-view-details.component.css']
})
export class ProductViewDetailsComponent implements OnInit{

  selectedProductIndex : number = 0;
  product: Product;
  constructor(private activatedRoute : ActivatedRoute,
    private router: Router,
    private productService:ProductService){

  }
  ngOnInit(): void {
    this.product = this.activatedRoute.snapshot.data['product'];
    console.log(this.product);

  }

  public addToCart(productId){
      this.productService.addToCart(productId).subscribe(
        (response) =>{
          console.log(response);
        }, (err)=>{
          console.log(err);
          
        }
      )
  }

  public changeIndex(index: number){
    this.selectedProductIndex = index
  }

  buyProduct(productId: number){
    this.router.navigate(['/buyProduct',{
      isSingleProductCheckout : true, id: productId
    }]);
  }




}
