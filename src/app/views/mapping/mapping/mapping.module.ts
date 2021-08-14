import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { MappingComponent } from './mapping.component';
import { MappingRoutingModule } from './mapping-routing.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    FormsModule,
    MappingRoutingModule,
    ChartsModule,
    BsDropdownModule,
    FormsModule,ReactiveFormsModule,
    ButtonsModule.forRoot()
  ],
  declarations: [ MappingComponent]
})
export class MappingModule { }
