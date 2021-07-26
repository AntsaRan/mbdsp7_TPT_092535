import { Component, OnInit } from '@angular/core';
import { Const } from 'src/app/shared/const/const';
import { Jeton } from 'src/app/shared/models/jeton';
import { Parieur } from 'src/app/shared/models/parieur.model';
@Component({
  selector: 'app-achatvente',
  templateUrl: './achatvente.component.html',
  styleUrls: ['./achatvente.component.css']
})
export class AchatventeComponent implements OnInit {
  amontant: string = "";
  ajetons: string = "";
  user:Parieur=new Parieur();
  vjetons: string = "";
  error: string = "";
  prix: Number = Const.prixJeton;
  mise
  constructor() { }

  ELEMENT_DATA: Jeton[] = [
    { devise: "Ariary", prix: this.prix }
  ];
  displayedColumns: string[] = ['devise', 'prix'];
  dataSource = this.ELEMENT_DATA;
  ngOnInit(): void {
    this.user=JSON.parse(localStorage.getItem('user'));
    this.mise=localStorage.getItem('miseTotale');

  }
  changemontant() {
    console.log("montant: " + this.amontant + " prix: " + this.prix);

    let jetons = Number(this.amontant) / Number(this.prix);
    this.ajetons = jetons.toString();
  }
  changejetons() {

    let montant = Number(this.ajetons) * Number(this.prix);
    this.amontant = montant.toString();
  }

  onsubmitachat() {
    // vérifier que champs non vides
    if (this.ajetons == "" || this.amontant == "") {
      this.error = "Tous les champs doivent être renseignés";
    } else {
      if (Number(this.ajetons) < 0 || Number(this.amontant) < 0 || String(this.amontant).includes(".") || String(this.ajetons).includes(".")) {
        this.error = "Les valeurs doivent être des nombres entiers positifs"
      }else{
        this.error="";
        console.log( "OK");
      }
    }
    // vérifier que nombre de jetons entier
    // vérifier que montant positif
  }
}

