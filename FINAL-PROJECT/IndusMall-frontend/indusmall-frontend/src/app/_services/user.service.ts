import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';
import { Subject } from "rxjs";

@Injectable()
export class UserService {
  PATH_OF_API = 'http://localhost:9090';

  private userNameSubject = new Subject<string>();
  userName$ = this.userNameSubject.asObservable();

  updateUserName(userName: string) {
    this.userNameSubject.next(userName);
  }

  
  getCurrentUserName(): string {
    return JSON.parse(localStorage.getItem("username"));
  }

  
  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService
  ) {}
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  /* requestHeader = {
    "content-type": "application/json",
    "Authorization": `Bearer ${this.userAuthService.getToken()}`,

  } */

  public login(loginData) {
    return this.httpclient.post(this.PATH_OF_API + '/authenticate', loginData, {
      headers: this.requestHeader,
    });
  }

  public register(registerData){
      return this.httpclient.post(this.PATH_OF_API+'/registerNewUser', registerData);
  }

  /* public forUser() {
    const token = this.userAuthService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.httpclient.get(this.PATH_OF_API + '/forUser', {
      headers: headers,
      responseType: 'text',
    });
  } */

  public forUser(token) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.httpclient.get(this.PATH_OF_API + '/forUser', {
      headers : headers,
      responseType: 'text',
    });
  }


  public forAdmin() {
    return this.httpclient.get(this.PATH_OF_API + '/forAdmin', {
      responseType: 'text',
    });
  }

  public roleMatch(allowedRoles): boolean | any {
    let isMatch = false;
    const userRoles: any = this.userAuthService.getRoles();
    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i].roleName === allowedRoles[j]) {
            isMatch = true;
            return isMatch;
          } else {
            return isMatch;
          }
        }
      }
    }
  }
}


