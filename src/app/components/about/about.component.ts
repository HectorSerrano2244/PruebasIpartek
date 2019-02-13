import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {

  repeticiones: number;

  constructor(private route: ActivatedRoute) { 
    console.trace('AboutComponent constructor');
    this.repeticiones = 0;
    
    
    }

  ngOnInit() {
    this.route.paramMap.subscribe( params =>{
      this.repeticiones = +params.get('repeticiones'); 
    });
    // usamos (+) para hacer parseInt. Devuelve (NaN) si no es un número
        //this.repeticiones = +this.route.snapshot.params.repeticiones;
  }

}
