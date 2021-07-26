import { DatePipe } from '@angular/common';
import { ReturnStatement, ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackbarokComponent } from 'src/app/coupons/snackbarok/snackbarok.component';
import { Parieur } from 'src/app/shared/models/parieur.model';
import { AuthService } from 'src/app/shared/services/auth.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  nom = "";
  prenom = "";
  Datedenaissance :Date=null;
  pseudo = "";
  mail = "";
  mdp = "";
  confirmmdp = "";
  error = "";
  hide = true;
  currentdate = new Date();
  date: string;
  parieur: Parieur = new Parieur();
  durationInSeconds: number=2;
  constructor(public dialog: MatDialog, private datePipe: DatePipe,private authserv : AuthService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    //this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');
    this.currentdate = new Date();
  }

  onSubmit(event) {

    //check champs
    if (this.nom == ""
      || this.Datedenaissance == null
      || this.prenom == ""
      || this.mail == ""
      || this.mdp == ""
      || this.pseudo == "") {
        console.log (
          this.nom 
          + " "+this.Datedenaissance
          + " "+this.prenom
          +" "+this.mail
          +" "+this.pseudo
          +" "+this.mdp)
      this.error = " Tous les champs sont obligatoires";
      return;
    }
    //check age
    if (this.checkMajority() < 18) {
      this.error = "Vous devez être majeur pour vous inscrire";
      return;
    }
    //check mdp 
    if (this.mdp !== this.confirmmdp) {
      this.error = " Les mots de passes de coïncident pas";
      return;
    }
    this.parieur.dateNaissance=this.Datedenaissance;
    this.parieur.nom=this.nom;
    this.parieur.prenom=this.prenom;
    this.parieur.pseudo=this.pseudo;
    this.parieur.pwd=this.mdp;
    this.parieur.mail=this.mail;
    this.authserv.inscription(this.parieur, this.datePipe.transform(this.Datedenaissance, 'yyyy-MM-dd'))
    .subscribe(data => {
      console.log(data)
    });
  }

  checkMajority(): Number {
    console.log(this.Datedenaissance + " datenaissance");
    console.log(this.currentdate + " date");
    let age = Number(this.currentdate.getFullYear()) - Number(this.Datedenaissance.getFullYear());
    return age;
  }
  openSnackBar() {
    this._snackBar.openFromComponent(SnackbarokComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }
}
