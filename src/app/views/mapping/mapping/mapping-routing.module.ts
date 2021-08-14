import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MappingListComponent } from './mapping-list.component';
import { MappingUpdateComponent } from '../mapping-update/mapping-update.component';
import { MappingajoutComponent } from './mappingajout.component';

import { MappingComponent } from './mapping.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Mapping'
    },
    children: [
      {
        path: '',
        redirectTo: 'maps'
      },
      {
        path: 'liste',
        component: MappingListComponent,
        data: {
          title: 'Liste'
        }
      },
      {
        path: 'ajout',
        component: MappingajoutComponent,
        data: {
          title: 'Ajout'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MappingRoutingModule { }
