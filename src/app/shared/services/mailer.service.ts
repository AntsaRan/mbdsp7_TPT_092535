import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MailerService {
  // LIENS DE CONNEXION NODE : 
 // uri = "http://localhost:8010/mail";
  uri="https://apinode-mbds.herokuapp.com/mail" 

  constructor(private http: HttpClient) { }

  sendmail(message,nom,mail): Observable<any> {
    return this.http.post(this.uri + "/mail", {msg:message,nom:nom,mail:mail});
  }
  
  sendforgetmail(email):Observable<any>{
    return this.http.post(this.uri + "/mailforgotten", email);

  }

  forgetpass(mail):Observable<any>{
    console.log(mail + " MAIL")
    return this.http.post(this.uri+"/mdp",{mail});
  }

 
}
