import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'moneda'
})
export class MonedaPipe implements PipeTransform {

  /**
   * Transforma un número añadiendo al final el símbolo de la moneda
   * @param numero número a transformar
   * @param locale string por defecto 'es', cambiamos 
   * <ol>
   *  <li> es => '€'</li>
   *  <li> us => '$'</li>
   * </ol>
   */
  transform(numero: number, locale: string = 'es'): string {
    let simbolo = '';
    switch(locale) {
      case 'es':
        simbolo = '€';
      break;
      case 'us':
        simbolo = '$';
      break;
      case 'uk':
        simbolo = '£';
      break;
      case 'jp':
        simbolo = '¥';
      break;
      case 'in':
        simbolo = '₹';
      break;
      case 's':
        simbolo = '₪';
      break;
    }
    return numero + ' ' + simbolo;
  }

}
