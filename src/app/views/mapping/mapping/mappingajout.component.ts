import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { icon, Marker } from 'leaflet';
import { LeafletMouseEvent } from 'leaflet';
import { Point } from '../../../shared/model/points.model';
import { MappingService } from '../../../shared/services/mapping.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-mappingajout',
  templateUrl: './mappingajout.component.html',
  styleUrls: ['./mappingajout.component.scss']
})
export class MappingajoutComponent implements OnInit {

  map: L.Map;
  private centroid: L.LatLngExpression = [-18.878594066916172, 47.552341677709514];
  addresse = "";
  lat = "";
  long = "";
  coords: Point[] = [];
  p: Point = new Point();
  error: string = "";
  success: boolean;
  constructor(private mapserv: MappingService) {
  }

  private initMap(): void {

    const iconRetinaUrl = '../assets/marker-icon-2x.png';
    const iconUrl = '../assets/marker-icon.png';
    const shadowUrl = '../assets/marker-shadow.png';
    const iconDefault = icon({
      iconRetinaUrl,
      iconUrl,
      shadowUrl,
      iconSize: [25, 41],
      iconAnchor: [12, 41],
      popupAnchor: [1, -34],
      tooltipAnchor: [16, -28],
      shadowSize: [41, 41]
    });
    Marker.prototype.options.icon = iconDefault;
    this.map = L.map('map', {
      center: this.centroid,
      zoom: 18
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 10,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });
    //create 5 random jitteries and add them to map


    if (this.coords.length > 0) {
      this.coords.forEach(point => {
        var lat: number = +point.lat;
        var lng: number = +point.lng;
        var mah: L.LatLngExpression = [lat, lng];
        L.marker(mah).bindPopup(point.adresse)
          .on('mouseover', function (e) {
            this.openPopup();
          }).on('mouseout', function (e) {
            this.closePopup();
          }).addTo(this.map);
      })
    }

    tiles.addTo(this.map);

    this.map.on('click', <LeafletMouseEvent>(e) => {
      var coord: L.LatLngExpression = e.latlng;
      console.log(e.latlng)
      L.marker(coord).bindPopup("Point de vente")
        .on('mouseover', function (e) {
          this.openPopup();
        }).on('mouseout', function (e) {
          this.closePopup();
        }).addTo(this.map);

      this.onclickHero(e.latlng.lat, e.latlng.lng);
    });

  }

  getpoints() {
    this.mapserv.getpoints()
      .subscribe(p => {
        if (p) {
          p.forEach(point => {
            this.coords.push(point);
          })
          this.initMap()
        } else {

          this.initMap();

        }
      })

  }

  onSubmit(event) {
    console.log("onsubmit");
    if (this.p.adresse == "" || this.p.lat == "" || this.p.lng == "") {
      console.log("vide");
      this.error = "Veuillez renseigner tous les champs";
    } else {
      console.log("tsy vide");
      this.mapserv.insertpoint(this.p)
        .subscribe(m => {
          if (m.insert.id) {
            this.success=true;
            console.log(" OK");
          } else {
            console.log(" NOT OK")
          }
        })
    }
  }

  onclickHero(lat, lng) {
    this.p.lat = lat;
    this.p.lng = lng;
  }
  newHero() {
    this.p.adresse = "";
    this.p.lat = "";
    this.p.lng = "";
  }
  ngOnInit(): void {
    this.success = false,
      this.getpoints();
    this.newHero();
  }


  title = 'Gestion des points de vente';

}
