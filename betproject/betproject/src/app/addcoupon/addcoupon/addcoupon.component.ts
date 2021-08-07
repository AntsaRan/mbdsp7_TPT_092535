import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CartService } from 'src/app/shared/services/cart.service';

@Component({
  selector: 'app-addcoupon',
  templateUrl: './addcoupon.component.html',
  styleUrls: ['./addcoupon.component.css']
})
export class AddcouponComponent implements OnInit {

  constructor(private cartserv: CartService,
    private dialogRef: MatDialogRef<AddcouponComponent>,
    @Inject(MAT_DIALOG_DATA) data) { }

  mise = null;
  error = "";
  ngOnInit(): void {
  }
  addcoupon() {
  }
  onSubmit(event) {
    if (!this.mise) {
      this.error = "Renseignez tous les champs obligatoires";
    }else if(this.mise<1){
      this.error = "La mise doit être supérieur à 1 Ar";
    }else{
      this.dialogRef.close({ mise: this.mise });
    }
  }
}