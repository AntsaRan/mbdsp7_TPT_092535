import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/shared/models/match.model';
import { CartService } from 'src/app/shared/services/cart.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent implements OnInit {
  date: string;
  currentdate = new Date();
  coupons : Match[]=[];
  constructor(private datePipe: DatePipe, private cartserv:CartService) {
    this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');

  }
  ngOnInit(): void {
  }

  get couponslist(){
    return this.cartserv.items;
  }
}
