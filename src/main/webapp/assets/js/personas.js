
(function () {
    var filas = Array.prototype.slice.call(document.querySelectorAll('#tablaPersonas tbody tr[data-grupo]'));
    var filaVacia = document.getElementById('filaSinResultados');
    var buscador = document.getElementById('buscador');
    var contador = document.getElementById('contadorFilas');
    var subfiltro = document.getElementById('subfiltroClientes');

    var grupo = 'TODOS';     // TODOS | CLIENTE | TRABAJADOR
    var subtipo = 'TODOS';   // TODOS | CLIENTE_NATURAL | CLIENTE_JURIDICO

    function aplicar() {
        var texto = buscador.value.trim().toLowerCase();
        var visibles = 0;

        filas.forEach(function (fila) {
            var ok = true;
            if (grupo !== 'TODOS' && fila.getAttribute('data-grupo') !== grupo) ok = false;
            if (ok && grupo === 'CLIENTE' && subtipo !== 'TODOS' && fila.getAttribute('data-tipo') !== subtipo) ok = false;
            if (ok && texto && fila.getAttribute('data-texto').indexOf(texto) === -1) ok = false;

            fila.style.display = ok ? '' : 'none';
            if (ok) visibles++;
        });

        contador.textContent = visibles;
        if (filaVacia) filaVacia.style.display = (visibles === 0 && filas.length > 0) ? '' : 'none';
    }

    function activar(contenedor, boton) {
        contenedor.querySelectorAll('button').forEach(function (b) { b.classList.remove('activo'); });
        boton.classList.add('activo');
    }

    document.querySelectorAll('#filtroGrupo button').forEach(function (boton) {
        boton.addEventListener('click', function () {
            grupo = boton.getAttribute('data-valor');
            activar(document.getElementById('filtroGrupo'), boton);

            // El sub-filtro solo tiene sentido para clientes
            subfiltro.classList.toggle('visible', grupo === 'CLIENTE');
            if (grupo !== 'CLIENTE') {
                subtipo = 'TODOS';
                activar(document.getElementById('filtroSubtipo'),
                        document.querySelector('#filtroSubtipo button[data-valor="TODOS"]'));
            }
            aplicar();
        });
    });

    document.querySelectorAll('#filtroSubtipo button').forEach(function (boton) {
        boton.addEventListener('click', function () {
            subtipo = boton.getAttribute('data-valor');
            activar(document.getElementById('filtroSubtipo'), boton);
            aplicar();
        });
    });

    buscador.addEventListener('input', aplicar);
    aplicar();
})();