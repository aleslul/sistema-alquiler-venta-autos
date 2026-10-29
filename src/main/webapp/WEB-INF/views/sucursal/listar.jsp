<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sucursales</title>
    <link rel="stylesheet" href="${ctx}/assets/css/estilos.css">
</head>
<body>
<div class="contenedor">
    <div class="encabezado">
        <h1>Listado de Sucursales</h1>
        <div class="acciones">
            <a class="btn btn-secundario" href="${ctx}/index.jsp">Inicio</a>
            <a class="btn" href="${ctx}/sucursales?accion=nuevo">+ Nueva Sucursal</a>
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
                    <th>ID</th>
                    <th>Empresa</th>
                    <th>Nombre Sede</th>
                    <th>Dirección</th>
                    <th>Horario</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${sucursales}">
                    <tr>
                        <td>${s.sucursalId}</td>
                        <td><c:out value="${s.nombreEmpresa}" /></td>
                        <td><c:out value="${s.nombre}" /></td>
                        <td><c:out value="${s.direccion}" /></td>
                        <td><c:out value="${s.horarioAtencion}" /></td>
                        <td><span class="badge ${s.estadoSucursal == 'Activa' ? 'badge-activo' : 'badge-inactivo'}"><c:out value="${s.estadoSucursal}" /></span></td>
                        <td>
                            <div class="acciones">
                                <a class="btn btn-sm btn-secundario" href="${ctx}/sucursales?accion=editar&id=${s.sucursalId}">Editar</a>
                                <form method="post" action="${ctx}/sucursales" onsubmit="return confirm('¿Eliminar la sucursal ${s.nombre}?');">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="${s.sucursalId}">
                                    <button type="submit" class="btn btn-sm btn-peligro">Eliminar</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty sucursales and empty error}">
                    <tr><td colspan="7" class="vacio">No hay sucursales registradas.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>