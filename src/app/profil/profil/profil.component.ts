import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Parieur } from 'src/app/shared/models/parieur.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { ModifInfosComponent } from '../modifInfos/modif-infos/modif-infos.component';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  loading: boolean = true;
  user: Parieur = new Parieur();
  user2: Parieur = new Parieur();
  mise:Number=0;
  elementType: 'url' | 'canvas' | 'img' = 'url';
  value = null;
  constructor(private auth: AuthService, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    if (localStorage.getItem('currentUser') != null) {
      this.user2 = JSON.parse(localStorage.getItem('user'));
      // console.log(localStorage.getItem('currentUser') + " GGGGGGGGGGGGGGG");
      this.user.id = this.user2.id;
      this.user.mail = this.user2.mail;
      this.user.prenom = this.user2.prenom;
      this.user.nom = this.user2.nom;
      this.auth.getUserMise(this.user2.id)
        .subscribe(mise => {
          if(mise){
            console.log(JSON.stringify(mise)+ ' mise');
            this.mise = mise;
            localStorage.setItem('miseUser',  this.mise.toString());
          }

        })
      this.auth.getUserByID(this.user2.id)
      .subscribe(u=>{
        console.log(u.jetons+ ' U JETONS');
        this.user.jetons=u.jetons;
        localStorage.setItem('jetonsuser', u.jetons.toString());
        this.loading=false;
      })
      //this.mise = localStorage.getItem('miseTotale');
      this.value = this.user.id + "|" + this.user.jetons + "|" + this.user.mail + "|" + this.user.prenom + "|" + this.user.nom;
    } else {
      this.reloadComponent();
    }
  }
  reloadComponent() {
    this.router.navigate(["/"]);
  }
  openLoginDialog() {
    const dialogRef = this.dialog.open(ModifInfosComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
