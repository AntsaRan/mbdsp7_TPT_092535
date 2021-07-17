import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/shared/models/match.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-listematchs',
  templateUrl: './listematchs.component.html',
  styleUrls: ['./listematchs.component.css']
})
export class ListematchsComponent implements OnInit {

  matches: Match[]=[];
  currentdate = new Date();
  date: string;
  loading:boolean=true;
  constructor(
    private datePipe: DatePipe,
    private cartserv: CartService,
    private matchserv: MatchServiceService) {

    this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    this.getMatches();
    this.cartserv.format();
  }

  getMatches() {
    console.log("getmatches");
    this.matchserv.getMatches()
      .subscribe(data => {
        data.forEach(match=>{         
          console.log(match.id+ " match")
          this.matches.push(match);

        })
        this.loading=false;
      });
  }
}
