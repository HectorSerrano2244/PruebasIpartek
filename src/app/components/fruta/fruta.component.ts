import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-fruta',
  templateUrl: './fruta.component.html',
  styleUrls: ['./fruta.component.scss']
})
export class FrutaComponent implements OnInit {

  @Output() llamarPadre = new EventEmitter();
  
  @Input('fruta') fruta: any;
  @Input('frutaComparar') frutaComparar: any;

  constructor() { 

    
  }

  ngOnInit() {
  }

  clickBoton(event, fruta) {
    console.trace('clickBoton');
    this.llamarPadre.emit(fruta);
  }
}