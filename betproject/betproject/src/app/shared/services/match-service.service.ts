import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Match } from '../models/match.model';
import { HttpClient,HttpHeaders  } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MatchServiceService {

  uri = "http://localhost:3000/getAllMatches";

  constructor(private http:HttpClient) { }
  
  getMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(this.uri);
  }
}
