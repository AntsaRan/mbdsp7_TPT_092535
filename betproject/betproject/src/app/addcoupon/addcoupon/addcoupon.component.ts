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

  mise: Number;
  error = "";
  ngOnInit(): void {
  }
  addcoupon() {
  }
  onSubmit(event) {
    this.dialogRef.close({ mise: this.mise });
  }
}