import { Injectable } from '@angular/core';
import { Observable, } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { AuthService } from '../../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LightPathsService {
  private baseUrl = environment.baseUrl;
  private topologyInfoUrl='restconf/config/tapi-common:context/tapi-path-computation:path-computation-context';
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

  getLightPaths(): Observable<any> {
    return this.http.get(this.baseUrl + this.topologyInfoUrl,this.httpOptions)
      .pipe(
        tap(_ => console.log('fetched Light Paths info - SUCCESS')),
        catchError(this.authService.handleError('getLightPaths'))
      );
  }
  async getLinks(linkReq): Promise<Observable<any>> {
    return await this.http.post(this.baseUrl + 'restconf/operations/qameleon-topology:get-qam-link',linkReq,this.httpOptions)
      .pipe(
        tap(_ => console.log('fetched link info - SUCCESS')),
        catchError(this.authService.handleError('getLinks'))
      );
  }
  getPathByUuid(uuid): Observable<any> {
    return this.http.get(this.baseUrl + 'restconf/config/tapi-common:context/tapi-path-computation:path-computation-context/path/'+uuid,this.httpOptions)
      .pipe(
        tap(_ => console.log('fetched link info by uuid - SUCCESS')),
        catchError(this.authService.handleError('getLinksByUuid'))
      );
  }
  postLightPath(onBoardTRequest: Object): Observable<String> {
    return this.http.post(this.baseUrl +'restconf/operations/tapi-path-computation:compute-p-2-p-path', onBoardTRequest, this.httpOptions)
      .pipe(
        tap((topologId: String) => this.authService.log(`added Topology w/ id=${topologId}`, 'SUCCESS', true)),
        catchError(this.authService.handleError<String>('postTopology'))
      );
  }
  deleteLightPath(reqBody): Observable<String> {
    return this.http.post(this.baseUrl +'restconf/operations/tapi-path-computation:delete-p-2-p-path', reqBody, this.httpOptions)
      .pipe(
        tap((liId: String) => this.authService.log(`remove light path w/ id=${liId}`, 'SUCCESS', true)),
        catchError(this.authService.handleError<String>('deleteLightPath'))
      );
  }
}
