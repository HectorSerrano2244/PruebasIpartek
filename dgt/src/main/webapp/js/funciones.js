/* cambiar año*/
function cambiarAnyo() {
	anyoSeleccionado = document.querySelector("#anyo").value;
	console.log(anyoSeleccionado);
	window.location = 'privado/estadisticas?anyocombo='+anyoSeleccionado;
}

/*decimales*/
$("input.number").on("keydown", function (e) {
    // allow function keys and decimal separators
    if (
        // backspace, delete, tab, escape, enter, comma and .
        $.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 188, 190]) !== -1 ||
        // Ctrl/cmd+A, Ctrl/cmd+C, Ctrl/cmd+X
        ($.inArray(e.keyCode, [65, 67, 88]) !== -1 && (e.ctrlKey === true || e.metaKey === true)) ||
        // home, end, left, right
        (e.keyCode >= 35 && e.keyCode <= 39)) {

        return;
    }
    // block any non-number
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
});
/* contador text area*/ 
console.log('funcionando');
let label;
let concepto; // textarea

const MAX_CARACTERES = 255; // TODO cambiar por 250 despues de las pruebas
const MIN_CARACTERES = 10;

window.addEventListener('load', function() {

	console.log('el DOM cargado y listo');
	label = document.querySelector('#contadorLabel');
	concepto = document.querySelector('#concepto');

	let caracteres = concepto.value.length;
	let mensaje = "";
	label.textContent = `(${caracteres}/${MAX_CARACTERES})`;
	concepto.maxLength = `${MAX_CARACTERES}`;
	
	if (caracteres <= MIN_CARACTERES) {
		label.style.color = 'red';
		label.textContent = `(${caracteres}/${MAX_CARACTERES}) Minimo ${MIN_CARACTERES} caracteres`;
	} else if (caracteres >= MIN_CARACTERES) {
		label.style.color = 'green';
		label.textContent = `(${caracteres}/${MAX_CARACTERES}) Correcto`;
	}

	if (caracteres >= (MAX_CARACTERES / 1.25)) {
		label.textContent = `(${caracteres}/${MAX_CARACTERES}) Estas cerca del limite`;
	}
	if (caracteres >= MAX_CARACTERES) {
		label.textContent = `(${caracteres}/${MAX_CARACTERES}) Llegaste al limite`;
	}

	concepto.addEventListener("keyup", function() {
		let caracteres = concepto.value.length;
		// console.log('keypress ' + caracteres);
		label.textContent = `(${caracteres}/${MAX_CARACTERES})`;

		if (caracteres <= MIN_CARACTERES) {
			label.style.color = 'red';
			label.textContent = `(${caracteres}/${MAX_CARACTERES}) Minimo ${MIN_CARACTERES} caracteres`;
		} else if (caracteres >= MIN_CARACTERES) {
			label.style.color = 'green';
			label.textContent = `(${caracteres}/${MAX_CARACTERES}) Correcto`;
		}

		if (caracteres >= (MAX_CARACTERES / 1.25)) {
			label.textContent = `(${caracteres}/${MAX_CARACTERES}) Estas cerca del limite`;
		}
		if (caracteres >= MAX_CARACTERES) {
			label.textContent = `(${caracteres}/${MAX_CARACTERES}) Llegaste al limite`;
		}

	});

});
/*preloader*/
jQuery(document).ready(function(){
    jQuery(".preloader").delay(1000).fadeOut("slow").delay(1000, function(){
      jQuery(this).remove();
    });
});

/*barraaaaaa*/

$(document).ready(function () {
	  var trigger = $('.hamburger'),
	      overlay = $('.overlay'),
	     isClosed = false;

	    trigger.click(function () {
	      hamburger_cross();      
	    });

	    function hamburger_cross() {

	      if (isClosed == true) {          
	        overlay.hide();
	        trigger.removeClass('is-open');
	        trigger.addClass('is-closed');
	        isClosed = false;
	      } else {   
	        overlay.show();
	        trigger.removeClass('is-closed');
	        trigger.addClass('is-open');
	        isClosed = true;
	      }
	  }
	  
	  $('[data-toggle="offcanvas"]').click(function () {
	        $('#wrapper').toggleClass('toggled');
	  });  
	});