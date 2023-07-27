import { Component, OnInit } from '@angular/core';
import { ProductService } from './../_services/product.service';
import { HttpErrorResponse } from "@angular/common/http";
import { Product } from "../_model/product.model";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from "@angular/material/dialog";
import { DeleteConfirmationDialogComponent } from './delete-confirmation-dialog/delete-confirmation-dialog.component';
import { ShowProductImagesDialogComponent } from './show-product-images-dialog/show-product-images-dialog.component';
import { ImageProcessingServiceService } from "../image-processing-service.service";
import { map } from 'rxjs/operators';
import { Router } from "@angular/router";


@Component({
  selector: 'app-show-product-details',
  templateUrl: './show-product-details.component.html',
  styleUrls: ['./show-product-details.component.css']
})
export class ShowProductDetailsComponent implements OnInit{

showLoadMoreProduct = false;
showTable = false;
pageNumber:number = 0;
productDetails : Product[] = []
displayedColumns: string[] = ['Id', 'Product Name', 'description', 'Discounted Price' , 'Actual Price', 'Actions'];

constructor(private productService : ProductService, 
  private dialog: MatDialog, 
  private imagesDialog: MatDialog,
  private imageProcessionService:ImageProcessingServiceService,
  private router:Router){
}
ngOnInit():void{
  this.getAllProducts();
}

public searchByKeyword(searchKeyword){
  this.productDetails = []
  this.pageNumber = 0;
  this.getAllProducts(searchKeyword)

}

public getAllProducts(searchKeyword:string =""){
  this.showTable = false;
  this.productService.getAllProducts(this.pageNumber, searchKeyword)
  .pipe(
    map((x: Product[], i) => x.map((product: Product)=> this.imageProcessionService.createImages(product)))
  )
  .subscribe(
    (resp : Product[]) => {
      resp.forEach(product => this.productDetails.push(product))
      this.productDetails = resp;
      this.showTable = true;
      if(resp.length == 8){
        this.showLoadMoreProduct = true;
      }else{
        this.showLoadMoreProduct = false;
      }
    }, (error: HttpErrorResponse) => {
      console.log(error)
    }
  );
}

public deleteProduct(productId){
  this.productService.deleteProduct(productId).subscribe(
    (resp) => {

      this.getAllProducts();
    },
    (error: HttpErrorResponse) => {
      console.error(error);
    }
  ); 
}

showImages(product:Product){
  console.log(product);
  this.imagesDialog.open(ShowProductImagesDialogComponent, {
    data: {
      images: product.productImages
    },
    height: '400px',
    width: '800px',
  });
}

openDeleteConfirmationDialog(productId: number): void {
  const dialogRef: MatDialogRef<DeleteConfirmationDialogComponent> = this.dialog.open(DeleteConfirmationDialogComponent, {
    width: '400px',
    data: { productId }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result === true) {
      // User clicked 'Yes', perform the delete operation
      this.deleteProduct(productId);
    }
  });
}


editProductDetails(productId){
  this.router.navigate(['/addNewProduct', {productId:productId}]);
}

loadMoreProducts(){
    ++this.pageNumber;
    this.getAllProducts();
}

}
