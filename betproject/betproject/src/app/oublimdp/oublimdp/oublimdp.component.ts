import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { InscriptionComponent } from 'src/app/inscription/inscription/inscription.component';
import { AuthService } from 'src/app/shared/services/auth.service';
import { MailerService } from 'src/app/shared/services/mailer.service';

@Component({
  selector: 'app-oublimdp',
  templateUrl: './oublimdp.component.html',
  styleUrls: ['./oublimdp.component.css']
})
export class OublimdpComponent implements OnInit {
  emailhidden: string = "";
  realmail: string = "";
  email: string = "";
  error = "";
  constructor(public dialog: MatDialog, private auth: MailerService, private route: ActivatedRoute,
    private router: Router) {
    // this.realmail = localStorage.getItem('mail');
    //  this.emailhidden = this.censorEmail(this.realmail);
  }

  ngOnInit(): void {
  }

  onSubmit($event) {
    if (this.email = "") {
      this.error = "Email obligatoire";
    } else {
      this.auth.forgetpass(this.email)
        .subscribe(m => {
          if(m){
            
          }
          console.log("ok");
        })
    }

  }
  openSigninDialog() {
    this.dialog.closeAll();
    const dialogRef = this.dialog.open(InscriptionComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
  //console.log(censorEmail("jack.dawson@gmail.com"));
}
