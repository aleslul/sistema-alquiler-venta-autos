<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="esEdicion" value="${sucursal.sucursalId > 0}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${esEdicion ? 'Editar' : 'Nueva'} Sucursal</title>
    <link rel="stylesheet" href="${ctx}/assets/css/estilos.css">
</head>
<body>
<div class="contenedor contenedor-angosto">
    <div class="encabezado">
        <h1>${esEdicion ? 'Editar Sucursal' : 'Nueva Sucursal'}</h1>
    </div>

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

    <form class="formulario" method="post" action="${ctx}/sucursales">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="sucursalId" value="${esEdicion ? sucursal.sucursalId : ''}">

        <div class="grid-2">
            <div class="campo">
                <label for="empresaId">Empresa</label>
                <select id="empresaId" name="empresaId" required>
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="emp" items="${empresas}">
                        <option value="${emp.empresaId}" ${emp.empresaId == sucursal.empresaId ? 'selected' : ''}>
                            <c:out value="${emp.razonSocial}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="campo">
                <label for="nombre">Nombre de Sucursal</label>
                <input type="text" id="nombre" name="nombre" maxlength="100" required value="${fn:escapeXml(sucursal.nombre)}">
            </div>
        </div>

        <div class="campo">
            <label for="direccion">Dirección</label>
            <input type="text" id="direccion" name="direccion" maxlength="200" required value="${fn:escapeXml(sucursal.direccion)}">
        </div>

        <div class="grid-2">
            <div class="campo">
                <label for="horarioAtencion">Horario de Atención</label>
                <input type="text" id="horarioAtencion" name="horarioAtencion" maxlength="100" placeholder="Ej. Lun-Sab 8:00 - 20:00" value="${fn:escapeXml(sucursal.horarioAtencion)}">
            </div>
            <div class="campo">
                <label for="estadoSucursal">Estado</label>
                <select id="estadoSucursal" name="estadoSucursal" required>
                    <option value="Activa" ${sucursal.estadoSucursal == 'Activa' ? 'selected' : ''}>Activa</option>
                    <option value="Inactiva" ${sucursal.estadoSucursal == 'Inactiva' ? 'selected' : ''}>Inactiva</option>
                </select>
            </div>
        </div>

        <div class="acciones acciones-formulario">
            <a class="btn btn-secundario" href="${ctx}/sucursales">Cancelar</a>
            <button type="submit" class="btn">${esEdicion ? 'Actualizar' : 'Registrar'}</button>
        </div>
    </form>
</div>
</body>
</html>