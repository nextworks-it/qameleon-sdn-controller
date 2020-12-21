import { Component, OnInit ,ViewChild,Input} from '@angular/core';
import{LightPathsService} from '../light-paths.service'
import{NetworkTopologyService} from '../../network-topology/network-topology.service'
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder } from '@nebular/theme';

interface TreeNode<T> {
  data: T;
}

interface PInfoEntry {
  Name: string;
  Source:string;
  Destination:string;
}

@Component({
  selector: 'ngx-light-paths-show',
  templateUrl: './light-paths-show.component.html',
  styleUrls: ['./light-paths-show.component.scss']
})
export class LightPathsShowComponent implements OnInit {
  @ViewChild('item', { static: true }) accordion;
  toggle() {
    this.accordion.toggle();
  }

  customColumn = 'Name';
  defaultColumns = ['Source','Destination'];
  allColumns = [ this.customColumn, ...this.defaultColumns ];
  
  view: any[];
  dataSource: NbTreeGridDataSource<PInfoEntry>;
  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;
  nodeColor:string;
  selectedNode:string;
  selectedLink:string;
  channelName:string;
  channelNumber:string;
  links:any;
  nodes:any;
  linkInfos:any;
  nodeInfos:any;
  pathUuid:string;
  sourceLinkNode=[];
  targetLinkNode=[];
  linkOfPath=[];
  
  constructor(private lightPathsService:LightPathsService,
    private networkTopologyService:NetworkTopologyService,
    private dataSourceBuilder: NbTreeGridDataSourceBuilder<PInfoEntry>) { 
      this.pathUuid=localStorage.getItem('pathUuid');
      this.links=[];
      this.nodes=[];
      var linkArr: TreeNode<PInfoEntry>[];
      linkArr=[];
  
  /************************************************finding links of path*************************************************** */
     
  this.lightPathsService.getPathByUuid(this.pathUuid).subscribe(async (pathInfos: any) =>
  {
    this.channelName=pathInfos['tapi-path-computation:path'][0]['name'][1]['value-name']
    this.channelNumber=pathInfos['tapi-path-computation:path'][0]['name'][1]['value']

    for(var pa of pathInfos['tapi-path-computation:path'][0]['link']){
    
      var linkReq={
                  "qameleon-topology:input": {
                    "qameleon-topology:link-id": pa['link-uuid']
                  }
                }
            
      ;(await this.lightPathsService.getLinks(linkReq)).subscribe((linkInfos: any) =>
      {
        console.log("linkInfos",linkInfos)
        this.sourceLinkNode.push(linkInfos['output']['link-out']['node-src']);
  
        this.targetLinkNode.push(linkInfos['output']['link-out']['node-dst']);

        this.linkOfPath.push(linkInfos['output']['link-out']['uuid']);
        
        linkArr.push(
          {
            data: { Name: linkInfos['output']['link-out']['uuid'],
            Source:linkInfos['output']['link-out']['node-src'],
            Destination:linkInfos['output']['link-out']['node-dst']
            }
          }
  
        ) 
       

      } 

      
      );
  
    } 
   
  }
  );



  /************************************************create graph*************************************************** */
var topoReq={
  "qameleon-topology:input": {
    "qameleon-topology:topology-id": "QameleonTopology"
  }
}
  this.networkTopologyService.getTopologies(topoReq).subscribe((topologyInfos: any) =>
    {   
      this.linkInfos=topologyInfos['output']['qam-topology-out']['qam-link'];
      this.nodeInfos=topologyInfos['output']['qam-topology-out']['qam-node'];
      
      for(var nodeInfos of topologyInfos['output']['qam-topology-out']['qam-node']){
        this.nodeColor='#dce9fc';
        this.nodes.push(
          {
            id: nodeInfos['uuid'],
            label: nodeInfos['uuid'],
            color:this.nodeColor,
            labelColor:'black'
          }
        )

      }

      for(var linkInfos of topologyInfos['output']['qam-topology-out']['qam-link']){   
        this.links.push(
          {
            id: linkInfos['uuid'],
            source: linkInfos['node-src'],
            target: linkInfos['node-dst'],
            sourcePort:linkInfos['port-src'],
            destPort:linkInfos['ort-dst'],
            label: linkInfos['uuid'],
            color:'black'
          }
        ) 
      }
      (async () => { 
        await this.delay(1000);
        for(var nd of this.nodes){  
        
          if(this.sourceLinkNode.includes(nd['id']) || this.targetLinkNode.includes(nd['id']))
          {
              nd['color']='#c1eeb0';
              nd['labelColor']='black';          
            }
            else {  
              nd['color']='#f2f2f2';
              nd['labelColor']='#cac7c0';
            } 
          }

          for(var lnk of this.links){
            if(this.linkOfPath.includes(lnk['id'])){
              lnk['color']='green';
            } 
            else{
              lnk['color']='#f2f2f2'
            } 
            
      
          }
          (<HTMLInputElement> document.getElementById("graph")).style.display="inline";
          
          this.dataSource = this.dataSourceBuilder.create(linkArr);  

          window.dispatchEvent(new Event('resize'));


    })();
        }       
    );
}


  ngOnInit(): void {}


   delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
}

  updateSort(sortRequest: NbSortRequest): void {
    this.sortColumn = sortRequest.column;
    this.sortDirection = sortRequest.direction;
  }
  getSortDirection(column: string): NbSortDirection {
    if (this.sortColumn === column) {
      return this.sortDirection;
    }
    return NbSortDirection.NONE;
  }
  getShowOn(index: number) {
    const minWithForMultipleColumns = 400;
    const nextColumnStep = 100;
    return minWithForMultipleColumns + (nextColumnStep * index);
  }


}

@Component({
  selector: 'ngx-fs-icon',
  template: `
    <nb-tree-grid-row-toggle [expanded]="expanded" *ngIf="isDir(); else fileIcon">
    </nb-tree-grid-row-toggle>
    <ng-template #fileIcon>
      <nb-icon icon="file-text-outline"></nb-icon>
    </ng-template>
  `,
})
export class FsIconComponent {
  @Input() kind: string;
  @Input() expanded: boolean;

  isDir(): boolean {
    return this.kind === 'dir';
  }
}