import { Component, Input, OnInit } from '@angular/core';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder } from '@nebular/theme';
import {Router } from '@angular/router';
import{LightPathsService} from '../light-paths.service'


interface TreeNode<T> {
  data: T;
}

interface FSEntry {
  Id: string;
  Description:string;
}

@Component({
  selector: 'ngx-light-paths-list',
  templateUrl: './light-paths-list.component.html',
  styleUrls: ['./light-paths-list.component.scss']
})

export class LightPathsListComponent implements OnInit{
  customColumn = 'Id';
  defaultColumns = [ 'Description','Action' ];
  topologyInfos:string
  allColumns = [ this.customColumn, ...this.defaultColumns ];
  dataSource: NbTreeGridDataSource<FSEntry>;
  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;
  networkId:string;
  constructor(private dataSourceBuilder: NbTreeGridDataSourceBuilder<FSEntry>,
              private router: Router,private lightPathsService:LightPathsService) {
  
  

  }
  ngOnInit(){
    var lightPathArr: TreeNode<FSEntry>[];
    lightPathArr=[]
    this.lightPathsService.getLightPaths().subscribe((lighPathInfos: any) =>
    { 
      for(var li of lighPathInfos['tapi-path-computation:path-computation-context']['path']){
        var desc='From '+li['name'] [0]['value']+' To '+li['name'] [2]['value'];
          lightPathArr.push(
            {
              data: { Id:  li['uuid'], Description: desc }
            }
          )
      }

      this.dataSource = this.dataSourceBuilder.create(lightPathArr);
    });


  }
  updateSort(sortRequest: NbSortRequest): void {
    this.sortColumn = sortRequest.column;
    this.sortDirection = sortRequest.direction;
  }
  CreateLightPath(){
    this.router.navigate(['pages/lightpaths/light-paths-add']);
  }
  getSortDirection(column: string): NbSortDirection {
    if (this.sortColumn === column) {
      return this.sortDirection;
    }
    return NbSortDirection.NONE;
  }



  showLightPaths(uuId){
    localStorage.setItem('pathUuid',uuId);
    this.router.navigate(['pages/lightpaths/light-paths-show']);

  }


  removeLightPaths(uuId){
    var reqBody={
      "tapi-path-computation:input": {
       "tapi-path-computation:path-id-or-name":uuId
      }
    }
    this.lightPathsService.deleteLightPath(reqBody).subscribe((res: any) =>
    {
      window.location.reload();
     });
    

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

