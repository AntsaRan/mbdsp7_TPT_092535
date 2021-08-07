import { Component, OnInit } from '@angular/core';
import { Equipe } from 'src/app/shared/models/equipe.model';
import { EquipeService } from 'src/app/shared/services/equipe.service';
import { MatchServiceService } from 'src/app/shared/services/match-service.service';

@Component({
  selector: 'app-historique-recherche',
  templateUrl: './historique-recherche.component.html',
  styleUrls: ['./historique-recherche.component.css']
})
export class HistoriqueRechercheComponent implements OnInit {
  equipes: Equipe[] = [];
  searchvalue = "";

  constructor(private matchserv: MatchServiceService,private equipeserv:EquipeService) { }

  ngOnInit(): void {
  }
  onSubmitsearch() {
    console.log(" SEARCH EACUIPE")
    this.equipeserv.getEquipebyName(this.searchvalue)
    .subscribe(data => {
      data.forEach(equipe=>{         
        console.log(equipe.id+ " match")
        this.equipes.push(equipe);
      })
    });
    //fonction chercher equipe by name
    
  }
}
