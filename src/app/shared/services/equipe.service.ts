import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Equipe } from '../models/equipe.model';
import { Match } from '../models/match.model';

@Injectable({
  providedIn: 'root'
})
export class EquipeService {
// LIENS DE CONNEXION NODE : 
//uri = "http://localhost:8010";
 uri = "https://apinode-mbds.herokuapp.com"

  constructor(private http: HttpClient) { }

  getEquipebyName(nom): Observable<Equipe[]> {
    return this.http.get<Equipe[]>(this.uri + "/equipenom/" + nom);
  }
  getEquipebyId(id): Observable<Equipe> {
    return this.http.get<Equipe>(this.uri + "/equipe/" + id);
  }

}
