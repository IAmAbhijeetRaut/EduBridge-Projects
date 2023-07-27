import { Component } from '@angular/core';
import { UserService } from './../_services/user.service';
import { UserAuthService } from "../_services/user-auth.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  constructor(private userService : UserService, private userAuthService:UserAuthService){}
  message;
  
  ngOnInit():void{
    const token = this.userAuthService.getToken();
    this.forUser(token);
  }

  forUser(token){
     this.userService.forUser(token).subscribe(
      (response) => {
        console.log(response);
        this.message = response;
      },
      (error) => {
        console.log(error);
      }
    );

    console.log("Bearer with token : "+"Bearer "+this.userAuthService.getToken);
  }

}
