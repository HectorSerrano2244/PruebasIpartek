import { Component, OnInit, ElementRef, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-flujo',
  templateUrl: './flujo.component.html',
  styleUrls: ['./flujo.component.scss']
})
export class FlujoComponent implements OnInit {

  // Declaramos dentro de la class
  expresion: string;
  ocultar: boolean;
  claseModelo: string;
  claseValidar: string;
  nombre: string;

  constructor(private element: ElementRef) { 
    // Inicializamos en el constructor
    console.trace('FlujoComponent constructor');
    this.expresion = "Soy una variable inicializada en el modelo";
    this.ocultar = true;
    this.nombre = 'min 10 chars';
  }

  borrar() {
    this.nombre = "";
  }

  changeClass(claseSeleccionada: string) {
    this.claseModelo = claseSeleccionada;
  }

  ngOnInit() {
    console.trace('FlujoComponent ngOnInit');
    this.claseValidar = "form-control";
  }

}
