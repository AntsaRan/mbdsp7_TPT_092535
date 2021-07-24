import { Component, OnInit } from '@angular/core';
import { Const } from 'src/app/shared/const/const';
import { Jeton } from 'src/app/shared/models/jeton';
@Component({
  selector: 'app-achatvente',
  templateUrl: './achatvente.component.html',
  styleUrls: ['./achatvente.component.css']
})
export class AchatventeComponent implements OnInit {
  amontant: string = "";
  ajetons: string = "";
  vjetons: string = "";
  error: string = "";
  prix: Number = Const.prixJeton;
  constructor() { }

  ELEMENT_DATA: Jeton[] = [
    { devise: "Ariary", prix: this.prix }
  ];
  displayedColumns: string[] = ['devise', 'prix'];
  dataSource = this.ELEMENT_DATA;
  ngOnInit(): void {
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
      if (Number(this.ajetons) < 0 || Number(this.amontant) < 0 || this.amontant.includes(".") || this.ajetons.includes(".")) {
        this.error = "Les valeurs doivent être des nombres entiers positifs"
      }else{
        console.log( "OK");
      }
    }
    // vérifier que nombre de jetons entier
    // vérifier que montant positif
  }
}

