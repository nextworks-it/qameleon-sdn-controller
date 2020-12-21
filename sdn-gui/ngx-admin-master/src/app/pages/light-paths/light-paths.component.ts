import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ngx-light-paths-elements',
  template: `
    <router-outlet></router-outlet>
  `,
})
export class LightPathsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
