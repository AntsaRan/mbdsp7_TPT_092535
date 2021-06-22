import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/shared/models/match.model';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {

  currentdate = new Date();
  date: string;
  constructor(private datePipe: DatePipe) {
    this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');
  }

  ngOnInit(): void {
  }
  match1: Match = {
    _id: "1",
    idequipe1: "equipe 1",
    idequipe2: "equipe 2",
    date: new Date("21/06/21"),
    lieu: "Paris",
    etat: "terminé",
    scoreeq1: 2,
    scoreeq2: 0,
    idMatchRegle: "1"
  }
  match2: Match = {
    _id: "2",
    idequipe1: "equipe 2",
    idequipe2: "equipe 3",
    date: new Date("21/06/21"),
    lieu: "Paris",
    etat: "terminé",
    scoreeq1: 2,
    scoreeq2: 0,
    idMatchRegle: "1"
  }
  match3: Match = {
    _id: "3",
    idequipe1: "equipe 1",
    idequipe2: "equipe 3",
    date: new Date("21/06/21"),
    lieu: "Paris",
    etat: "terminé",
    scoreeq1: 2,
    scoreeq2: 0,
    idMatchRegle: "1"
  }
  match4: Match = {
    _id: "4",
    idequipe1: "equipe 3",
    idequipe2: "equipe 4",
    date: new Date("21/06/21"),
    lieu: "Paris",
    etat: "terminé",
    scoreeq1: 2,
    scoreeq2: 0,
    idMatchRegle: "1"
  }
  matchs: Match[] = [this.match1,this.match2,this.match3,this.match4,this.match4,
    this.match4,this.match4,this.match4,this.match4,this.match4,this.match4];

    ajaxtest(){
      
    }
}