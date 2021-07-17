import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
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
  private userLoggedIn = new Subject<boolean>();

  constructor(
    private http: HttpClient, private router: Router) {
    this.userLoggedIn.next(false);
    this.currentUserSubject = new BehaviorSubject<Parieur>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  uri = "http://localhost:8010/auth/";

  public get currentUserValue(): Parieur {
    return this.currentUserSubject.value;
  }

  logIn(mail: string, passowrd: string): Observable<any> {
    console.log("LOGIN O")
    let user = new Parieur();
    user.mail = mail;
    user.pwd = passowrd;
    console.log("user alefa " + user.mail)
    return this.http.post<any>(this.uri + "/login", user)
      .pipe(
        map(user => {
          if (user.user != null) {
            console.log(user.user.id + " user id");
            console.log(user.user.jetons + " user JETONS");
            console.log(user.token + "user token");
            this.setSession(user);
            this.setUserLoggedIn(true);
            return user.user;
          } else {
            return null;
          }
        }),
        catchError(this.handleError<any>('### catchError: login'))
      );
  }
  setUserLoggedIn(userLoggedIn: boolean) {
    this.userLoggedIn.next(userLoggedIn);
  }

  getUserLoggedIn(): Observable<boolean> {
    return this.userLoggedIn.asObservable();
  }
  private setSession(user) {
    localStorage.setItem('currentUser', user.user.id);
    localStorage.setItem('username', user.user.nom);
    localStorage.setItem('prenom', user.user.prenom);
    localStorage.setItem('mail', user.user.mail);
    localStorage.setItem('currentToken', user.token);
    localStorage.setItem('jetons', user.user.jetons);
    this.currentUserSubject.next(user.user);
  }

  private handleError<T>(operation: any, result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);
      console.log(operation + ' a échoué' + error.message);
      return of(result as T);
    }
  }

}

