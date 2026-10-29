<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="esEdicion" value="${vehiculo.vehiculoId > 0}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${esEdicion ? 'Editar' : 'Nuevo'} Vehículo</title>
    <link rel="stylesheet" href="${ctx}/assets/css/estilos.css">
</head>
<body>
<div class="contenedor contenedor-angosto">
    <div class="encabezado">
        <h1>${esEdicion ? 'Editar Vehículo' : 'Nuevo Vehículo'}</h1>
    </div>

    <c:if test="${not empty error}">
        <div class="alerta alerta-error"><c:out value="${error}" /></div>
    </c:if>

    <c:if test="${not empty errores}">
        <div class="alerta alerta-error">
            <strong>Revise los siguientes datos:</strong>
            <ul class="lista-errores">
                <c:forEach var="e" items="${errores}">
                    <li><c:out value="${e}" /></li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form class="formulario" method="post" action="${ctx}/vehiculos">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="vehiculoId" value="${esEdicion ? vehiculo.vehiculoId : ''}">

        <div class="grid-2">
            <div class="campo">
                <label for="placa">Placa</label>
                <input type="text" id="placa" name="placa" maxlength="20" required value="${fn:escapeXml(vehiculo.placa)}">
            </div>
            <div class="campo">
                <label for="marcaId">Marca</label>
                <select id="marcaId" name="marcaId" required>
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="m" items="${marcas}">
                        <option value="${m.marcaId}" ${m.marcaId == vehiculo.marcaId ? 'selected' : ''}><c:out value="${m.nombre}" /></option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="grid-2">
            <div class="campo">
                <label for="modelo">Modelo</label>
                <input type="text" id="modelo" name="modelo" maxlength="50" required value="${fn:escapeXml(vehiculo.modelo)}">
            </div>
            <div class="campo">
                <label for="anio">Año</label>
                <input type="number" id="anio" name="anio" min="1990" max="2028" required value="${vehiculo.anio > 0 ? vehiculo.anio : 2025}">
            </div>
        </div>

        <div class="grid-2">
            <div class="campo">
                <label for="categoriaId">Categoría</label>
                <select id="categoriaId" name="categoriaId" required>
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="cat" items="${categorias}">
                        <option value="${cat.categoriaId}" ${cat.categoriaId == vehiculo.categoriaId ? 'selected' : ''}><c:out value="${cat.nombre}" /></option>
                    </c:forEach>
                </select>
            </div>
            <div class="campo">
                <label for="sucursalActualId">Sucursal Actual</label>
                <select id="sucursalActualId" name="sucursalActualId" required>
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="suc" items="${sucursales}">
                        <option value="${suc.sucursalId}" ${suc.sucursalId == vehiculo.sucursalActualId ? 'selected' : ''}><c:out value="${suc.nombre}" /></option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="grid-2">
            <div class="campo">
                <label for="tipoTransmisionId">Transmisión</label>
                <select id="tipoTransmisionId" name="tipoTransmisionId" required>
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="tt" items="${tiposTransmision}">
                        <option value="${tt.tipoTransmisionId}" ${tt.tipoTransmisionId == vehiculo.tipoTransmisionId ? 'selected' : ''}><c:out value="${tt.nombre}" /></option>
                    </c:forEach>
                </select>
            </div>
            <div class="campo">
                <label for="tipoCombustibleId">Combustible</label>
                <select id="tipoCombustibleId" name="tipoCombustibleId" required>
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="tc" items="${tiposCombustible}">
                        <option value="${tc.tipoCombustibleId}" ${tc.tipoCombustibleId == vehiculo.tipoCombustibleId ? 'selected' : ''}><c:out value="${tc.nombre}" /></option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="grid-2">
            <div class="campo">
                <label for="numPasajeros">N° de Pasajeros</label>
                <input type="number" id="numPasajeros" name="numPasajeros" min="1" max="20" required value="${vehiculo.numPasajeros > 0 ? vehiculo.numPasajeros : 5}">
            </div>
            <div class="campo">
                <label for="numPuertas">N° de Puertas</label>
                <input type="number" id="numPuertas" name="numPuertas" min="2" max="6" required value="${vehiculo.numPuertas > 0 ? vehiculo.numPuertas : 4}">
            </div>
        </div>

        <div class="grid-2">
            <div class="campo">
                <label for="modalidad">Modalidad del Vehículo</label>
                <select id="modalidad" name="modalidad" required onchange="cambiarModalidad()">
                    <option value="Alquiler" ${vehiculo.modalidad != 'Venta' ? 'selected' : ''}>Para Alquiler</option>
                    <option value="Venta" ${vehiculo.modalidad == 'Venta' ? 'selected' : ''}>Para Venta</option>
                </select>
            </div>
            <div class="campo">
                <label for="estadoVehiculoId">Estado del Vehículo</label>
                <select id="estadoVehiculoId" name="estadoVehiculoId" required>
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="ev" items="${estadosVehiculo}">
                        <option value="${ev.estadoVehiculoId}" ${ev.estadoVehiculoId == vehiculo.estadoVehiculoId ? 'selected' : ''}><c:out value="${ev.nombre}" /></option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- SECCIÓN DINÁMICA: DATOS DE ALQUILER -->
        <div id="seccionAlquiler" style="border-top: 1px solid var(--color-borde); padding-top: 16px; margin-top: 8px;">
            <h3 style="margin: 0 0 12px; font-size: 1.05rem;">Datos de Vehículo en Alquiler</h3>
            <div class="grid-2">
                <div class="campo">
                    <label for="kilometrajeActual">Kilometraje Actual (km)</label>
                    <input type="number" id="kilometrajeActual" name="kilometrajeActual" min="0"
                           value="${vehiculo.kilometrajeActual != null ? vehiculo.kilometrajeActual : 0}">
                </div>
                <div class="campo">
                    <label for="depositoGarantiaBase">Depósito de Garantía Base (S/)</label>
                    <input type="number" id="depositoGarantiaBase" name="depositoGarantiaBase" step="0.01" min="0"
                           value="${vehiculo.depositoGarantiaBase != null ? vehiculo.depositoGarantiaBase : '500.00'}">
                </div>
            </div>
            <div class="campo">
                <label for="tarifaDiariaEspecifica">Tarifa Diaria Específica (S/) (Opcional)</label>
                <input type="number" id="tarifaDiariaEspecifica" name="tarifaDiariaEspecifica" step="0.01" min="0.01"
                       value="${vehiculo.tarifaDiariaEspecifica}">
            </div>
        </div>

        <!-- SECCIÓN DINÁMICA: DATOS DE VENTA -->
        <div id="seccionVenta" style="display:none; border-top: 1px solid var(--color-borde); padding-top: 16px; margin-top: 8px;">
            <h3 style="margin: 0 0 12px; font-size: 1.05rem;">Datos de Vehículo en Venta</h3>
            <div class="grid-2">
                <div class="campo">
                    <label for="precioVenta">Precio de Venta (S/)</label>
                    <input type="number" id="precioVenta" name="precioVenta" step="0.01" min="0.01"
                           value="${vehiculo.precioVenta}">
                </div>
                <div class="campo">
                    <label for="condicion">Condición</label>
                    <select id="condicion" name="condicion">
                        <option value="Nuevo" ${vehiculo.condicion == 'Nuevo' ? 'selected' : ''}>Nuevo</option>
                        <option value="Seminuevo" ${vehiculo.condicion == 'Seminuevo' ? 'selected' : ''}>Seminuevo</option>
                        <option value="Usado" ${vehiculo.condicion == 'Usado' ? 'selected' : ''}>Usado</option>
                    </select>
                </div>
            </div>
            <div class="campo">
                <label for="mesesGarantia">Meses de Garantía</label>
                <input type="number" id="mesesGarantia" name="mesesGarantia" min="0"
                       value="${vehiculo.mesesGarantia != null ? vehiculo.mesesGarantia : 12}">
            </div>
        </div>

        <div class="campo">
            <label>
                <input type="checkbox" name="aireAcondicionado" value="true" ${vehiculo.aireAcondicionado ? 'checked' : ''} style="width:auto;">
                Cuenta con Aire Acondicionado
            </label>
        </div>

        <div class="acciones acciones-formulario">
            <a class="btn btn-secundario" href="${ctx}/vehiculos">Cancelar</a>
            <button type="submit" class="btn">${esEdicion ? 'Actualizar' : 'Registrar'}</button>
        </div>
    </form>
</div>

<script>
function cambiarModalidad() {
    var mod = document.getElementById('modalidad').value;
    var secAlq = document.getElementById('seccionAlquiler');
    var secVen = document.getElementById('seccionVenta');
    if (mod === 'Venta') {
        secAlq.style.display = 'none';
        secVen.style.display = 'block';
    } else {
        secAlq.style.display = 'block';
        secVen.style.display = 'none';
    }
}
cambiarModalidad();
</script>
</body>
</html>