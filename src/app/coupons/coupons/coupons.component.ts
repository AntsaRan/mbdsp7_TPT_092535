import { DatePipe } from '@angular/common';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
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
import { LoginComponent } from 'src/app/login/login/login.component';
import { AuthService } from 'src/app/shared/services/auth.service';
import { CheckmiseComponent } from '../checkjetons/checkmise/checkmise.component';
@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent implements OnInit {
  @Output() mise = new EventEmitter<string>();

  date: string;
  currentdate = new Date();
  coupons: Coupon[] = [];
  paris: Pari[] = [];
  durationInSeconds = 2;
  prixjeton: any;
  peutparier: boolean = false;
  //gain:number=0;

  constructor(private router: Router, private datePipe: DatePipe, private cartserv: CartService, private route: ActivatedRoute, public dialog: MatDialog,
    private parisserv: ParisService, private _snackBar: MatSnackBar, private auth: AuthService) {
    this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');

  }
  ngOnInit(): void {
    this.prixjeton = Const.prixJeton;

  }

  get couponslist() {
    return this.cartserv.items;
  }
  get gain() {
    let coupons = this.couponslist;
    let total: number = 0;
    this.couponslist.forEach(c => {
      total += c.mise * c.regle.cote * this.prixjeton;
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

  checkuserjetons(id, mise: Number): Number {
    let jetons;

    return jetons
  }

  parier() {
    if (localStorage.getItem('currentUser')) {
      let id = localStorage.getItem('currentUser');
      this.coupons = this.cartserv.items;
      this.coupons.forEach(coupon => {
        let pari: Pari = new Pari();
        pari.matchRegle = coupon.idTypeRegle;
        pari.idMatch = coupon.idmatch;
        pari.idparieur = id;
        pari.mise = coupon.mise;
        console.log(pari.mise + "  pari.mise")
        this.auth.getUserByID(id)
          .subscribe(u => {
            if (u.jetons >= coupon.mise) {
              console.log(" inserer pari ok ")
              this.parisserv.insertparis(pari)
                .subscribe(data => {
                  if (data.insert = "ok") {
                    this.openSnackBar();
                    this.cartserv.remove(coupon);
                  }
                })
            } else {
              this.openSnackBarerror();
            }
          })

      })
    } else {
      this.openLoginDialog();
    }
  }
  openSnackBar() {
    this._snackBar.openFromComponent(SnackbarokComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }
  openSnackBarerror() {
    this._snackBar.openFromComponent(CheckmiseComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }

  remove(coupon: Coupon) {
    this.cartserv.remove(coupon);
    console.log(coupon.respbtn);
    this.mise.emit(coupon.respbtn);
  }
  openLoginDialog() {
    const dialogRef = this.dialog.open(LoginComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
