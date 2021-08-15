import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { InscriptionComponent } from 'src/app/views/inscription/inscription/inscription.component';
import { LoginComponent } from 'src/app/views/login/login.component';
import { AuthService } from 'src/app/shared/services/auth.service';
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
    private router: Router, private authserv:AuthService) {
    // this.realmail = localStorage.getItem('mail');
    //  this.emailhidden = this.censorEmail(this.realmail);
  }

  ngOnInit(): void {
  }

  onSubmit($event) {
    let id=localStorage.getItem('v');
    if (this.password != this.password2) {
      this.error = "Les mots de passes ne sont pas identiques";
    } else {
      this.authserv.resetpass(this.password,id)
        .subscribe(m => {
          if(m.msg!="error"){
            console.log(m +" update mdp");
            this.openLogin();
          }else{
            this.error= " une erreur s'est produite, veuillez recommencer.";
          }
        })
    }

  }
   openLogin() {
    this.dialog.closeAll();
    const dialogRef = this.dialog.open(LoginComponent);
  }
  openSignInDialog() {
    this.dialog.closeAll();
    const dialogRef = this.dialog.open(InscriptionComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
