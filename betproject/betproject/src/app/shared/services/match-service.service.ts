import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MatchRegles } from '../models/matchregles.model';
import { Match } from '../models/match.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Equipe } from '../models/equipe.model';
import { Regles } from '../models/regles.model';

@Injectable({
  providedIn: 'root'
})
export class MatchServiceService {

  //uri = "http://localhost:8010";
   uri="https://apinode-mbds.herokuapp.com"

  constructor(private http: HttpClient) { }

  getMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(this.uri + "/getAllMatches");
  }
  getMatchById(id): Observable<Match> {
    return this.http.get<Match>(this.uri + "/match/" + id);
  }

  getRegleById(id): Observable<Regles> {
    return this.http.get<Regles>(this.uri + "/regle/" + id);
  }


  getMatchByEquipe(id): Observable<Match[]> {
    return this.http.get<Match[]>(this.uri + "/match/equipe/" + id);
  }

  getMatchRegles(id): Observable<MatchRegles> {
    return this.http.get<MatchRegles>(this.uri + "/matchregles/" + id);
  }

  getMatchByDate(date): Observable<Match[]> {
    return this.http.get<Match[]>(this.uri + "/match/date/" + date);
  }

  getMatchBymaxParis(date: Date): Observable<Match[]> {
    return this.http.get<Match[]>(this.uri + "/match/paris/" + date);
  }
}
