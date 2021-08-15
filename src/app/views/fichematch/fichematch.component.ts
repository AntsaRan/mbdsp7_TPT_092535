import { DatePipe } from '@angular/common';
import { ThisReceiver } from '@angular/compiler';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Match } from 'src/app/shared/models/match.model';
import { MatchRegles } from 'src/app/shared/models/matchregles.model';
import { Reglematch } from 'src/app/shared/models/reglematch.model';
import { ReglesCotes } from 'src/app/shared/models/reglescotes.model';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatDialog } from '@angular/material/dialog';
import { AddcouponComponent } from 'src/app/views/addcoupon/addcoupon/addcoupon.component';
import { Pari } from 'src/app/shared/models/pari.model';
import { Parieur } from 'src/app/shared/models/parieur.model';
import { Coupon } from 'src/app/shared/models/coupon.model';
import { throwToolbarMixedModesError } from '@angular/material/toolbar';

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

  domel: string[] =
    ["isDisabledrcs0",
      "isDisabledrcs1",
      "isDisabledrcs2",
      "isDisabledrcp0",
      "isDisabledrcp1",
      "isDisabledrcp2",
      "isDisabledrcc0",
      "isDisabledrcc1",
      "isDisabledrcc2"]
  /*************/
  @ViewChild('myDOMElement', { static: true }) MyDOMElement: ElementRef;

  /* isDisabledrcs0 = false;
   isDisabledrcs1 = false;
   isDisabledrcs2 = false;
 
   isDisabledrcp0 = false;
   isDisabledrcp1 = false;
   isDisabledrcp2 = false;
 
   isDisabledrcc0 = false;
   isDisabledrcc1 = false;
   isDisabledrcc2 = false;*/

  constructor(
    private route: ActivatedRoute, public dialog: MatDialog,
    private matchserv: MatchServiceService, private cartserv: CartService,) {

  }

  ngOnInit(): void {
    this.idmatch = this.route.snapshot.params.id;
    //
    this.getMatchById();
    setTimeout(() => {
      console.log('works');
      /*if (this.match.etat != '3') {
        this.setdomel(this.domel);
      }*/
      this.setdomel(this.domel);
    }, 7000)

    //localStorage.clear();
    //}

    //this.getMatchRegles();

  }
  ngAfterViewInit() {


  }

  setdomel(tab: string[]) {
    this.domel.forEach(d => {
      let val = localStorage.getItem(d + this.idmatch);
      console.log(val + " val " + d + " d");
      if (val != null) {
        console.log(val + " valnotnull");
        (document.getElementById(d + this.idmatch) as HTMLButtonElement).disabled = JSON.parse(val);
        if (JSON.parse(val)) {
          console.log("TRUE " + d + this.idmatch);
          (document.getElementById(d + this.idmatch) as HTMLButtonElement).className = "disabled";
        } else {
          console.log("FALSE " + d + this.idmatch);
          (document.getElementById(d + this.idmatch) as HTMLButtonElement).className = "enabled";
        }
      } else {
        console.log(val + " val null"); 
        if ((document.getElementById(d + this.idmatch) as HTMLButtonElement)) {
          (document.getElementById(d + this.idmatch) as HTMLButtonElement).disabled = false;
          (document.getElementById(d + this.idmatch) as HTMLButtonElement).className = "enabled";
        }

      }
    })
  }
  getMatchById() {
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
        // this.setdomel(this.domel);
      })

  }
  getMatchRegles() {
    console.log("GETMATCHREGLESFICHE");
    this.matchserv.getMatchRegles(this.idmatch)
      .subscribe(regles => {
        if (regles != null) {
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
  opencouponform(event1, event2) {
    const dialogRef = this.dialog.open(AddcouponComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        console.log("MISY RESULT" + result)
        this.mise = result['mise'];
        let coupon: Coupon = new Coupon;
        coupon.idmatch = this.idmatch;
        coupon.mise = this.mise;
        coupon.idTypeRegle = event1.regle.id;
        coupon.match = this.match;
        coupon.regle = event1;
        coupon.respbtn = event2 + this.idmatch;
        console.log(coupon.respbtn + " respbtn")
        this.cartserv.add(coupon);
        console.log(event2 + this.idmatch + " EVENT E");
        //document.getElementById(event2+this.idmatch).style.backgroundColor = "red";
        (document.getElementById(event2 + this.idmatch) as HTMLButtonElement).className = "disabled";
        (document.getElementById(event2 + this.idmatch) as HTMLButtonElement).disabled = true;
        localStorage.setItem(event2 + this.idmatch, JSON.stringify(true));
        // localStorage.setItem(event2, "true");
        // this.compareeventvar(event2);
      }
    });
  }
  ablebtn(event) {
    console.log(event + " EMIT EVANT");
    localStorage.setItem(event, JSON.stringify(false));
    if (document.getElementById(event) as HTMLButtonElement != null) {
      (document.getElementById(event) as HTMLButtonElement).disabled = false;
      (document.getElementById(event) as HTMLButtonElement).className = "enabled";
    }
    console.log(localStorage.getItem(event) + " localStorage après ablebtn");
  }

}
