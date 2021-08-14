import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Point } from '../model/points.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class MappingService {

    // LIENS DE CONNEXION NODE : 
  //uri = "http://localhost:8010/point";
  uri = "https://apinode-mbds.herokuapp.com/point"

  constructor(private http: HttpClient) { }

  insertpoint(p: Point): Observable<any> {
    return this.http.post(this.uri + "/insertpoint", p);
  }

  getpoints(): Observable<Point[]> {
    return this.http.get<Point[]>(this.uri + "/getpoints");
  }

  delete(id):Observable<any>{
    return this.http.delete(this.uri+"/deletepoint/"+id);
  }
}
