
//variables globales
var ulVehiculos;
var formEditar = document.querySelector('#formEditar');
var formCrear = document.querySelector('#formCrear');
var vehiculos = [];
var vehiculoSeleccionado;

const ENDPOINT = 'http://localhost:8080/wsrest/api/vehiculo/';


window.addEventListener('load', function(){

    console.info('documento cargado y listo');    
    // console.trace('esto es un trace');
    // console.debug('esto es un debug');
    // console.warn('esto es un waring');
    // console.error('esto es un ERROR');
    
    ulVehiculos = document.getElementById('ulVehiculo');

    refrescarLista();

});


function refrescarLista(){

    console.trace('refrescarLista');
    ulVehiculos.innerHTML = '<li class="list-group-item">Cargando vehiculos....</li>';
    let div = `<h2>Formulario</h2>
    <div>
        <input class="form-control" type="text" id="matricula" placeholder="BI000KM">
        <input class="form-control mt-3" type="text" id="modelo">
        <input class="form-control mt-3" type="number" id="km">
        <button class="btn btn-outline-primary btn-block mt-4" onclick="crear('POST')">Crear</button>
    </div>`;
    formCrear.innerHTML = div;
    formEditar.innerHTML = "";

    let xhr = new XMLHttpRequest();    
    xhr.onreadystatechange = function(){ 
        if (xhr.readyState == 4 && xhr.status == 200 ){               
        let lis = '';
        vehiculos = JSON.parse(xhr.responseText);
        vehiculos.forEach( (vehiculo, index) => {
            lis += `<li class="list-group-item">
                        <span class="matricula">${vehiculo.matricula}</span> 
                        <span class="modelo">${vehiculo.modelo}</span>
                        <span class="km">${vehiculo.km} KM</span>
                        <a href="#" onclick="editar(${index}))">Editar</a>
                        <a href="#" onclick="eliminar(${vehiculo.id})">Eliminar</a>
                    </li>`;
        });
        ulVehiculos.innerHTML = lis;
        }    
    };
    xhr.open('GET', ENDPOINT );    
    xhr.send();
} // refrescarLista

function editar(index){
    let vehiculo = vehiculos[index];
    console.log('click detalle %o', vehiculo);            
    let div = `<h2>Formulario</h2>
    <div>
        <input class="form-control" type="text" id="matricula" placeholder="BI000KM">
        <input class="form-control mt-3" type="text" id="modelo">
        <input class="form-control mt-3" type="number" id="km">
        <button class="btn btn-outline-primary btn-block mt-4" onclick="crear('PUT')">Editar</button>
        <button class="btn btn-outline-secondary btn-block mt-4" onclick="refrescarLista()">Volver a Crear</button>
    </div>`;
    formCrear.innerHTML = "";
    formEditar.innerHTML = div;
    document.querySelector('#matricula').value = vehiculo.matricula;
    document.querySelector('#modelo').value = vehiculo.modelo;
    document.querySelector('#km').value = vehiculo.km;
}

function modificar() {
    let vehiculo = vehiculos[index];
    console.log('click detalle %o', vehiculo);

    let xhr = new XMLHttpRequest();    
    xhr.onreadystatechange = function(){ 
        if (xhr.readyState == 4 && xhr.status == 200 ) {               

        }
    };
    xhr.open('PUT', ENDPOINT + idVehiculo );    
    xhr.send();
}

function eliminar( idVehiculo ){

   
    console.log('click Eliminar %o', idVehiculo );

    if ( confirm('¿Estas Seguro?') ){
        let xhr = new XMLHttpRequest();    
        xhr.onreadystatechange = function(){ 
            if (xhr.readyState == 4) {
                switch(xhr.status) {  
                    case 201:    
                        showAlert('Coche eliminado con éxito', 'success');         
                        refrescarLista();
                    break;
                    case 404:
                        showAlert('No existe el coche', 'warning');
                    break;
                    case 409:
                        showAlert('CONFLICTO existen multas asociadas', 'warning');
                    break;
                    default:
                        showAlert('Error inesperado', 'warning');
                    break;
                }   
            }    
        };
        xhr.open('DELETE', ENDPOINT + idVehiculo );    
        xhr.send();
    }    
    
} // eliminar


function crear(accion){
    console.log('click crear' );

    let matricula = document.querySelector('#matricula').value;
    let modelo = document.querySelector('#modelo').value;
    let km = document.querySelector('#km').value;

    let jsonCoche = {
        "matricula" : matricula,
        "modelo": modelo,
        "km": km
    };

   let xhr = new XMLHttpRequest();    
   xhr.onreadystatechange = function(){ 
        if (xhr.readyState == 4 ){   
            console.debug(' STATUS ' + xhr.status );
            switch(xhr.status) {
            case 201:
                if (accion == 'POST') {
                    showAlert('Coche creado con éxito');
                }
                else {
                    showAlert('Coche modificado con éxito');
                }
                refrescarLista();
            break;
            case 400:
                if (accion == 'POST') {
                    showAlert('No se pudo crear el coche', 'warning');
                }
                else {
                    showAlert('No se pudo modificar el coche');
                }
            break;
            default:
                showAlert('Error inesperado', 'warning');
            break;
            }
        }   
   };
   xhr.open('POST', ENDPOINT );
   xhr.setRequestHeader("Content-type", "application/json");
   xhr.send( JSON.stringify(jsonCoche) );

};


function showAlert( texto, tipo = 'info' ){

    
   let alertHTML = `<div class="alert alert-${tipo} alert-dismissible fade show" role="alert">
        <p>${texto}</p>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        </div>`;

    document.getElementById('alert').innerHTML = alertHTML;


}// showAlert