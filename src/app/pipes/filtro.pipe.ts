import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filtro'
})
export class FiltroPipe implements PipeTransform {

/**
 * Filtro para frutas
 * @param frutas El array con todas las frutas
 * @param isOferta boolean true mostrar sólo frutas en oferta
 * @param searchText string cadena texto a buscar en el nombre de las frutas, caseInsensitive y cualquier coincidencia
 */
  transform(frutas: any[], isOferta: string, searchText?: string): any[] {
    console.trace('isOferta %s frutas %o searchText %s', isOferta, frutas, searchText);
    let resultado = frutas.map(f => f);

    if(isOferta) {
      resultado = resultado.filter(f => f.oferta);
    }

    if(searchText && searchText !== '') {
      let resultado;
      resultado = resultado.filter(f => { 
        let colores = f.colores.map(c => c.nombre).join('');
        let busqueda = f.nombre + colores;
        return busqueda.toLowerCase().includes(searchText.toLowerCase());
      });
    }

    return resultado;
  }
}
