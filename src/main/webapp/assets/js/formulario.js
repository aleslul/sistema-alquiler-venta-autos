
(function () {
    var selectTipo = document.getElementById('tipoRegistro');
    var secciones = document.querySelectorAll('.seccion-tipo');

    function mostrarSeccion() {
        var tipo = selectTipo.value;
        secciones.forEach(function (s) {
            s.style.display = (s.getAttribute('data-tipo') === tipo) ? 'block' : 'none';
        });
    }

    selectTipo.addEventListener('change', mostrarSeccion);
    mostrarSeccion();
})();