<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Vehículos</title>
    <link rel="stylesheet" href="${ctx}/assets/css/estilos.css">
</head>
<body>
<div class="contenedor">
    <div class="encabezado">
        <h1>Flota de Vehículos</h1>
        <div class="acciones">
            <a class="btn btn-secundario" href="${ctx}/index.jsp">Inicio</a>
            <a class="btn" href="${ctx}/vehiculos?accion=nuevo">+ Nuevo Vehículo</a>
        </div>
    </div>

    <c:if test="${not empty mensaje}">
        <div class="alerta alerta-exito"><c:out value="${mensaje}" /></div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alerta alerta-error"><c:out value="${error}" /></div>
    </c:if>

    <div class="tabla-contenedor">
        <table class="tabla">
            <thead>
                <tr>
                    <th>Placa</th>
                    <th>Marca / Modelo</th>
                    <th>Año</th>
                    <th>Categoría</th>
                    <th>Transmisión</th>
                    <th>Combustible</th>
                    <th>Sucursal</th>
                    <th>Modalidad</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="v" items="${vehiculos}">
                    <tr>
                        <td><strong><c:out value="${v.placa}" /></strong></td>
                        <td><c:out value="${v.nombreMarca} ${v.modelo}" /></td>
                        <td>${v.anio}</td>
                        <td><c:out value="${v.nombreCategoria}" /></td>
                        <td><c:out value="${v.nombreTransmision}" /></td>
                        <td><c:out value="${v.nombreCombustible}" /></td>
                        <td><c:out value="${v.nombreSucursal}" /></td>
                        <td><c:out value="${v.modalidad}" /></td>
                        <td><span class="badge ${v.nombreEstado == 'Disponible' ? 'badge-activo' : 'badge-inactivo'}"><c:out value="${v.nombreEstado}" /></span></td>
                        <td>
                            <div class="acciones">
                                <a class="btn btn-sm btn-secundario" href="${ctx}/vehiculos?accion=editar&id=${v.vehiculoId}">Editar</a>
                                <form method="post" action="${ctx}/vehiculos" onsubmit="return confirm('¿Eliminar el vehículo con placa ${v.placa}?');">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="${v.vehiculoId}">
                                    <button type="submit" class="btn btn-sm btn-peligro">Eliminar</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty vehiculos and empty error}">
                    <tr><td colspan="10" class="vacio">No hay vehículos registrados. (Asegúrate de haber creado al menos 1 Categoría y 1 Sucursal antes de registrar un auto).</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>