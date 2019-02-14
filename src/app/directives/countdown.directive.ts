import { Directive, HostListener, ElementRef, Input, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Directive({
  selector: '[appCountdown]'
})
export class CountdownDirective {

  @Input('appCountdown') appCountdown:number;

  constructor(private element: ElementRef, private route: ActivatedRoute) { 
    console.trace('Countdown directiva constructor');
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes.appCountdown) {
      console.trace(changes.appCountdown);
      if(changes.appCountdown.currentValue > 6) {
        this.element.nativeElement.style.color = "green";
      }
      else if(changes.appCountdown.currentValue > 3) {
        this.element.nativeElement.style.color = "yellow";
      }
      else {
        this.element.nativeElement.style.color = "red";
      }
    }
  }

}
