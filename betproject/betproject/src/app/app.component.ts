import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import { CartService } from './shared/services/cart.service';
import { Match } from './shared/models/match.model';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from './login/login/login.component';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'betproject';
  islogged:boolean=false;

  constructor(private datePipe: DatePipe, private cartserv:CartService,public dialog: MatDialog) {

  }
  openLoginDialog(){
    const dialogRef = this.dialog.open(LoginComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
