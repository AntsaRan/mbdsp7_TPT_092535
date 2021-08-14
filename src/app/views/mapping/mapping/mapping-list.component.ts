import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { icon, Marker } from 'leaflet';
import { LeafletMouseEvent } from 'leaflet';
import { Point } from '../../../shared/model/points.model';
import { MappingService } from '../../../shared/services/mapping.service';

@Component({
  selector: 'app-mapping-list',
  templateUrl: './mapping-list.component.html',
  styleUrls: ['./mapping-list.component.scss']
})
export class MappingListComponent implements OnInit {


  map: L.Map;
  private centroid: L.LatLngExpression = [-18.878594066916172, 47.552341677709514];
  addresse = "";
  lat = "";
  long = "";
  coords: Point[] = [];
  loading: string = " Loading ..."
  constructor(private mapserv: MappingService) {

  }

  getpoints() {
    this.mapserv.getpoints()
      .subscribe(p => {
        p.forEach(point => {
          this.coords.push(point);
        })
        this.loading = "";
      })
  }

  ngOnInit(): void {
    this.getpoints();
  }
  delete(e, id) {
    if (confirm("Voulez vous vraiment supprimer ce point?")) {
      this.mapserv.delete(id)
        .subscribe(m => {
          if (m.msg == "ok") {
            window.location.reload();
          }else{
            
          }
        })
    }
  }

  title = 'Gestion des points de vente';

}
