import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { Parieur } from '../models/parieur.model';
import { catchError, map } from 'rxjs/operators';
import { MessagingService } from './messaging.service';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // LIENS DE CONNEXION NODE : 

  //uri = "http://localhost:8010/auth";
  //uriPari = "http://localhost:8010/pari";

  uri = "https://apinode-mbds.herokuapp.com/auth"
  uriPari = "https://apinode-mbds.herokuapp.com/pari";

  miseTotale: Number = 0;
  loggedIn: boolean = false;
  admin = false;
  private currentUserSubject: BehaviorSubject<Parieur>;
  public currentUser: Observable<Parieur>;
  private userLoggedIn = new Subject<boolean>();

  constructor(
    private http: HttpClient, private router: Router
  ) {
    this.userLoggedIn.next(false);

  }


  public get currentUserValue(): Parieur {
    return this.currentUserSubject.value;
  }

  getUserByID(id): Observable<Parieur> {
    return this.http.get<Parieur>(this.uri + "/user/" + id);
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

  getUserLoggedIn(): Observable<boolean> {
    return this.userLoggedIn.asObservable();
  }

  getUserMise(id): Observable<Number> {
    console.log("user user mise " + id);
    return this.http.get<Number>(this.uriPari + "/getAllMise/" + id);
  }
  private setSession(user) {

    const expiresAt = moment().add(user.expiresIn, 'second');
    localStorage.setItem('currentUser', user.user.id);
    localStorage.setItem('username', user.user.nom);
    localStorage.setItem('prenom', user.user.prenom);
    localStorage.setItem('mail', user.user.mail);
    localStorage.setItem('currentToken', user.token);
    localStorage.setItem('jetons', user.user.jetons);
    localStorage.setItem('datenaiss', user.user.dateNaissance);
    localStorage.setItem('pseudo', user.user.pseudo);
    localStorage.setItem('user', JSON.stringify(user.user));
    localStorage.setItem('miseTotale', JSON.stringify(this.miseTotale));
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()));
    console.log(localStorage.getItem('currentUser') + " localStorage.getItem('currentUser')")
    this.currentUserSubject = new BehaviorSubject<Parieur>(JSON.parse(localStorage.getItem('currentUser')));
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

  inscription(parieur: Parieur, date) {
    console.log("inscription auth")
    var body = { parieur, date }
    return this.http.post<any>(this.uri + "/sign", body)
      .pipe(
        map(user => {
          if (user) {
            console.log(" nety inscri");
          } else {
            return null;
          }
        }),
        catchError(this.handleError<any>('### catchError: login'))
      );
  }
  resetpass(mdp, id): Observable<any> {
    return this.http.put(this.uri + "/resetmdp", { mdp, id });
  }

  fireauth(iduser: string, token: string) {
    console.log("inscription auth");
    var body = { iduser, token }
    return this.http.post<any>(this.uri + "/firetoken", body)
      .pipe(
        map(m => {
          if (m) {
            return m;
          } else {
            return null;
          }
        }),
        catchError(this.handleError<any>('### catchError: login'))
      );
  }
  updateUser(user): Observable<any> {
    return this.http.put<any>(this.uri + "/updateuser", user);
  }
}

