import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AddcouponComponent } from 'src/app/addcoupon/addcoupon/addcoupon.component';
import { Coupon } from 'src/app/shared/models/coupon.model';
import { Match } from 'src/app/shared/models/match.model';
import { Pari } from 'src/app/shared/models/pari.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';
import { ParisService } from 'src/app/shared/services/paris.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackbarokComponent } from '../snackbarok/snackbarok.component';
import { Const } from 'src/app/shared/const/const';
@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent implements OnInit {
  date: string;
  currentdate = new Date();
  coupons: Coupon[] = [];
  paris: Pari[] = [];
  durationInSeconds = 2;
  prixjeton:any;

  //gain:number=0;

  constructor(private datePipe: DatePipe, private cartserv: CartService, private route: ActivatedRoute, public dialog: MatDialog,
    private parisserv: ParisService, private _snackBar: MatSnackBar) {
    this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');

  }
  ngOnInit(): void {
    this.prixjeton=Const.prixJeton;

  }

  get couponslist() {
    return this.cartserv.items;
  }
  get gain() {
    let coupons = this.couponslist;
    let total: number = 0;
    this.couponslist.forEach(c => {
      total += c.mise * c.regle.cote*this.prixjeton;
    })
    return total;
  }
  opencouponform(coupon: Coupon) {
    const dialogRef = this.dialog.open(AddcouponComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        console.log("MISY RESULT")
        coupon.mise = result['mise'];
      }
    });
  }
  parier() {
    this.coupons = this.cartserv.items;
    this.coupons.forEach(coupon => {
      let pari: Pari = new Pari();
      console.log(coupon.mise + "  coupon.mise")
      console.log(coupon.idmatch + "  coupon.idmatch")
      pari.idTypeRegle = coupon.idTypeRegle;
      pari.idMatch = coupon.idmatch;
      pari.idparieur = localStorage.getItem('currentUser');
      pari.mise = coupon.mise;
      console.log(pari.mise + "  pari.mise")
      this.parisserv.insertparis(pari)
        .subscribe(data => {
          if (data.insert = "ok") {
            this.openSnackBar();
            this.cartserv.remove(coupon);
          }
        })
    })
  }
  openSnackBar() {
    this._snackBar.openFromComponent(SnackbarokComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }

  remove(coupon: Coupon) {
    this.cartserv.remove(coupon);
  }
}
