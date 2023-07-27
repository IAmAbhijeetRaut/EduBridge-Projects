import { Component, OnInit } from '@angular/core';
import { NgForm } from "@angular/forms";
import { OrderDetails } from './../_model/order-details-model';
import { ActivatedRoute, Router } from "@angular/router";
import { Product } from "../_model/product.model";
import { ProductService } from "../_services/product.service";



declare var Razorpay :any;
@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent implements OnInit{
  productDetails:Product[] = []; 
  isSingleProductCheckout : string = '';


  orderDetails : OrderDetails = {
    fullName: "",
    fullAddress: "",
    contactNumber: "",
    alternateContactNumber: "",
    transactionId: "",
    orderProductQuantityList: []
  }


constructor(private activatedRoute: ActivatedRoute,
  private productService: ProductService,
  private router : Router){

}
  ngOnInit(): void {
    this.productDetails = this.activatedRoute.snapshot.data['productDetails'];
    this.isSingleProductCheckout = this.activatedRoute.snapshot.paramMap.get("isSingleProductCheckout")
    this.productDetails.forEach(
        x => this.orderDetails.orderProductQuantityList.push(
          {productId: x.productId, quantity: 1}
        )
    );
    console.log("Order Details",this.orderDetails);
    console.log("Product Details",this.productDetails);
  }

  //triggered on placeOrder click
  public placeOrder(orderForm:NgForm){
      this.productService.placeOrder(this.orderDetails,this.isSingleProductCheckout).subscribe(
        (resp) =>{
          console.log(resp);
          orderForm.reset();
          this.router.navigate(["/orderConfirm"])
        }, (error) =>{
          console.log(error)
        }
      )
  }

  public getQuantityForProduct(productId:number){
     const filteredProduct =  this.orderDetails.orderProductQuantityList.filter(
        (productQuantity) => productQuantity.productId === productId
      );

      return filteredProduct[0].quantity;
  }

  public getCalculatedTotal(productId:number, productDiscountedPrice: number){
      return this.getQuantityForProduct(productId) * productDiscountedPrice;
  }

  public onQuantityChange(quantity, productId){
      this.orderDetails.orderProductQuantityList.filter(
        (orderProduct) => orderProduct.productId === productId
      )[0].quantity = quantity;
  }

  public getCalculatedGrandTotal(){
      let grandTotal = 0;
      this.orderDetails.orderProductQuantityList.forEach(
      (productQuantity) => {
        const price = this.productDetails.filter(product => product.productId === productQuantity.productId)[0].productDiscountedPrice
        grandTotal = grandTotal + (price * productQuantity.quantity);
      }
      );
      return grandTotal
  }

  public createTransactionAndPlaceOrder(orderForm:NgForm){
    let amount = this.getCalculatedGrandTotal();
    this.productService.createTransaction(amount).subscribe(
      (resp)=>{
        console.log(resp);
        this.openTransactionModal(resp, orderForm);   
      },(err)=>{
        console.log(err);  
      }
    )
  }

  openTransactionModal(response:any, orderForm:NgForm){
      var options = {
        order_id:response.orderId,
        key: response.key,
        amount: response.amount,
        currency: response.currency,
        name: 'IndusMall',
        description:'Payment of indusmall shop products',
        image: 'https://cdn.pixabay.com/photo/2017/03/13/17/26/ecommerce-2140604_1280.jpg',
        handler: (response:any) => {
          if( response != null && response.razorpay_payment_id != null ){
            this.processResponse(response,orderForm);
          } else {
            alert("Payment Failed...")
          }
            
        },
        prefill : {
          name : 'AR',
          email: 'AR900@gmail.com',
          contact: '9347898989'
        },
        notes:{
          address: 'OnlineShopping @Indusmall'
        },
        theme:{
          color:'#F37254'
        }
      };

      var razorpayObject =  new Razorpay(options);
      razorpayObject.open();
  }

  processResponse(resp,orderForm:NgForm){
    this.orderDetails.transactionId = resp.razorpay_payment_id;
      this.placeOrder(orderForm);
      
  }
}
