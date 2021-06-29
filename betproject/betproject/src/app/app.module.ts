import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import { Routes, RouterModule, Router } from '@angular/router';
import { AccueilComponent } from './accueil/accueil/accueil.component';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import { DatePipe } from '@angular/common';
import { CouponsComponent } from './coupons/coupons/coupons.component';
import { LoginComponent } from './login/login/login.component';
import { FormsModule } from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDialogModule} from '@angular/material/dialog';
import { InscriptionComponent } from './inscription/inscription/inscription.component';
import { MatNativeDateModule } from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { FichematchComponent } from './ficheMatch/fichematch/fichematch.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

const routes: Routes = [
  {
    path: "",
    component: AccueilComponent,
  },
  {
    path: "detailmatch/:id",
    component: FichematchComponent,
  }
]

@NgModule({
  declarations: [
    AppComponent,
    AccueilComponent,
    CouponsComponent,
    LoginComponent,
    InscriptionComponent,
    FichematchComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSidenavModule, 
    RouterModule.forRoot(routes),
    MatListModule,MatDatepickerModule,HttpClientModule,
    MatCardModule,MatDialogModule,FormsModule,MatCheckboxModule,MatNativeDateModule
    
  ],
  providers: [
    DatePipe,MatNativeDateModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
