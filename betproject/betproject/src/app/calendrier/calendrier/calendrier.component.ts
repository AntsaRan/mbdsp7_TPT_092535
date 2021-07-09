import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/shared/services/cart.service';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-calendrier',
  templateUrl: './calendrier.component.html',
  styleUrls: ['./calendrier.component.css']
})
export class CalendrierComponent implements OnInit {

  loading: boolean = true;

  constructor(private datePipe: DatePipe,
    private cartserv: CartService,
    private matchserv: MatchServiceService) { }

  ngOnInit(): void {
  }
  getMatchByDate() {
  
    this.matchserv.getMatches()
      .subscribe(matches => {
        
      })

  }
}
