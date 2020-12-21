import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule,
  NbTreeGridModule,  
  
  NbAccordionModule,

  
  NbListModule,
  NbRouteTabsetModule,
  NbStepperModule,
  NbTabsetModule

} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { NetworkTopologyRoutingModule } from './network-topology-routing.module';
import { NetworkTopologyComponent } from './network-topology.component';
import { NetworkTopologyAddComponent } from './network-topology-add/network-topology-add.component';
import { NetworkTopologyListComponent } from './network-topology-list/network-topology-list.component';
import { NetworkTopologyShowComponent } from './network-topology-show/network-topology-show.component';
import { FormsModule as ngFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { FsIconComponent } from './network-topology-list/network-topology-list.component';
import { NgxGraphModule } from '@swimlane/ngx-graph';
import { NgxEchartsModule } from 'ngx-echarts';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    ThemeModule,
    NbInputModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbCheckboxModule,
    NbRadioModule,
    NbDatepickerModule,
    NetworkTopologyRoutingModule,
    NbSelectModule,
    NbIconModule,
    ngFormsModule,
    NbTreeGridModule,
    Ng2SmartTableModule,
    NgxGraphModule,
    NbTabsetModule,
    NbRouteTabsetModule,
    NbStepperModule,

    NbListModule,
    NbAccordionModule,
    FormsModule,
    NgxEchartsModule,
  ],
  declarations: [
    NetworkTopologyComponent,
    NetworkTopologyAddComponent,
    NetworkTopologyListComponent,
    NetworkTopologyShowComponent,
    FsIconComponent
  ],
})
export class NetworkTopologyModule { }
