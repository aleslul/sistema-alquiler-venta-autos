<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Personas</title>
    <link rel="stylesheet" href="${ctx}/assets/css/estilos.css">
</head>
<body>
<div class="contenedor">
    <div class="encabezado">
        <h1>Listado de Personas</h1>
        <div class="acciones">
            <a class="btn btn-secundario" href="${ctx}/index.jsp">Inicio</a>
            <a class="btn" href="${ctx}/personas?accion=nuevo">+ Nueva Persona</a>
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
                    <th>Tipo Doc.</th>
                    <th>N° Documento</th>
                    <th>Correo</th>
                    <th>Teléfono</th>
                    <th>Dirección</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${personas}">
                    <tr>
                        <td>${p.personaId}</td>
                        <td><c:out value="${p.nombreTipoDocumento}" /></td>
                        <td><c:out value="${p.numeroDocumento}" /></td>
                        <td><c:out value="${p.correo}" /></td>
                        <td><c:out value="${p.telefono}" /></td>
                        <td><c:out value="${p.direccion}" /></td>
                        <td><span class="badge ${p.estadoUsuario == 'Activo' ? 'badge-activo' : 'badge-inactivo'}"><c:out value="${p.estadoUsuario}" /></span></td>
                        <td>
                            <div class="acciones">
                                <a class="btn btn-sm btn-secundario" href="${ctx}/personas?accion=editar&id=${p.personaId}">Editar</a>
                                <form method="post" action="${ctx}/personas" onsubmit="return confirm('¿Eliminar a la persona con documento ${p.numeroDocumento}?');">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="${p.personaId}">
                                    <button type="submit" class="btn btn-sm btn-peligro">Eliminar</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty personas and empty error}">
                    <tr><td colspan="8" class="vacio">No hay personas registradas.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>