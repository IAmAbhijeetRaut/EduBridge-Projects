import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserService } from './../_services/user.service';
import { UserAuthService } from './../_services/user-auth.service';
import { UserStateService } from "../_services/user-state.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  currentLoggedInUser : string = '';

  constructor(private userAuthService: UserAuthService, 
    private router: Router, 
    public userService: UserService
    , private userStateService: UserStateService
    ){
      
  }

  ngOnInit(): void {
    this.userStateService.username$.subscribe((username) => {
      this.currentLoggedInUser = username;
    });
  }
  

   isLoggedIn(){
    return this.userAuthService.isLoggedIn();
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/']);
  }

  public isAdmin(): boolean{
    return this.userAuthService.isAdmin();
  }

  public isUser() : boolean{
    return this.userAuthService.isUser();
  }
}
