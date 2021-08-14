import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import * as moment from 'moment';
import { User } from '../model/user';
import { resolve } from '@angular/compiler-cli/src/ngtsc/file_system';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // LIENS DE CONNEXION NODE : 

  uri = "http://localhost:8010/auth";
  //uriPari = "http://localhost:8010/pari";

  //uri = "https://apinode-mbds.herokuapp.com/auth"
  uriPari = "https://apinode-mbds.herokuapp.com/pari";

  miseTotale: Number = 0;
  loggedIn: boolean = false;
  admin = false;
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  private userLoggedIn = new Subject<boolean>();

  constructor(
    private http: HttpClient, private router: Router
  ) {
    this.userLoggedIn.next(false);

  }


  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  logIn(pseudo: string, passowrd: string): Observable<any> {
    console.log("LOGIN O")
    let user = new User();
    user.pseudo = pseudo;
    user.password = passowrd;

    console.log("user alefa " + user.pseudo)
    return this.http.post<any>(this.uri + "/loginadmin", user)
      .pipe(
        map(user => {
          this.loggedIn = true;
          console.log(JSON.stringify(user) + " user ");
          if (user.user != null) {
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

  isloggedin() {
    const isUserAdmin = new Promise(
      (resolve, reject) => {
        resolve(this.loggedIn)
      }
    );
    return isUserAdmin;
  }

  getUserLoggedIn(): Observable<boolean> {
    return this.userLoggedIn.asObservable();
  }
  private setSession(user) {
    const expiresAt = moment().add(user.expiresIn, 'second');
    console.log(user.user.id);
    localStorage.setItem('currentUser', user.user.id);
    localStorage.setItem('user', JSON.stringify(user.user));
    console.log(localStorage.getItem('currentUser') + " localStorage.getItem('currentUser')")
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
    this.currentUserSubject.next(user.user);
  }

  getExpiration() {
    const expiration = localStorage.getItem("expires_at");
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);
  }
  private handleError<T>(operation: any, result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);
      console.log(operation + ' a échoué' + error.message);
      return of(result as T);
    }
  }

}

