import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Match } from '../models/match.model';
import { HttpClient,HttpHeaders  } from '@angular/common/http';
import { Equipe } from '../models/equipe.model';

@Injectable({
  providedIn: 'root'
})
export class MatchServiceService {

  uri = "http://localhost:3000";

  constructor(private http:HttpClient) { }
  
  getMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(this.uri+"/getAllMatches");
  }
  getMatchById(id): Observable<Match> {
    return this.http.get<Match>(this.uri+"/match/"+id);
  }

  getMatchByEquipe(id):Observable<Match[]>{
    return this.http.get<Match[]>(this.uri+"/match/equipe"+id);
  }
  getEquipebyId(id):Observable<Equipe>{
    return this.http.get<Equipe>(this.uri+"/equipe/"+id);
  }

}
