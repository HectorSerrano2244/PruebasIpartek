function cambiarAnyo() {
	anyoSeleccionado = document.querySelector("#anyo").value;
	console.log(anyoSeleccionado);
	window.location = 'privado/estadisticas?anyoCombo='+anyoSeleccionado;
}