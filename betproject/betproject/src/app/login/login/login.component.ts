import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = "";
  password = null;
  hide = true;
 // user: User;
  error = "";
  constructor() { }

  ngOnInit(): void {
  }
  onSubmit(event) {
    if (!this.username || !this.password)
      return;

   /* this.authservice.logIn(this.username, this.password)
      .pipe(first())
      .subscribe(m => {
        if (!m) 
        {
          this.error=" Username or password error";
          return;
        }
        this.reloadComponent();
      });*/
  }

}
