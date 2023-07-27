import { Component } from '@angular/core';
import { Product } from "../_model/product.model";
import { FormBuilder, NgForm } from '@angular/forms';
import { ProductService } from './../_services/product.service';
import { HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { FileHandle } from './../_model/file_handle.model';
import { DomSanitizer } from "@angular/platform-browser";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-add-new-product',
  templateUrl: './add-new-product.component.html',
  styleUrls: ['./add-new-product.component.css']
})
export class AddNewProductComponent {

  isNewProduct = true;

  product:Product = {
    productId : null,
    productName: "",
    productDescription: "",
    productActualPrice : 0,
    productDiscountedPrice: 0,
    productImages:[]

  }

  constructor(private productService: ProductService, 
    private sanitizer: DomSanitizer, 
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder){
      

  }
  ngOnInit():void{
      this.product = this.activatedRoute.snapshot.data['product'];

      if(this.product && this.product.productId){
        this.isNewProduct = false;
      }
  }

  addProduct(productForm:NgForm){
    console.log("This is productForm : ",productForm);
    if (this.product.productDiscountedPrice >= this.product.productActualPrice) {
      alert("Discounted price must be less than the actual price.");
      return;
    }
    const productFormData = this.prepareFormData(this.product)
    this.productService.addProduct(productFormData).subscribe(
      (response: Product) => {
        productForm.reset();
        if(this.isNewProduct){
          alert("Added Product Successfully !!!");
        }else{
          alert("Product Updated Successfully !!!")
        }
        
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
      
    )
  }


  prepareFormData(product: Product): FormData{
    const formData = new FormData();
    formData.append(
      'product',
      new Blob([JSON.stringify(product)], {type:'application/json'})
    );
    for (let i = 0; i < product.productImages.length; i++) {
        formData.append(
          'imageFile',
          product.productImages[i].file,
          product.productImages[i].file.name
        );
    }
    return formData;
  }


  resetForm(productForm:NgForm){
        productForm.reset();     
  }

  onFileSelected(event){
    if(event.target.files){
        const files = event.target.files[0];

        const fileHandle : FileHandle = {
          file: files,
          url: this.sanitizer.bypassSecurityTrustUrl(
            window.URL.createObjectURL(files)
          )
        }
        this.product.productImages.push(fileHandle);
    } 
  }

  removeImage(i:number){
      this.product.productImages.splice(i,1);
  }

  fileDropped(fileHandle: FileHandle){
    this.product.productImages.push(fileHandle);
  }

}
