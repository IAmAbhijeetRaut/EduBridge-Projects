import { Component } from '@angular/core';
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import { UserService } from "../_services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
    constructor(private router:Router,
      private userService:UserService){

    }


    public register(registerForm:NgForm){
         this.userService.register(registerForm.value).subscribe(
          (response)=>{
            console.log(response);
            this.router.navigate(['/login'])
          }, (err)=>{
              console.log(err);
              
          }
         )
    }
}
