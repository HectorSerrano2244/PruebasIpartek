import { Component, OnInit } from '@angular/core';
const TIEMPO_JUEGO: number = 10;
@Component({
  selector: 'app-juego',
  templateUrl: './juego.component.html',
  styleUrls: ['./juego.component.scss']
})
export class JuegoComponent implements OnInit {

  // variable tipo number
  
  contador: number;
  tiempo: number;
  interval;

  constructor() {
    console.trace('JuegoComponent constructor');
    // inicializar las variables
    this.contador = 0;
    this.tiempo = TIEMPO_JUEGO;
  }

  ngOnInit() {
    console.trace('PruebaComponent ngOnInit');
  }

  sumar() {
    console.trace('click botón sumar');
    if (this.tiempo > 0) {
      this.contador++;
    }
  }

  restart() {
    this.tiempo = TIEMPO_JUEGO;
    this.contador = 0;
    clearInterval(this.interval);
  }

  iniciar() {
    clearInterval(this.interval);
    this.tiempo = TIEMPO_JUEGO;
    this.contador = 0;
    this.interval = setInterval(() => {
      if (this.tiempo > 0) {
        this.tiempo--;
      }
    },1000)
  }
}