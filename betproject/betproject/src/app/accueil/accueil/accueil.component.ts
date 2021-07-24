import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/shared/models/match.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {
  matches: Match[] = [];
  matchesdujour: Match[] = [];
  currentdate = new Date();
  date: string;
  loading: boolean = true;
  nodata:string;
  constructor(
    private datePipe: DatePipe,
    private cartserv: CartService,
    private matchserv: MatchServiceService) {

    this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    this.getMatches();
    this.getMatchesdujour();
    // this.cartserv.format();
  }

  getMatches() {
    console.log("getmatches");
    this.matchserv.getMatches()
      .subscribe(data => {
        data.forEach(match => {
          this.matches.push(match);

        })
        this.loading = false;
      });
  }
  getMatchesdujour() {
    console.log("getmatches");
    this.matchserv.getMatchByDate(this.date)
      .subscribe(data => {
        if (data) {
          console.log(" daataaaa");
          data.forEach(match => {
            this.matchesdujour.push(match);
          })
        }else{
          console.log("no daataaaa");
          this.nodata="No data";
        }

        this.loading = false;
      });
  }

}