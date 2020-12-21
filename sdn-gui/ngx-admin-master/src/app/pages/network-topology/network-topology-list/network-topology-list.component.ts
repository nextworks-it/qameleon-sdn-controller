import { Component, Input, OnInit } from '@angular/core';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder } from '@nebular/theme';
import {Router } from '@angular/router';
import{NetworkTopologyService} from '../network-topology.service'


interface TreeNode<T> {
  data: T;
}

interface FSEntry {
  Name: string;
}

@Component({
  selector: 'ngx-network-topology-list',
  templateUrl: './network-topology-list.component.html',
  styleUrls: ['./network-topology-list.component.scss']
})

export class NetworkTopologyListComponent implements OnInit{
  customColumn = 'Name';
  defaultColumns = ['Action' ];
  topologyInfos:string
  allColumns = [ this.customColumn, ...this.defaultColumns ];
  dataSource: NbTreeGridDataSource<FSEntry>;
  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;

  constructor(private dataSourceBuilder: NbTreeGridDataSourceBuilder<FSEntry>, private router: Router,private networkTopologyService:NetworkTopologyService) {
  
  

  }
  ngOnInit(){
    var topoReq={
      "qameleon-topology:input": {
        "qameleon-topology:topology-id": "QameleonTopology"
      }
    }
    var topologyArr: TreeNode<FSEntry>[];
    topologyArr=[]
    this.networkTopologyService.getTopologies(topoReq).subscribe((topologyInfos: any) =>
    {
      
      topologyArr.push(
        {
          data: { Name:  topologyInfos['output']['qam-topology-out']['topology-uuid'] }
        }
      )
      this.dataSource = this.dataSourceBuilder.create(topologyArr);
    });


  }
  updateSort(sortRequest: NbSortRequest): void {
    this.sortColumn = sortRequest.column;
    this.sortDirection = sortRequest.direction;
  }
  CreateNetwork(){
    this.router.navigate(['pages/networks/network-add']);
  }
  getSortDirection(column: string): NbSortDirection {
    if (this.sortColumn === column) {
      return this.sortDirection;
    }
    return NbSortDirection.NONE;
  }



  showGraph(uuId){
    localStorage.setItem('uuid', uuId);
    this.router.navigate(['pages/networks/network-show']);
  }

  showLightPaths(uuId){
    localStorage.setItem('pathUuid', uuId);
    this.router.navigate(['pages/lightpaths/light-paths-list']);
  }

  deleteGraph(uuId){

      window.location.reload();
     
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
