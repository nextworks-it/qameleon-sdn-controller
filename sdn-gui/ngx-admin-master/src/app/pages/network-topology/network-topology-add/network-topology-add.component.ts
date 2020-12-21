import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import{NetworkTopologyService} from '../network-topology.service';
import {AuthService} from '../../../auth/auth.service';
import {Router } from '@angular/router';

@Component({
  selector: 'ngx-network-topology-add',
  templateUrl: './network-topology-add.component.html',
  styleUrls: ['./network-topology-add.component.scss']
})
export class NetworkTopologyAddComponent implements OnInit{
  formGroup: FormGroup;
  tpoObj : object;
  constructor(
    private _formBuilder: FormBuilder,
    private networkTopologyService: NetworkTopologyService,
    private authService:AuthService,
    private router: Router) {
  }
  ngOnInit() {
    this.formGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
  }
  onUploadedTopology(event: any, tpos: File[]) {
    let promises = [];

    for (let tpo of tpos) {
      if(tpo.type=='application/json' && tpo.name.includes('json')){

        let tpoPromise = new Promise(resolve => {
            let reader = new FileReader();
            reader.readAsText(tpo);
            reader.onload = () => resolve(reader.result);
        });
        promises.push(tpoPromise);
      }else{
        this.authService.log(`the file is not json`, 'FAILED', false);
      }
    }
    if(promises.length > 0){
    Promise.all(promises).then(fileContents => {
        this.tpoObj = JSON.parse(fileContents[0]);

        //console.log(JSON.stringify(this.tpoObj, null, 4));
    });
  }
  }
  onBoardTopology(){
      this.networkTopologyService.postTopology(this.tpoObj).subscribe((res: any) =>
      {
      //  this.router.navigate(['pages/networks/network-list']);
      });
  }
}
