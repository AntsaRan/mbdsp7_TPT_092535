import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import * as L from 'leaflet';
import { cpuUsage } from 'process';
import { SnackbarokComponent } from 'src/app/views/coupons/snackbarok/snackbarok.component';
import { MailerService } from 'src/app/shared/services/mailer.service';
import { MapService } from 'src/app/shared/services/map.service';
import { Point } from 'src/app/shared/models/Point.model';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.css']
})
export class MapsComponent implements OnInit {

  private map: L.Map;
  private centroid: L.LatLngExpression = [-18.878594066916172, 47.552341677709514]; //boston
  email = "";;
  message = "";
  error = "";
  nom = "";
  durationInSeconds = 2;
  coords: Point[] = [];

  constructor(private mapserv:MapService, private mail: MailerService, private _snackBar: MatSnackBar) { }

  private initMap(): void {
    this.map = L.map('map', {
      center: this.centroid,
      zoom: 18
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 10,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    // create 5 random jitteries and add them to map


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
    this.mail.sendmail(this.message, this.nom, this.email)
      .subscribe(res => {
        if (res) {
          console.log("Ok");
          this.openSnackBar();
        }
      })
  }
  ngOnInit(): void {
    this.getpoints();
  }
  title = 'Contact';
  openSnackBar() {
    this._snackBar.openFromComponent(SnackbarokComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }
}
