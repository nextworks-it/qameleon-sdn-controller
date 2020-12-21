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
  NbTabsetModule,
  NbRouteTabsetModule,
  NbStepperModule,
  NbListModule

} from '@nebular/theme';
import { NgxEchartsModule } from 'ngx-echarts';
import { FormsModule } from '@angular/forms';

import { ThemeModule } from '../../@theme/theme.module';
import { LightPathsRoutingModule } from './light-paths-routing.module';
import { LightPathsComponent } from './light-paths.component';
import { LightPathsListComponent } from './light-paths-list/light-paths-list.component';
import { FormsModule as ngFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { FsIconComponent } from './light-paths-list/light-paths-list.component';
import { NgxGraphModule } from '@swimlane/ngx-graph';
import { LightPathsShowComponent } from './light-paths-show/light-paths-show.component';
import { LightPathsAddComponent } from './light-paths-add/light-paths-add.component';


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
    LightPathsRoutingModule,
    NbSelectModule,
    NbIconModule,
    ngFormsModule,
    NbTreeGridModule,
    Ng2SmartTableModule,
    NgxGraphModule,
    NbAccordionModule,
    NbTabsetModule,
    NbRouteTabsetModule,
    NbStepperModule,
    NbListModule,
    FormsModule,
    NgxEchartsModule,
  ],
  declarations: [
    LightPathsComponent,
    LightPathsListComponent,
    FsIconComponent,
    LightPathsShowComponent,
    LightPathsAddComponent
  ],
})
export class LightPathsModule { }
