import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pari } from '../model/pari';

@Injectable({
  providedIn: 'root'
})
export class StatService {

 
    // LIENS DE CONNEXION NODE : 
    uri = "http://localhost:8010/stat";
    //uri = "https://apinode-mbds.herokuapp.com/point"
  
    constructor(private http: HttpClient) { }
  
    getAchats(): Observable<number> {
      return this.http.get<number>(this.uri + "/getachat");
    }
  
    getVentes(): Observable<number> {
      return this.http.get<number>(this.uri + "/getvente");
    }
    getParisMonth(): Observable<Pari[]> {
      return this.http.get<Pari[]>(this.uri + "/getParisMonth");
    }

}
