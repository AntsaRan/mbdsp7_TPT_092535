import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Parieur } from '../models/parieur.model';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn: boolean = false;
  admin = false;
  private currentUserSubject: BehaviorSubject<Parieur>;
  public currentUser: Observable<Parieur>;

  constructor(
    private http: HttpClient, private router: Router) {
    this.currentUserSubject = new BehaviorSubject<Parieur>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }


  uri = "http://localhost:8010/api/auth/";

  public get currentUserValue(): Parieur {
    return this.currentUserSubject.value;
  }

  logIn(pseudo: string, passowrd: string) {
    let user = new Parieur();
    user.pseudo = pseudo;
    user.pwd = passowrd;
    return this.http.post<any>(this.uri + "login", user)
      .pipe(
        map(user => {
          if (user) {
            console.log(user.token + "user token");
            this.setSession(user);
            return user;
          }
        }),
        catchError(this.handleError<any>('### catchError: login'))
      );
  }
  private setSession(user) {
    localStorage.setItem('currentUser', JSON.stringify(user.id));
    localStorage.setItem('username', user.username);
    localStorage.setItem('currentToken', user.token);
    localStorage.setItem('isadmin', user.isadmin);
    this.currentUserSubject.next(user);
  }
  private handleError<T>(operation: any, result?: T) {
    console.log("HANDLE ERROR");
    return (error: any): Observable<T> => {
      console.log(error);
      console.log(operation + ' a échoué' + error.message);
      return of(result as T);
    }
  }
}

