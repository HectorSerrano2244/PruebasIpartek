import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-saludo',
  templateUrl: './saludo.component.html',
  styleUrls: ['./saludo.component.scss']
})
export class SaludoComponent implements OnInit {

  nombre: any;
  apellido1: any;
  apellido2: any;

  constructor(private route: ActivatedRoute) { 
    this.nombre = "";
    this.apellido1 = "";
    this.apellido2 = "";
  }

  ngOnInit() {
    this.route.paramMap.subscribe( params =>{
      this.nombre = params.get('nombre'); 
    });
    this.route.paramMap.subscribe( params =>{
      this.apellido1 = params.get('apellido1'); 
    });
    this.route.paramMap.subscribe( params =>{
      this.apellido2 = params.get('apellido2'); 
    });
  }

}
