import {MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-show-product-images-dialog',
  templateUrl: './show-product-images-dialog.component.html',
  styleUrls: ['./show-product-images-dialog.component.css']
})
export class ShowProductImagesDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(){
    this.recieveImages();
  }

  recieveImages(){
    console.log(this.data);
  }
}
