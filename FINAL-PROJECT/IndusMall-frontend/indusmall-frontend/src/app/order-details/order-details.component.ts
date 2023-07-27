import { Component, OnInit } from '@angular/core';
import { ProductService } from "../_services/product.service";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit{
  
  displayedColumns: string[] = ['Id', 'Product Name', 'Person Name', 'Address', 'Contact No.', 'Status', 'Action'];
  allOrderDetails = [];
  status:string ='All';

  constructor(private productService:ProductService){

  }
  ngOnInit(): void {
   this.getAllOrderDetailsForAdmin(this.status);
  }

  public getAllOrderDetailsForAdmin(status:string){
    this.productService.getAllOrderDetailsForAdmin(status).subscribe(
      (resp)=>{
        console.log(resp);
        this.allOrderDetails = resp;
        
      },(err)=>{
        console.log(err);
        
      }
    )
  }

  markAsDelivered(orderId){
      this.productService.markAsDelivered(orderId).subscribe(
        (resp)=>{
          console.log(resp);
          this.getAllOrderDetailsForAdmin(this.status);
        },(err)=>{
          console.log(err);
        }
      )
  }

}
