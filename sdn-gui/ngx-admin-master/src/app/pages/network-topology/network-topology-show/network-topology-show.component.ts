

import { Component, OnInit ,ViewChild,Input} from '@angular/core';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder } from '@nebular/theme';
import{NetworkTopologyService} from '../network-topology.service'

interface TreeNode<T> {
  data: T;
}

interface LEntry {
  Links: string;

}

interface NEntry {
  Nodes: string;

}


interface PEntry {
  Name: string;
  Source:string;
  Destination:string;
  SourcePort:string;
  DestPort:string;
  Direction:string;
}

@Component({
  selector: 'ngx-network-topology-show',
  templateUrl: './network-topology-show.component.html',
  styleUrls: ['./network-topology-show.component.scss']
})
export class NetworkTopologyShowComponent implements OnInit {
  @ViewChild('item', { static: true }) accordion;

  toggle() {
    this.accordion.toggle();
  }
  customColumnNode = 'Nodes';
  allColumnsNode = [ this.customColumnNode];

  customColumn = 'Links';
  defaultColumns = [];
  allColumns = [ this.customColumn, ...this.defaultColumns ];


  customColumnSelected = 'Name';
  defaultColumnsSelected = ['Source','Destination','SourcePort','DestPort','Direction'];
  allColumnsSelected = [ this.customColumnSelected, ...this.defaultColumnsSelected ];

  view: any[];

  topologyInfos:string
  showSelectedNode:boolean;
  showSelectedLink:boolean;
  dataSourceNode: NbTreeGridDataSource<NEntry>;
  dataSourceLink: NbTreeGridDataSource<LEntry>;
  dataSourceSelectedLink: NbTreeGridDataSource<PEntry>;
  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;
  nodeColor:string;
  selectedNode:string;
  selectedLink:string
  links:any;
  nodes:any;
  ports:any;
  PortArrSelect:any;
  clusters:any;
  linkInfos:any;
  nodeInfos:any;
  connectedNodes=[];
  connectedlinks=[];
  connectedPorts=[];
  nodePorts=[];
  topoName:string
  constructor(private networkTopologyService:NetworkTopologyService,
    private dataSourceBuilderNode: NbTreeGridDataSourceBuilder<NEntry>,
    private dataSourceBuilderLink: NbTreeGridDataSourceBuilder<LEntry>,
    private dataSourceSelectedBuilder: NbTreeGridDataSourceBuilder<PEntry>) { 

     
  }


  ngOnInit(): void {
    this.topoName=localStorage.getItem('uuid')
    this.showSelectedNode=false;
    this.showSelectedLink=false;
    var nodeArr: TreeNode<NEntry>[];
    nodeArr=[];
    var linkArr: TreeNode<LEntry>[];
    linkArr=[];
    this.links=[];
    this.nodes=[];
    this.clusters=[];
    var topoReq={
      "qameleon-topology:input": {
        "qameleon-topology:topology-id": "QameleonTopology"
      }
    }
    this.networkTopologyService.getTopologies(topoReq).subscribe((topologyInfos: any) =>
    {
    
      (async () => { 
        await this.delay(1000);
      
        this.topologyInfos=topologyInfos;
        this.linkInfos=topologyInfos['output']['qam-topology-out']['qam-link'];
        this.nodeInfos=topologyInfos['output']['qam-topology-out']['qam-node'];
        
        for(var nd of this.nodeInfos){
          nodeArr.push(
            {
              data: { Nodes:  nd['uuid']},
            }
          )      
        }
  
        for(var ln of this.linkInfos){
          linkArr.push(
            {
              data: { Links:  ln['uuid']},
            }
          )      
        }
        this.dataSourceNode = this.dataSourceBuilderNode.create(nodeArr);
        this.dataSourceLink = this.dataSourceBuilderLink.create(linkArr);
  
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
              destPort:linkInfos['port-dst'],
              direction:linkInfos['direction'],
              label: linkInfos['uuid'],
              color:'black'
            }
          ) 
        }
        (<HTMLInputElement> document.getElementById("graph")).style.display="inline";
        window.dispatchEvent(new Event('resize'));



    })();


        }

    );

    


  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
}
  
  onNodeClick(event:any,nodeLable){ 
    this.showSelectedLink=false;
    window.dispatchEvent(new Event('resize'));

   this.showSelectedNode=true;  
   this.connectedNodes=[]
   this.connectedlinks=[]
   this.PortArrSelect=[];
   this.selectedNode=nodeLable;
   for(var nd of this.nodes){   
     if(nd['id']!=nodeLable){
       nd['color']='#f2f2f2';
       nd['labelColor']='#cac7c0';
     }else if(nd['id']==nodeLable){  
        nd['color']='#dce9fc';
        nd['labelColor']='black';
        
     }
   }

   for(var lnk of this.links){
    if(lnk['source']==nodeLable){
      this.connectedlinks.push(lnk['id']);
      lnk['color']='black';
     for(var nd of this.nodes){   
      if(nd['id']==lnk['target']){
        this.connectedNodes.push(nd['id'])
          nd['color']='#dce9fc';
          nd['labelColor']='#black';
   
      }
      for(var portInf of this.nodePorts){
        if(portInf['node']==nodeLable){
            this.connectedPorts=portInf['ports']
        }
      }
    }
    }else{
      lnk['color']='#f2f2f2';
    }
    if(lnk['target']==nodeLable){
      this.connectedlinks.push(lnk['id']);
      lnk['color']='black';
      for(var nd of this.nodes){   
        if(nd['id']==lnk['source']){
          this.connectedNodes.push(nd['id'])
            nd['color']='#dce9fc';
            nd['labelColor']='black';
        }
      }     
    }

  }

  for(var node of this.topologyInfos['output']['qam-topology-out']['qam-node']){
    if(node['uuid']==nodeLable){
      for(var ownedNode of node['owned-node-edge-point']){        
        this.PortArrSelect.push(
          {
             Name:  ownedNode['uuid'],Type: ownedNode['link-port-direction'],
          }
        ) 
        
    }
    }

  }  
    
  }
  
  onLinkClick(event:any,linkLabel){
    this.showSelectedNode=false
    var source:string;
    var target:string;
    this.showSelectedLink=true;
    var linkSelectArr: TreeNode<PEntry>[];
    linkSelectArr=[];
    this.selectedLink=linkLabel;
    for(var lnk of this.links){
     
      if(lnk['id']==linkLabel){
        source=lnk['source'];
        target=lnk['target'];
        lnk['color']='black';
      linkSelectArr.push(
        {
          data:{
          Name: lnk['id'],
          Source: lnk['source'],
          Destination: lnk['target'],
          SourcePort: lnk['sourcePort'],
          DestPort: lnk['destPort'],
          Direction :lnk['direction']
        }
        }
      ) 
      } 
      else{
        lnk['color']='#f2f2f2'
      } 
    }
   
    for(var nd of this.nodes){ 
      
      if(nd['id']==source || nd['id']==target){
          nd['labelColor']='black';
          nd['color']='#dce9fc'; 
      }
      else{
         nd['color']='#f2f2f2';
         nd['labelColor']='#cac7c0';
      }
    }
  
    this.dataSourceSelectedLink = this.dataSourceSelectedBuilder.create(linkSelectArr);
    window.dispatchEvent(new Event('resize'));


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
