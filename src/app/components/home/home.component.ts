import { Component, OnInit } from '@angular/core';
import { Persona } from '../../model/persona';
    
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  manolo: Persona;

  idiomas: string[];
  idiomaSeleccionado: string;
  textoTraducir: string;

  constructor() { 
    console.trace('Constructor HomeComponent');
    this.idiomas = ['eu', 'es', 'en'];
    this.idiomaSeleccionado = this.idiomas[0]; // eu
    this.textoTraducir = "";

    this.manolo = new Persona();
    console.debug('manolo sin inicializar %o', this.manolo);
    this.manolo.nombre = "Manolo";
    console.debug("Dame tu nombre ", this.manolo.nombre);
  }

  escucho(event) {
    alert('Acabo de recibir un evento del Hijo. Idioma: '+event.idioma+' Texto: '+event.texto);
  }

  cambiarIdioma(idioma: string) {
    this.idiomaSeleccionado = idioma;
  }

  ngOnInit() {
    console.trace('ngOnInit HomeComponent');
  }

}
