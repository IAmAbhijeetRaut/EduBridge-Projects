import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from './../_services/user.service';
import { UserAuthService } from "../_services/user-auth.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  

  constructor(
    private userService:UserService, 
    private userAuthService:UserAuthService,
    private router: Router ){

  }
  login(loginForm:NgForm){
      this.userService.login(loginForm.value).subscribe(
        (response:any)=>{
          console.log("This is the response object -->",response);
          this.userAuthService.login(response.user.userName);
          this.userAuthService.setRoles(response.user.role);
          this.userAuthService.setToken(response.jwtToken);

          const role = response.user.role[0].roleName;
          if(role === 'ADMIN'){
            alert("LOGGED IN AS ADMIN")
              this.router.navigate(['/addNewProduct']);
          }else{
            this.router.navigate(['']);
          }
        },
        (error) => {
          console.log(error);
        }
      ) 
  }

  public registerUser(){  
         this.router.navigate(['/register']) 
  }

 
}
