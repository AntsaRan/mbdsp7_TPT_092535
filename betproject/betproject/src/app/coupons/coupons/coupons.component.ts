import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AddcouponComponent } from 'src/app/addcoupon/addcoupon/addcoupon.component';
import { Coupon } from 'src/app/shared/models/coupon.model';
import { Match } from 'src/app/shared/models/match.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent implements OnInit {
  date: string;
  currentdate = new Date();
  //gain:number=0;
  
  constructor(private datePipe: DatePipe, private cartserv: CartService, private route: ActivatedRoute, public dialog: MatDialog,
    private matchserv: MatchServiceService) {
    this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');

  }
  ngOnInit(): void {
  }

  get couponslist() {
    return this.cartserv.items;
  }
  get gain() {
    let coupons=this.couponslist;
    let total:number=0;
    this.couponslist.forEach(c=>{
      total+=c.mise*c.regle.cote;
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

  remove(coupon:Coupon){
    this.cartserv.remove(coupon);
  }
}
