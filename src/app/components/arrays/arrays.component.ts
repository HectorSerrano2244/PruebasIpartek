import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-arrays',
  templateUrl: './arrays.component.html',
  styleUrls: ['./arrays.component.scss']
})

export class ArraysComponent implements OnInit {

isOferta: boolean;
frutas: any[];
f_nombres: any[];
f_precios: any[];
f_precios_nombre: any[];
f_oferta: any[];
f_no_oferta: any[];
f_index_par: any[];
f_index_impar: any[];
f_total_oferta: any[];
f_total: any[];
f_por_color: any[];
textcolor: string;

  constructor() { 
    this.isOferta = false;
    console.trace('ArraysComponent constructor');
    this.frutas = [
      {
        "nombre": "fresa",
        "oferta": true,
        "precio": 3.59,
        "descuento": 5,
        "imagen": "https://media.mercola.com/assets/images/foodfacts/strawberry-nutrition-facts.jpg",
        "colores": [
        { "nombre": "rojo" , "codigo": "#F00"}
        ]
      },
      {
        "nombre": "pomelo",
        "oferta": false,
        "precio": 7.43,
        "descuento": 7,
        "imagen": "http://frutasfercas.com/wp-content/uploads/2018/03/pomelo-1.jpg",
        "colores": [
        {"nombre": "rojo", "codigo":"#F00"},
        {"nombre": "naranja", "codigo":"#FFA500"}
        ]
      },
      {
        "nombre": "chirimoya",
        "oferta": true,
        "precio": 10,
        "descuento": 0,
        "imagen": "https://media.mercola.com/assets/images/foodfacts/cherimoya-nutrition-facts.jpg",
        "colores": [
        {"nombre": "verde", "codigo":"#0F0"}
        ]
      },
      {
        "nombre": "manzana",
        "oferta": true,
        "precio": 5.59,
        "descuento": 3.5,
        "imagen": "https://www.comenaranjas.com/images/stories/virtuemart/product/manzana-royal.jpg",
        "colores": [
        {"nombre": "verde", "codigo":"#0F0"},
        {"nombre": "rojo", "codigo":"#F00"},
        {"nombre": "amarillo", "codigo":"#FF0"}
        ]
      },
      {
        "nombre": "tamarindo",
        "oferta": true,
        "precio": 13.70,
        "descuento": 3,
        "imagen": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTuiEhT2_yFMqcz3TlSGzmwVqrOXJnO1gdlEd5ErYl4ynfte4J",
        "colores": [
        {"nombre": "negro", "codigo":"#000"},
        {"nombre": "verde", "codigo":"#0F0"}
        ]
      }      
    ];
    console.trace('Comenzamos a mapear');
    this.f_nombres = this.frutas.map((fruta, index, array) => { 
      console.debug('index '+index);
      console.debug('value '+fruta);
      console.debug('array '+array);
      return fruta.nombre 
    }); 

    this.f_precios = this.frutas.map(fruta => { 
      return fruta.precio
    }); 

    this.f_precios_nombre = this.frutas.map(fruta => {    
      return {
        "nombre": fruta.nombre,
        "precio": fruta.precio
      } 
    });
    this.f_por_color = this.frutas.filter(f => f.oferta
    ).map(f => {
      return f.nombre
    });
    this.f_oferta = this.frutas.filter(f => f.oferta).map(f => {return f.nombre});
    this.f_no_oferta = this.frutas.filter(f => !f.oferta).map(f => {return f.nombre});
    this.f_index_par = this.frutas.filter((f, i) => {
      if(i % 2 == 0) {
        return f.nombre;
      } 
    }).map(f => {return f.nombre});
    this.f_index_impar = this.frutas.filter((f, i) => {
      if(i % 2 != 0) {
        return f.nombre;
      } 
    }).map(f => {return f.nombre});

    this.f_total = this.frutas.map(f => f.precio).reduce((previous, current) => { 
      return previous + current;
    }, 0);

    this.f_total_oferta = this.frutas.filter(f => f.oferta
      ).map(f => f.precio
      ).reduce((previous, current) => { 
        return previous + current;
      }, 0);
  }

  calcularDescuento(frutas: any) {
    return frutas.precio - ((frutas.precio * frutas.descuento) / 100);
  }

  cambiarOferta(value: boolean) {
    this.isOferta = value;
    return this.isOferta;
  }

  ngOnInit() {
  }

}
