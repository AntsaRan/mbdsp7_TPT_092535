import { Injectable } from '@angular/core';
import { AngularFireMessaging } from '@angular/fire/messaging';
import { BehaviorSubject, Observable } from 'rxjs'
import { mergeMap } from 'rxjs/operators';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MessagingService {
  public currentMessage:BehaviorSubject<any> = new BehaviorSubject(null);
  public msg$: Observable<any[]> = this.currentMessage.asObservable();

  constructor(private angularFireMessaging: AngularFireMessaging, private auth: AuthService) {
  }
  requestPermission() {
    let user = localStorage.getItem('currentUser');
    this.angularFireMessaging.requestToken.subscribe(
      (token) => {
        console.log('Permission granted! Save to the server!', token);
        this.auth.fireauth(user,token)
        .subscribe(m=>{
          console.log(m.msg + " MSG FIRE");
        })
      },
      (error) => { console.error(error); },
    );
  }
  receiveMessage() {
    this.angularFireMessaging.messages.subscribe(
      (payload) => {
        var msg:any = payload;
        console.log(msg+ " MSG");
        this.currentMessage.next(msg);
      })
  }
  
/*  sendnotif(title:string,body:string,incr:Number) {
    if(title=="Success"){
      console.log("NETY NOTIF "+ body);
      localStorage.setItem('notif',JSON.stringify(1));
    }
    else {
      console.log("NETY NOTIF FA TSY AIKO "+ title);
    }
  }*/
  deleteToken() {
    this.angularFireMessaging.getToken
      .pipe(mergeMap(token => this.angularFireMessaging.deleteToken(token)))
      .subscribe(
        (token) => { console.log('Token deleted!'); },
      );
  }
  listen() {
    this.angularFireMessaging.messages
      .subscribe((message) => { console.log(message); });
  }

}