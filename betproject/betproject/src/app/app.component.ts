import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'betproject';
  currentdate = new Date();
  date: string;
  constructor(private datePipe: DatePipe) {
    this.date = this.datePipe.transform(this.currentdate, 'yyyy-MM-dd');
  }

}
