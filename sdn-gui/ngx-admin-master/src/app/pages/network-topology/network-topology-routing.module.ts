import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NetworkTopologyComponent } from './network-topology.component';
import { NetworkTopologyAddComponent } from './network-topology-add/network-topology-add.component';
import { NetworkTopologyListComponent } from './network-topology-list/network-topology-list.component';
import { NetworkTopologyShowComponent } from './network-topology-show/network-topology-show.component';

const routes: Routes = [
  {
    path: '',
    component: NetworkTopologyComponent,
    children: [
      {
        path: 'network-add',
        component: NetworkTopologyAddComponent,
      },
      {
        path: 'network-list',
        component: NetworkTopologyListComponent,
      },
      {
        path: 'network-show',
        component: NetworkTopologyShowComponent,
      }
    ],
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class NetworkTopologyRoutingModule {
}

