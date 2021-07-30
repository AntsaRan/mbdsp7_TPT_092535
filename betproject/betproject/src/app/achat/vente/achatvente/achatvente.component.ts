import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Const } from 'src/app/shared/const/const';
import { Jeton } from 'src/app/shared/models/jeton';
import { Parieur } from 'src/app/shared/models/parieur.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { JetonsService } from 'src/app/shared/services/jetons.service';
@Component({
  selector: 'app-achatvente',
  templateUrl: './achatvente.component.html',
  styleUrls: ['./achatvente.component.css']
})
export class AchatventeComponent implements OnInit {
  amontant: string = "";
  ajetons: string = "";
  user: Parieur = new Parieur();
  vjetons: string = "";
  error: string = "";
  errorvente:string="";
  prix: Number = Const.prixJeton;
  mise;
  solde;
  constructor(private auth: AuthService, private jeton:JetonsService,
    private router: Router, public dialog: MatDialog) { }

  ELEMENT_DATA: Jeton[] = [
    { devise: "Ariary", prix: this.prix }
  ];
  displayedColumns: string[] = ['devise', 'prix'];
  dataSource = this.ELEMENT_DATA;

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.mise = localStorage.getItem('miseUser');
    this.auth.getUserByID(this.user.id)
      .subscribe(u => {
        this.solde=u.jetons;
      })
      this.auth.getUserMise(this.user.id)
      .subscribe(mise => {
        this.mise = mise.toString();
      })
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
      return;
    } else {
      if (Number(this.ajetons) < 0 || Number(this.amontant) < 0 || String(this.amontant).includes(".") || String(this.ajetons).includes(".")) {
        this.error = "Les valeurs doivent être des nombres entiers positifs"
        return;
      } else {
        this.jeton.achatjeton(this.user.id,this.ajetons)
        .subscribe(m=>{
          console.log("OK "+ m);
          window.location.reload();
        })
      }
    }
    // vérifier que nombre de jetons entier
    // vérifier que montant positif
  }
}

