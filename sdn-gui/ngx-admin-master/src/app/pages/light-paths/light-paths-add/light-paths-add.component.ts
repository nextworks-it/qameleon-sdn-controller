
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import{LightPathsService} from '../light-paths.service';
import {AuthService} from '../../../auth/auth.service';
import {Router } from '@angular/router';

@Component({
  selector: 'ngx-light-paths-add',
  templateUrl: './light-paths-add.component.html',
  styleUrls: ['./light-paths-add.component.scss']
})
export class LightPathsAddComponent implements OnInit{
  formGroup: FormGroup;
  pthObj : object;
  constructor(
    private _formBuilder: FormBuilder,
    private lightPathsService: LightPathsService,
    private authService:AuthService,
    private router: Router) {
  }
  ngOnInit() {
    this.formGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
  }
  onUploadedLightPath(event: any, pths: File[]) {
    let promises = [];

    for (let pth of pths) {
      if(pth.type=='application/json' && pth.name.includes('json')){

        let pthPromise = new Promise(resolve => {
            let reader = new FileReader();
            reader.readAsText(pth);
            reader.onload = () => resolve(reader.result);
        });
        promises.push(pthPromise);
      }else{
        this.authService.log(`the file is not json`, 'FAILED', false);
      }
    }
    if(promises.length > 0){
    Promise.all(promises).then(fileContents => {
        this.pthObj = JSON.parse(fileContents[0]);

        //console.log(JSON.stringify(this.pthObj, null, 4));
    });
  }
  }
  onBoardLightPath(){
      this.lightPathsService.postLightPath(this.pthObj).subscribe((res: any) =>
      {
        this.router.navigate(['pages/lightpaths/light-paths-list']);
      });
  }
}
