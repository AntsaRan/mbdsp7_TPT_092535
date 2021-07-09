import { DatePipe } from '@angular/common';
import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Match } from 'src/app/shared/models/match.model';
import { MatchRegles } from 'src/app/shared/models/matchregles.model';
import { Reglematch } from 'src/app/shared/models/reglematch.model';
import { ReglesCotes } from 'src/app/shared/models/reglescotes.model';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatDialog } from '@angular/material/dialog';
import { AddcouponComponent } from 'src/app/addcoupon/addcoupon/addcoupon.component';
import { Pari } from 'src/app/shared/models/pari.model';
import { Parieur } from 'src/app/shared/models/parieur.model';
import { Coupon } from 'src/app/shared/models/coupon.model';

@Component({
  selector: 'app-fichematch',
  templateUrl: './fichematch.component.html',
  styleUrls: ['./fichematch.component.css']
})
export class FichematchComponent implements OnInit {

  match: Match = null;
  idmatch: string;
  matchdate: Date;
  mnewDat: string;
  mnewTime: string;
  etat: string;
  matchRegles: MatchRegles = null;
  rcs: Reglematch[] = [];
  rcc: Reglematch[] = [];
  rcp: Reglematch[] = [];
  mise: number = 0;
  loading: boolean = true;
  constructor(
    private route: ActivatedRoute, public dialog: MatDialog,
    private matchserv: MatchServiceService, private cartserv: CartService,) { }

  ngOnInit(): void {
    this.getMatchById();
    //this.getMatchRegles();

  }
  getMatchById() {
    this.idmatch = this.route.snapshot.params.id;
    console.log(this.idmatch + " ITO ILAY ID ANLAY macth");
    this.matchserv.getMatchById(this.idmatch)
      .subscribe(matchid => {
        this.match = matchid;
        switch (this.match.etat) {
          case "1":
            this.etat = "Non commencé"
            break;
          case "2":
            this.etat = "En cours"
            break;
          case "3":
            this.etat = "Terminé"
            break;
        }
        this.matchdate = new Date(matchid.date);
        this.mnewDat = this.matchdate.toLocaleDateString();
        this.mnewTime = this.matchdate.toLocaleTimeString();
        this.getMatchRegles();
      })

  }
  getMatchRegles() {
    console.log("GETMATCHREGLESFICHE");
    this.idmatch = this.route.snapshot.params.id;
    this.matchserv.getMatchRegles(this.idmatch)
      .subscribe(regles => {
        if (regles!=null) {
          this.matchRegles = regles;
          this.matchRegles.regles.forEach(r => {
            if (r.regle.titre == "Score") {
              console.log(" score titre")
              let rm = new Reglematch;
              rm.regles = r;
              rm.type = "Score";
              this.rcs.push(rm);
            }
            if (r.regle.titre == "Corner") {
              console.log(" corner titre")
              const rm = new Reglematch;
              rm.regles = r;
              rm.type = "Corner";
              this.rcc.push(rm);
            }
            if (r.regle.titre == "Possession") {
              console.log(" poss titre")
              const rm = new Reglematch;
              rm.regles = r;
              rm.type = "Possession";
              this.rcp.push(rm);
            }
          }
          )
          this.loading = false;
        }
      })
  }
  opencouponform(event) {
    const dialogRef = this.dialog.open(AddcouponComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        console.log("MISY RESULT")
        this.mise = result['mise'];
        let coupon: Coupon = new Coupon;
        coupon.idmatch = this.idmatch;
        coupon.mise = this.mise;
        coupon.idTypeRegle = event.regle.id;
        coupon.match = this.match;
        coupon.regle = event;
        console.log(coupon.mise + " MISE")
        this.cartserv.add(coupon);
      }
    });
  }
}
