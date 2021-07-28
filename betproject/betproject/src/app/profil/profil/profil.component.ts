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
  mise: string;
  elementType: 'url' | 'canvas' | 'img' = 'url';
  value = null;
  constructor(private auth: AuthService, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    if (localStorage.getItem('currentUser') != null) {
      this.user2 = JSON.parse(localStorage.getItem('user'));
      // console.log(localStorage.getItem('currentUser') + " GGGGGGGGGGGGGGG");
      this.loading = false;
      this.user.id = this.user2.id;
      this.user.mail = this.user2.mail;
      this.user.prenom = this.user2.prenom;
      this.user.nom = this.user2.nom;
      this.auth.getUserMise(this.user2.id)
        .subscribe(mise => {
          this.mise = mise.toString();
          localStorage.setItem('miseUser',  this.mise.toString());
        })
      this.auth.getUserByID(this.user2.id)
      .subscribe(u=>{
        console.log(u.jetons+ ' U JETONS');
        this.user.jetons=u.jetons;
      })
      //this.mise = localStorage.getItem('miseTotale');
      this.value = this.user.id + "|" + this.user.jetons + "|" + this.user.mail + "|" + this.user.prenom + "|" + this.user.nom;
    } else {
      this.reloadComponent();
    }
  }
  reloadComponent() {
    this.router.navigate(["/"]);
    /* 
     this.router.routeReuseStrategy.shouldReuseRoute = () => false;
     this.router.onSameUrlNavigation = 'reload';*/
    //
  }
  openLoginDialog() {
    const dialogRef = this.dialog.open(ModifInfosComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
