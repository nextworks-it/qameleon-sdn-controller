
import {Component, OnChanges, Renderer2, ElementRef, Input, Output, EventEmitter} from '@angular/core';

declare var cytoscape: any;

//#424242; 
@Component({
  selector: 'ngx-netweork-graph',
  template: '<div id="cy"></div>',
    styles: [`#cy {
        background: #fff;
        height: 100%;
        width: 100%;
        position: relative;
        left: 0;
        top: 0;
    }`]
})

export class NetworkTopologyGraphComponent {

}
