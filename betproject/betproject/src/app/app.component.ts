import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { CartService } from './shared/services/cart.service';
import { Match } from './shared/models/match.model';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from './login/login/login.component';
import { InscriptionComponent } from './inscription/inscription/inscription.component';
import { MatchServiceService } from './shared/services/match-service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'betproject';
  islogged: boolean = false;
  nomutilisateur: string="";
  constructor(
    private datePipe: DatePipe,
    private cartserv: CartService,
    public dialog: MatDialog,
    private router: Router) {

  }
  ngOnInit(): void {

    if (localStorage.getItem('currentUser') != null) {
      this.islogged = true;
      this.nomutilisateur= localStorage.getItem('username');
    }
  }
  openLoginDialog() {
    const dialogRef = this.dialog.open(LoginComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
  openSigninDialog() {
    const dialogRef = this.dialog.open(InscriptionComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
  logout(){
    this.cartserv.format();
  }
  reloadComponent() {

     this.router.navigate(["/"]);
   // window.location.reload();

  }
}
