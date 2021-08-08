import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MatchRegles } from '../models/matchregles.model';
import { Match } from '../models/match.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Equipe } from '../models/equipe.model';
import { Regles } from '../models/regles.model';
import { Transaction } from '../models/transaction';

@Injectable({
  providedIn: 'root'
})
export class JetonsService {
// LIENS DE CONNEXION NODE : 
//uri = "http://localhost:8010/jetons";
uri="https://apinode-mbds.herokuapp.com/jetons" 
 
  constructor(private http: HttpClient) { }

  achatjeton(id:string,jetons:any): Observable<any> {
    console.log(jetons + " jetons achatserv")
    return this.http.put(this.uri + "/achatjeton",{id,jetons});
  }

  gethistouser(id:string):Observable<Transaction[]>{
    return this.http.get<Transaction[]>(this.uri+"/histojetons/"+id);
  }

}
