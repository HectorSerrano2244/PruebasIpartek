import { Component, OnInit } from '@angular/core';
import { Persona } from '../../model/persona';
    
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  manolo: Persona;

  constructor() { 
    this.manolo = new Persona();
    console.debug('manolo sin inicializar %o', this.manolo);
    this.manolo.nombre = "Manolo";
    console.debug("Dame tu nombre ", this.manolo.nombre);
  }

  ngOnInit() {
  }

}
