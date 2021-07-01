import { Component, OnInit } from '@angular/core';
import { Equipe } from 'src/app/shared/models/equipe.model';

@Component({
  selector: 'app-historique-recherche',
  templateUrl: './historique-recherche.component.html',
  styleUrls: ['./historique-recherche.component.css']
})
export class HistoriqueRechercheComponent implements OnInit {
  equipes: String[] = [];
  searchvalue = "";

  constructor() { }

  ngOnInit(): void {
  }
  onSubmitsearch(event) {
    this.equipes.push(this.searchvalue);
    //fonction chercher equipe by name
    
  }
}
