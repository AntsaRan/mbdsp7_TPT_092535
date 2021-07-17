import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Parieur } from 'src/app/shared/models/parieur.model';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  loading:boolean=true;
  user:Parieur=new Parieur();
  constructor( private router: Router) { }

  ngOnInit(): void {
    if(localStorage.getItem('currentUser')!=null){
      this.loading=false;
      this.user.id=localStorage.getItem('currentUser');
      this.user.jetons=Number.parseInt(localStorage.getItem('jetons'));
      this.user.mail=localStorage.getItem('mail');
      this.user.prenom=localStorage.getItem('prenom');
      this.user.nom=localStorage.getItem('username');
    }else{
      this.reloadComponent();
    }
  }
  reloadComponent() {
          console.log(localStorage.getItem('jetons') + " GGGGGGGGGGGGGGG");

    /* 
     this.router.routeReuseStrategy.shouldReuseRoute = () => false;
     this.router.onSameUrlNavigation = 'reload';*/
     //this.router.navigate(["/"]); 
   }
}
