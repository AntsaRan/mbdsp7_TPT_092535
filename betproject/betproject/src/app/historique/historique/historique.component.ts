import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Match } from 'src/app/shared/models/match.model';
import { EquipeService } from 'src/app/shared/services/equipe.service';
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
  nom:String;

  constructor(private route: ActivatedRoute,private matchserv: MatchServiceService,private equipeserv:EquipeService) { }

  ngOnInit(): void {
    this.getMatchsbyEquipe(this.id);
    this.getEquipebyId(this.id);
  }

  getMatchsbyEquipe(id) {
    this.matchserv.getMatchByEquipe(id)
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
    this.equipeserv.getEquipebyId(id)
      .subscribe(equipe => {
        console.log(equipe.logo + " LOOOOOOOGO");
        this.image=equipe.logo;
        this.nom=equipe.nom;
      });
  }
  
  displayedColumns: string[] = ['equipe1', 'scoreEquipe1', 'equipe2','date'];

  

}
