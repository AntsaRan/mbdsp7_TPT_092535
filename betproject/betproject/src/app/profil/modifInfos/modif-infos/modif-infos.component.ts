import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CartService } from 'src/app/shared/services/cart.service';

@Component({
  selector: 'app-modif-infos',
  templateUrl: './modif-infos.component.html',
  styleUrls: ['./modif-infos.component.css']
})
export class ModifInfosComponent implements OnInit {


  constructor(private cartserv: CartService,
    private dialogRef: MatDialogRef<ModifInfosComponent>,
    @Inject(MAT_DIALOG_DATA) data) { }

  nom = null;
  pseudo=null;
  prenom = null;
  mail = null;
  datenaiss=null;
  error = "";

  ngOnInit(): void {
  }

  onSubmit(event) {

      this.dialogRef.close({ nom: this.nom,pseudo:this.pseudo,prenom:this.prenom,mail:this.mail,datenaiss:this.datenaiss });
  }
updateinfo(){
  
}
}
