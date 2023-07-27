import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserStateService {
  private currentUsername: string = '';
  private usernameSubject = new Subject<string>();
  username$ = this.usernameSubject.asObservable();

  updateUsername(username: string) {
    this.currentUsername = username;
    this.usernameSubject.next(this.currentUsername);
  }

  clearUsername() {
    this.currentUsername = '';
    this.usernameSubject.next(this.currentUsername);
  }

  getUsername(): string {
    return this.currentUsername;
  }
}
