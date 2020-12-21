import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';

import { environment } from '../../environments/environment';
import { Router } from '@angular/router';




@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = environment.baseUrl;


  httpOptions = {
    headers: new HttpHeaders(
      { 'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
  };

  constructor(private http: HttpClient,
    private messageService: MessageService,
    private router: Router) { }

  handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      if (error.status === 401 /*|| error.status == 400*/) {
        if (operation.indexOf('refresh') >= 0 || operation.indexOf('login') >= 0) {
          // TODO: better job of transforming error for user consumption
          this.log(`${operation} failed: ${error.message}`, 'FAILED', false);
          localStorage.removeItem('username');
          this.router.navigate(['/login']).then(() => {
            window.location.reload();
          });
        } else {
          this.log(`${operation} failed: ${error.message}`, 'FAILED', false);
         
        }

      } else {
        if (error.status === 400) {
          if (operation.indexOf('refresh') >= 0 || operation.indexOf('login') >= 0) {
            // TODO: better job of transforming error for user consumption
            this.log(`${operation} failed: ${error.message}`, 'FAILED', false);
            localStorage.removeItem('username');
            this.router.navigate(['/login']).then(() => {
              window.location.reload();
            });
          }
        } else {
            console.log(error.status + ' after ' + operation);
            this.log(`${operation} failed: ${error.error}`, 'FAILED', false);
        }
      }

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


  handleValidatorError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
        if (error.status === 400  || error.status === 500) {
          console.log(error.message + ' after ' + operation);
          this.log(`${error.error.message} failed`, 'FAILED', false);
        } 
      return of(result as T);
    };
  }


  
  /** Log a Service message with the MessageService */
  log(message: string, action: string, reload: boolean) {
    this.messageService.add(`${message}`);
   // this.openSnackBar(`${message}`, action, reload);
  }
}
