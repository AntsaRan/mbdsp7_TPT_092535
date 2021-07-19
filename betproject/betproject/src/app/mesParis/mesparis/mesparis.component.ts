import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/shared/models/match.model';
import { Pari } from 'src/app/shared/models/pari.model';
import { Parismatch } from 'src/app/shared/models/parismatch.model';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';
import { ParisService } from 'src/app/shared/services/paris.service';
import { InscriptionComponent } from 'src/app/inscription/inscription/inscription.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-mesparis',
  templateUrl: './mesparis.component.html',
  styleUrls: ['./mesparis.component.css']
})
export class MesparisComponent implements OnInit {
  loading: boolean = false;
  paris: Parismatch[] = [];
  nodata: string;
  constructor(public dialog: MatDialog,private parisserv: ParisService, private matchserv: MatchServiceService) { }

  ngOnInit(): void {
    if(localStorage.getItem('currentUser')){
      console.log(localStorage.getItem('currentUser'));
      this.getParisbyuser(localStorage.getItem('currentUser'));
    }
  }
  getParisbyuser(id) {
    let matchpari: Match;
    this.parisserv.getPariByIdUser(id)
      .subscribe(data => {
        if (data) {
          console.log(" daataaaa");
          data.forEach(pari => {
            console.log(pari.idMatch + " MISEEEEE")
            this.matchserv.getMatchById(pari.idMatch)
              .subscribe(match => {
                if (match) {
                  console.log(match.date);
                  matchpari = match;
                  let parimatch = new Parismatch();
                  parimatch.id = pari.id;
                  parimatch.date = pari.dateParis;
                  parimatch.match = matchpari;
                  parimatch.mise = pari.mise;
                  this.paris.push(parimatch);
                }

              })
          })
        } else {
          console.log("no daataaaa");
          this.nodata = "No data";
        }

        this.loading = false;
      });
  }

}
