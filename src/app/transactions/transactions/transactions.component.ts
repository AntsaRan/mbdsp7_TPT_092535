import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/shared/models/transaction';
import { JetonsService } from 'src/app/shared/services/jetons.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  loading: boolean = true;
  transactions: Transaction[] = [];
  nodata: string;
  constructor(private jetonsserv: JetonsService) { }

  ngOnInit(): void {
    if(localStorage.getItem('currentUser')){
      this.gethisto(localStorage.getItem('currentUser'));
    }
  }
  gethisto(id) {
    this.jetonsserv.gethistouser(id)
      .subscribe(data => {
        if (data!=null) {
        data.forEach(d=>{
          this.transactions.push(d);
        })
          this.rearrangeDates(this.transactions);
          this.loading=false;
        } else {
          console.log("no daataaaa");
          this.loading=false;
          this.nodata = "No data";
        }

        this.loading = false;
      });
  }

  rearrangeDates(m: Transaction[]) {
    m.sort((a: Transaction, b: Transaction) => {
      return new Date(a.dateTransaction).getTime() - new Date(b.dateTransaction).getTime();
    });
  }
}
