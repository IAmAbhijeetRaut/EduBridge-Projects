import { Injectable } from '@angular/core';
import { UserStateService } from "./user-state.service";


@Injectable({
  providedIn: 'root'
})
export class UserAuthService {
/*Responsible for communicating with the local storage. Local storage consist of role and jwtToken*/
  
constructor(private userStateService: UserStateService) {
}

  public setRoles(roles:[]){
    localStorage.setItem("roles", JSON.stringify(roles));
  }

  public getRoles(): any {
    return JSON.parse(localStorage.getItem("roles"));
  }

  public setToken(jwtToken: string){
    localStorage.setItem("jwtToken", JSON.stringify(jwtToken));
  }

  public getToken(): string{
    return JSON.parse(localStorage.getItem("jwtToken"));
  } 

  public clear(){
    this.logout();
    localStorage.clear();
  }
  
  public isLoggedIn(){
    return this.getRoles() && this.getToken();
  }

  
  public isAdmin() : boolean {
    const roles : any[] = this.getRoles();
    return roles[0].roleName === 'ADMIN'

  }

  public isUser() : boolean {
    const roles : any[] = this.getRoles();
    return roles[0].roleName === 'USER'

  }

  public setUserName(userName : string){
    localStorage.setItem("username", JSON.stringify(userName));
  }

  public getUserName(): any {
    return JSON.parse(localStorage.getItem("username"));
  }

  login(username: string) {
    // Your login logic goes here...

    // After successful login, update the current username in the UserStateService
    this.userStateService.updateUsername(username);
  }

  logout() {
    // Your logout logic goes here...

    // Clear the current username from the UserStateService
    this.userStateService.clearUsername();
  }

}
