import { Component, OnInit } from '@angular/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  nom = "";
  prenom = "";
  Datedenaissance = null;
  pays="";
  mail="";
  mdp="";
  confirmmdp="";
  phone="";
  error = "";
  hide = true;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  onSubmit(event) {

  }
 
}
