import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Match } from 'src/app/shared/models/match.model';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-historique',
  templateUrl: './historique.component.html',
  styleUrls: ['./historique.component.css']
})
export class HistoriqueComponent implements OnInit {

  id=this.route.snapshot.params.id;
  match: Match[] = [];
  dataSource;
  matchdate: Date;
  mnewDat: String;
  mnewTime: String;
  image:String;

  constructor(private route: ActivatedRoute,private matchserv: MatchServiceService) { }

  ngOnInit(): void {
    this.getMatchsbyEquipe();
    this.getEquipebyId(this.id);
  }

  getMatchsbyEquipe() {
    this.matchserv.getMatches()
      .subscribe(matchs => {
        matchs.forEach(match=>{         
          console.log(match.id+ " match")
          this.match.push(match);
          this.dataSource = this.match;
          //this.matchdate = new Date(matchid.date);
        //  this.mnewDat = this.matchdate.toLocaleDateString();
         // this.mnewTime = this.matchdate.toLocaleTimeString();
        })
      });
  }
  getEquipebyId(id) {
    this.matchserv.getEquipebyId(id)
      .subscribe(equipe => {
        console.log(equipe.logo + " LOOOOOOOGO");
        this.image=equipe.logo;
      });
  }
  
  displayedColumns: string[] = ['equipe1', 'scoreEquipe1', 'equipe2','date'];

  

}
