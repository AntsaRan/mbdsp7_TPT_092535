import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Pari } from '../models/pari.model';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParisService {

  //uri = "http://localhost:8010/pari";
  uri="https://apinode-mbds.herokuapp.com/pari"

  constructor(private http: HttpClient) { }

  insertparis(pari: Pari): Observable<any> {
    console.log("insert pari service " + pari.idMatch);
    return this.http.post(this.uri + "/insertpari", pari);
  }

  getPariByIdUser(id): Observable<Pari[]> {
    return this.http.get<Pari[]>(this.uri + "/getparisuser/" + id);
  }
} 
