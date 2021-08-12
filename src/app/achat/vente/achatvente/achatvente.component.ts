import { ThrowStmt } from '@angular/compiler';
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
  errorvente: string = "";
  prix: Number;
  mise;
  solde;
  ELEMENT_DATA: Jeton[] = [];
  constructor(private auth: AuthService, private jeton: JetonsService,
    private router: Router, public dialog: MatDialog) {
    
  }



  displayedColumns: string[] = ['devise', 'prix'];
  dataSource ;
  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.mise = localStorage.getItem('miseUser');
    this.jeton.getprixjetons()
      .subscribe(j => {
        console.log(j + " J")
        this.prix = j;
        this.ELEMENT_DATA = [
          { devise: "Ariary", prix: this.prix }
        ];
        this.dataSource=this.ELEMENT_DATA;
      })
    this.auth.getUserByID(this.user.id)
      .subscribe(u => {
        this.solde = u.jetons;
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
    if (confirm("Confirmez votre achat ")) {
      if (this.ajetons == "" || this.amontant == "") {
        this.error = "Tous les champs doivent être renseignés";
        return;
      } else {
        if (Number(this.ajetons) < 0 || Number(this.amontant) < 0 || String(this.amontant).includes(".") || String(this.ajetons).includes(".")) {
          this.error = "Les valeurs doivent être des nombres entiers positifs"
          return;
        } else {
          this.jeton.achatjeton(this.user.id, this.ajetons, this.amontant)
            .subscribe(m => {
              console.log(m)
              if (m == "SI") {
                this.error = "Solde insuffisant";
              } else if (m.msg == "error") {
                this.error = "Une erreur s'est produite, veuillez recommencer";
              } else {
                window.location.reload();
              }

            })
        }
      }
    }
    // vérifier que nombre de jetons entier
    // vérifier que montant positif
  }
}

