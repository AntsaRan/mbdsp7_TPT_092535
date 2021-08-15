import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/shared/models/match.model';
import { Pari } from 'src/app/shared/models/pari.model';
import { Parismatch } from 'src/app/shared/models/parismatch.model';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';
import { ParisService } from 'src/app/shared/services/paris.service';
import { InscriptionComponent } from 'src/app/views/inscription/inscription/inscription.component';
import { MatDialog } from '@angular/material/dialog';
import { Regles } from 'src/app/shared/models/regles.model';

@Component({
  selector: 'app-mesparis',
  templateUrl: './mesparis.component.html',
  styleUrls: ['./mesparis.component.css']
})
export class MesparisComponent implements OnInit {
  loading: boolean = true;
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
    let regle:string;
    this.parisserv.getPariByIdUser(id)
      .subscribe(data => {
        if (data!=null) {
          this.rearrangeDates(data);
          data.forEach(pari => {
            this.matchserv.getRegleById(pari.matchRegle)
            .subscribe(r => {
              if (r) {
               // console.log(r.definition);
                regle=r.definition;
              }
            })
            this.matchserv.getMatchById(pari.idMatch)
              .subscribe(match => {
                if (match) {
                  matchpari = match;
                  let parimatch = new Parismatch();
                  parimatch.id = pari.id;
                  parimatch.date = pari.dateParis;
                  parimatch.match = matchpari;
                  parimatch.mise = pari.mise;
                  parimatch.regle=regle;
         
                  this.paris.push(parimatch);
                }
              })
          })
          this.rearrangeDatesPM(this.paris);
          this.loading=false;
        } else {
          console.log("no daataaaa");
          this.loading=false;
          this.nodata = "No data";
        }

        this.loading = false;
      });
  }

  rearrangeDates(m: Pari[]) {
    m.sort((a: Pari, b: Pari) => {
      return new Date(a.dateParis).getTime() - new Date(b.dateParis).getTime();
    });
  }
  rearrangeDatesPM(m: Parismatch[]) {
    m.sort((a: Parismatch, b: Parismatch) => {
      return new Date(a.date).getTime() - new Date(b.date).getTime();
    });
  }
}
