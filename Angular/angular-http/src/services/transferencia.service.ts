
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Transferencia } from 'src/models/transferencia';

@Injectable({
  providedIn: 'root'
})
export class TransferenciaService {

  url = 'http://localhost:8100/api/v1/transferencia';

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }


  getTransferencia(): Observable<Transferencia[]> {
    return this.httpClient.get<Transferencia[]>(this.url)
       .pipe(
         retry(2),
         catchError(this.handleError))
  }


  salvarTransferencia(transferencia: Transferencia): Observable<Transferencia> {
    return this.httpClient.post<Transferencia>(this.url, JSON.stringify(transferencia), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }


  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {

      errorMessage = error.error.message;
    } else {

      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };

}