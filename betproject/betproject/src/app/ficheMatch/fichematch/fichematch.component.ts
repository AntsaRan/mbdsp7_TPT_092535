import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Match } from 'src/app/shared/models/match.model';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-fichematch',
  templateUrl: './fichematch.component.html',
  styleUrls: ['./fichematch.component.css']
})
export class FichematchComponent implements OnInit {

  match: Match = null;
  idmatch: String;
  matchdate: Date;
  mnewDat: String;
  mnewTime: String;
  etat: String;

  constructor(
    private route: ActivatedRoute,
    private matchserv: MatchServiceService) { }

  ngOnInit(): void {
    this.getMatchById();
  }
  getMatchById() {
    this.idmatch = this.route.snapshot.params.id;
    console.log(this.idmatch + " ITO ILAY ID ANLAY macth");
    this.matchserv.getMatchById(this.idmatch)
      .subscribe(matchid => {
        this.match = matchid;
        switch (this.match.etat) {
          case "1":
            this.etat = "Non commencé"
            break;
          case "2":
            this.etat = "En cours"
            break;
          case "3":
            this.etat = "Terminé"
            break;
        }
        this.matchdate = new Date(matchid.date);
        this.mnewDat = this.matchdate.toLocaleDateString();
        this.mnewTime = this.matchdate.toLocaleTimeString();
      })
  }
}
