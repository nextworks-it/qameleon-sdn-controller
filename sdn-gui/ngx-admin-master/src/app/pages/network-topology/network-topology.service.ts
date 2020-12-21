import { Injectable } from '@angular/core';
import { Observable, } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { AuthService } from '../../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class NetworkTopologyService {
  private baseUrl = environment.baseUrl;
  private topologyInfoUrl='restconf/operations/tapi-topology:get-topology-list';
  
  httpOptions = {
    headers: new HttpHeaders(
      { 
        'Content-Type': 'application/json',
        'Authorization':'Basic YWRtaW46YWRtaW4='
      }) ,
      withCredentials:true
    };
  constructor(private http: HttpClient, 
    private authService: AuthService) 
    { }

  getTopologies(topologyRec): Observable<any> {
    return this.http.post(this.baseUrl + 'restconf/operations/qameleon-topology:get-qam-topology',topologyRec,this.httpOptions)
      .pipe(
        tap(_ => console.log('fetched topology info - SUCCESS')),
        catchError(this.authService.handleError('getTopologies'))
      );
  }
  postTopology(onBoardTRequest: Object): Observable<String> {
    return this.http.post(this.baseUrl + this.topologyInfoUrl, onBoardTRequest, this.httpOptions)
      .pipe(
        tap((topologId: String) => this.authService.log(`added Topology w/ id=${topologId}`, 'SUCCESS', true)),
        catchError(this.authService.handleError<String>('postTopology'))
      );
  }
}
