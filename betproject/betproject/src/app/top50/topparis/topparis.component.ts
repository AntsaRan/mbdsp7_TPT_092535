import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/shared/models/match.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-topparis',
  templateUrl: './topparis.component.html',
  styleUrls: ['./topparis.component.css']
})
export class TopparisComponent implements OnInit {

  matches: any[]=[];
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
  }
  getMatches() {
    let incr=0;
    console.log("getmatches");
    this.matchserv.getTop5matchs()
      .subscribe((response) => {
        console.log(response + " DATA TOP 5");
        response.forEach(match=>{         
          console.log(match.id+ " match")
          incr++;
          var m={incr,match};
          this.matches.push(m); 
          console.log(m.match.date);
        })
        this.loading=false;
      },
      (error)=>{
        console.error('error caught in component '+ error);
        //window.location.reload();
      });
  }

}
