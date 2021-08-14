import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { User } from '../../shared/model/user';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit {
  pseudo = "";
  password = "";
  hide = true;
  error: string = "";
  user:User=new User();
  constructor(private auth: AuthService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.newHero();

  }
  onSubmit(event) {
    if (this.user.pseudo=="" || this.user.password==""){
      this.error=" Tous les champs sont à remplir!"
      return;
    }
    this.auth.logIn(this.user.pseudo, this.user.password)
      .pipe(first())
      .subscribe(m => {
        if (!m) {
          this.error = " Username or password error";
          return;
        } else {
          this.router.navigate(['']);
        }

      });
  }

  newHero() {
    this.user.pseudo = "";
    this.user.password = "";
  }
  reloadComponent() {
    /* let currentUrl = this.router.url;
     this.router.routeReuseStrategy.shouldReuseRoute = () => false;
     this.router.onSameUrlNavigation = 'reload';
     this.router.navigate([currentUrl]);*/
    window.location.reload();

  }

}
