import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Transferencia } from 'src/models/transferencia';
import { TransferenciaService } from 'src/services/transferencia.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  result: any;
  urlToJson = 'http://localhost:8100/api/v1/transferencia';

  transferencia = {} as Transferencia;


  constructor(public transferenciaService: TransferenciaService) { }
  
  ngOnInit() {


    this.getTransferencia();
  }

 
  salvarTransferencia(form: NgForm) {

    this.transferenciaService.salvarTransferencia(this.transferencia).subscribe(() => {

    });
  }


   getTransferencia() {
     this.transferenciaService.getTransferencia().subscribe((transferencias: Transferencia[]) => {
      this.result = transferencias


    });

    
  }

  title = 'angular-http';
}