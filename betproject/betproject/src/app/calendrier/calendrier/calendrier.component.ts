import { DatePipe } from '@angular/common';
import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Match } from 'src/app/shared/models/match.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-calendrier',
  templateUrl: './calendrier.component.html',
  styleUrls: ['./calendrier.component.css']
})
export class CalendrierComponent implements OnInit {
  match: Match[] = [];
  loading: boolean = true;
  date: Date = null;
  datereceived: Date = null;
  nodata:String;
  constructor(private datePipe: DatePipe,
    private cartserv: CartService,
    private matchserv: MatchServiceService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (this.route.snapshot.params.date != null) {
      this.datereceived = this.route.snapshot.params.date;
    }
  }
  getMatchByDate() {
    console.log("Getmatchbydate cal "+ this.date.toISOString())
    this.match=[];
    this.matchserv.getMatchByDate(this.date.toISOString())
      .subscribe(matches => {
        if(matches){
          matches.forEach(match => {
            console.log(match.date + " getby date")
            this.match.push(match);
          }) 
        }else{
         this.nodata= "Aucun match";
        }
        this.loading = false;
      })

  }
}
