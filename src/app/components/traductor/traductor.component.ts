import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-traductor',
  templateUrl: './traductor.component.html',
  styleUrls: ['./traductor.component.scss']
})
export class TraductorComponent implements OnInit {

  @Input('idioma') idioma: string;
  @Input('texto') texto: string;

  // Evento de salida, este evento se debe capturar en componentePadre
  @Output() llamarPadre = new EventEmitter();

  constructor() { 
    console.trace("TraductorComponent constructor");
  }

  ngOnInit() {
    console.trace("TraductorComponent ngOnInit");
  }
  /**
   * Al realziar click sobre el boton, tenemos que emitir un evento para avisar al padre
   * usando el nombre del @Output llamarPadre del tipo EventEmitter
   * @param event 
   */
  clickBoton(event) {
    let datosEnviar = {
      'idioma': this.idioma,
      'texto': this.texto
    }
    this.llamarPadre.emit(datosEnviar);
  }
}
