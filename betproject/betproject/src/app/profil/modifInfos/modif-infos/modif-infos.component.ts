import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Parieur } from 'src/app/shared/models/parieur.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { CartService } from 'src/app/shared/services/cart.service';

@Component({
  selector: 'app-modif-infos',
  templateUrl: './modif-infos.component.html',
  styleUrls: ['./modif-infos.component.css']
})
export class ModifInfosComponent implements OnInit {


  constructor(private cartserv: CartService, private router: Router,
    private dialogRef: MatDialogRef<ModifInfosComponent>, private datePipe: DatePipe,
    @Inject(MAT_DIALOG_DATA) data, private auth: AuthService) { }
  user: Parieur = new Parieur();
  nom = null;
  pseudo = null;
  prenom = null;
  mail = null;
  datenaiss = null;
  error = "";
  id = null;

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user'))
    // console.log(this.user.dateNaissance + " MAIL USER")
    this.id = this.user.id;
    this.nom = this.user.nom;
    this.prenom = this.user.prenom;
    this.pseudo = this.user.pseudo;
    this.mail = this.user.mail;
    this.datenaiss = this.user.dateNaissance;
    this.datenaiss = this.datePipe.transform(this.user.dateNaissance, 'yyyy-MM-dd');

  }

  onSubmit(event) {
    let newuser = new Parieur();
    newuser.dateNaissance = this.datenaiss;
    newuser.nom = this.nom;
    newuser.prenom = this.prenom;
    newuser.id = this.id;
    newuser.mail = this.mail;
    newuser.pseudo = this.pseudo;
    this.auth.updateUser(newuser)
      .subscribe(u => {
        console.log(u.msg.id + " U ID")
        if (u.msg.id) {
          this.dialogRef.close();
          localStorage.setItem("user", JSON.stringify(u.msg));
          let currentUrl = this.router.url;
          this.router.routeReuseStrategy.shouldReuseRoute = () => false;
          this.router.onSameUrlNavigation = 'reload';
          this.router.navigate([currentUrl]);
        }
      })
  }

}
