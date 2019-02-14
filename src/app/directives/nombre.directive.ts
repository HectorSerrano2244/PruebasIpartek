import { Directive, ElementRef, Input, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Directive({
  selector: '[appNombre]'
})
export class NombreDirective {
  
  @Input('appNombre') appNombre:number;

  claseValidar: string;

  constructor( private element: ElementRef, private route: ActivatedRoute) { 

  }

    ngOnChanges(changes : SimpleChanges) {
      if(changes.appNombre) {
        if(changes.appNombre.currentValue.length < 10) {
          this.element.nativeElement.className = "form-control is-invalid";
        }
        else {
          this.element.nativeElement.className  = "form-control is-valid";
        }
      }
    }

}
