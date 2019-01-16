console.log('funcionando');
let label;
let concepto; // textarea

const MAX_CARACTERES = 50; // TODO cambiar por 250 despues de las pruebas
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
	} else if (caracteres >= MIN_CARACTERES) {
		label.style.color = 'green';
	}

	if (caracteres >= (MAX_CARACTERES / 2)) {

	}
	if (caracteres >= MAX_CARACTERES) {

	}

	concepto.addEventListener("keyup", function() {
		let caracteres = concepto.value.length;
		// console.log('keypress ' + caracteres);
		label.textContent = `(${caracteres}/${MAX_CARACTERES})`;

		if (caracteres <= MIN_CARACTERES) {
			label.style.color = 'red';
		} else if (caracteres >= MIN_CARACTERES) {
			label.style.color = 'green';
		}

		if (caracteres >= (MAX_CARACTERES / 2)) {

		}
		if (caracteres >= MAX_CARACTERES) {

		}

	});

});
