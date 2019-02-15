import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pipes',
  templateUrl: './pipes.component.html',
  styleUrls: ['./pipes.component.scss']
})
export class PipesComponent implements OnInit {

  nombre: string;
  nombreTrim: string;
  numero: number;
  frutas: string[];

  constructor() { 
    console.trace('PipesComponent constructor');
    this.nombre = "mI NOmbRE";
    this.nombreTrim = "         Hola             Que                  Tal  ";
    this.numero = 2.5673432;
  }

  ngOnInit() {
    console.trace('ngOnInit constructor');
  }

}
