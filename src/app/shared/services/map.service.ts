import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Point } from '../models/Point.model';

@Injectable({
  providedIn: 'root'
})
export class MapService {
  
  uri = "https://apinode-mbds.herokuapp.com/point"

  constructor(private http: HttpClient) { }
  
  getpoints(): Observable<Point[]> {
    return this.http.get<Point[]>(this.uri + "/getpoints");
  }
}
