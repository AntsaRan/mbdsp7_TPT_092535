import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { MatchRegles } from '../models/matchregles.model';
import { Match } from '../models/match.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Equipe } from '../models/equipe.model';
import { Regles } from '../models/regles.model';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MatchServiceService {
  // LIENS DE CONNEXION NODE : 
  //uri = "http://localhost:8010";
  uri="https://apinode-mbds.herokuapp.com"

  constructor(private http: HttpClient) { }

  getTop5matchs(): Observable<Match[]> {
    return this.http.get<Match[]>(this.uri + "/top5matchs")
      .pipe(
        catchError((err) => {
          console.log('error caught in service')
          console.error(JSON.stringify(err));
          return throwError(err);    //Rethrow it back to component
        })
      )
  }

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
