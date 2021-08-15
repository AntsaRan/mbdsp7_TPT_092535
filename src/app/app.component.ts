import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { CartService } from './shared/services/cart.service';
import { Match } from './shared/models/match.model';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from './views/login/login.component';
import { InscriptionComponent } from './views/inscription/inscription/inscription.component';
import { MatchServiceService } from './shared/services/match-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MessagingService } from './shared/services/messaging.service';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Notifs } from './shared/models/notifs';
import { Subscription } from 'rxjs';
import { NouveaumdpComponent } from './views/oublimdp/nouveaumdp/nouveaumdp.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'betproject';
  islogged: boolean = false;
  nomutilisateur: string = "";
  searchval: string = "";
  nbnotif: number = 0;
  hidden = false;
  horizontalPosition: MatSnackBarHorizontalPosition = 'start';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  notif: any[] = [];
  routeQueryParams: Subscription;
  userretrievemdp:string="";
  constructor(
    private datePipe: DatePipe,
    private cartserv: CartService,
    public dialog: MatDialog,
    private router: Router, private route: ActivatedRoute,
    private mess: MessagingService, private _snackBar: MatSnackBar) {
      this.routeQueryParams = route.queryParams.subscribe(params => {
        if (params['v']) {
          localStorage.setItem('v',params['v']);
          this.openDialog();
        }
      });
  }
  ngOnInit(): void {
    if (localStorage.getItem('currentUser') != null) {
      this.islogged = true;
      this.nomutilisateur = localStorage.getItem('username');
    }
    this.mess.receiveMessage();
    this.mess.msg$.subscribe(m => {
      if (m) {
        this.nbnotif += 1;
        var msg: any = m;
        this.notif.push(msg.data);
        console.log(JSON.stringify(msg.data) );
      }
    })
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(NouveaumdpComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.animal = result;
      this.router.navigate(['.'], { relativeTo: this.route });
    });
  }
  toggleBadgeVisibility() {
    if (this.nbnotif == 0) {
      this.hidden = !this.hidden;
      this.notif = [];
    } else {
      this.hidden = !this.hidden;
      this.nbnotif = 0;
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
  logout() {
    this.cartserv.format();
    this.router.navigate(["/"]);
    window.location.reload();
  }
  reloadComponent() {
    window.location.reload();
    //this.router.navigate(["/"]);

  }
  onSubmitsearch(event) {
    console.log(this.searchval + " searchval ");
    //this.router.navigate(["/search?search="+this.searchval]);
    this.router.navigate(['/search'], { queryParams: { search: this.searchval } });

  }
}
