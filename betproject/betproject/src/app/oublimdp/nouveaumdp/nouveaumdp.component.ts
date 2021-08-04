import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { InscriptionComponent } from 'src/app/inscription/inscription/inscription.component';
import { LoginComponent } from 'src/app/login/login/login.component';
import { MailerService } from 'src/app/shared/services/mailer.service';

@Component({
  selector: 'app-nouveaumdp',
  templateUrl: './nouveaumdp.component.html',
  styleUrls: ['./nouveaumdp.component.css']
})
export class NouveaumdpComponent implements OnInit {

  password: string = "";
  password2: string = "";
  hide = true;
  error = "";
  constructor(public dialog: MatDialog, private auth: MailerService, private route: ActivatedRoute,
    private router: Router) {
    // this.realmail = localStorage.getItem('mail');
    //  this.emailhidden = this.censorEmail(this.realmail);
  }

  ngOnInit(): void {
  }

  onSubmit($event) {
    if (this.password != this.password2) {
      this.error = "Les mots de passes ne sont pas identiques";
    } else {
      this.auth.resetpass(this.password)
        .subscribe(m => {
          if(m){
            
          }
          console.log("ok");
        })
    }

  }
  openLoginDialog() {
    this.dialog.closeAll();
    const dialogRef = this.dialog.open(LoginComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
