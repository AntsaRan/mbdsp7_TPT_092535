import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import * as L from 'leaflet';
import { cpuUsage } from 'process';
import { SnackbarokComponent } from 'src/app/coupons/snackbarok/snackbarok.component';
import { MailerService } from 'src/app/shared/services/mailer.service';

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

  constructor(private mail: MailerService, private _snackBar: MatSnackBar) { }

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

    const jittery = Array(4).fill(this.centroid).map(
      x => [x[0] + (Math.random() - .5) / 10, x[1] + (Math.random() - .5) / 10]
    ).map(
      x => L.marker(x as L.LatLngExpression)
    ).forEach(
      x => x.addTo(this.map)
    );
    var mah: L.LatLngExpression = [-18.878594066916172, 47.552341677709514];
    L.marker(mah).bindPopup("Chez Maharo")
      .on('mouseover', function (e) {
        this.openPopup();
      }).on('mouseout', function (e) {
        this.closePopup();
      }).addTo(this.map);

    tiles.addTo(this.map);

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
    this.initMap();
  }
  title = 'Contact';
  openSnackBar() {
    this._snackBar.openFromComponent(SnackbarokComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }
}
