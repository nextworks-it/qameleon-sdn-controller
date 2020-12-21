import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LightPathsComponent } from './light-paths.component';
import { LightPathsListComponent } from './light-paths-list/light-paths-list.component';
import { LightPathsShowComponent } from './light-paths-show/light-paths-show.component';
import { LightPathsAddComponent } from './light-paths-add/light-paths-add.component';

const routes: Routes = [
  {
    path: '',
    component: LightPathsComponent,
    children: [
      {
        path: 'light-paths-list',
        component: LightPathsListComponent,
      },
      {
        path: 'light-paths-show',
        component: LightPathsShowComponent,
      },
      {
        path: 'light-paths-add',
        component: LightPathsAddComponent,
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
export class LightPathsRoutingModule {
}

