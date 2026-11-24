<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Personas</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fraunces:wght@600;700&family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/assets/css/estilos.css">
    <link rel="stylesheet" href="${ctx}/assets/css/personas.css">
</head>
<body>
<div class="contenedor">
    <div class="encabezado">
        <div>
            <h1>Listado de Personas</h1>
            <p class="subtitulo">Clientes y trabajadores registrados</p>
        </div>
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

    <!-- ===== Filtros ===== -->
    <div class="filtros">
        <div class="filtros-fila">
            <span class="filtros-etiqueta">Mostrar</span>
            <div class="segmento" id="filtroGrupo">
                <button type="button" class="activo" data-valor="TODOS">Todos</button>
                <button type="button" data-valor="CLIENTE">Clientes</button>
                <button type="button" data-valor="TRABAJADOR">Trabajadores</button>
            </div>

            <div class="subfiltro" id="subfiltroClientes">
                <span class="filtros-etiqueta">Tipo</span>
                <div class="segmento" id="filtroSubtipo">
                    <button type="button" class="activo" data-valor="TODOS">Todos</button>
                    <button type="button" data-valor="CLIENTE_NATURAL">Naturales</button>
                    <button type="button" data-valor="CLIENTE_JURIDICO">Jurídicos</button>
                </div>
            </div>

            <div class="buscador">
                <input type="search" id="buscador" placeholder="Buscar por nombre, documento, correo o código..." autocomplete="off">
            </div>
        </div>
    </div>

    <p class="contador">Mostrando <strong id="contadorFilas">0</strong> de <strong>${fn:length(personas)}</strong> personas</p>

    <!-- ===== Tabla ===== -->
    <div class="tabla-contenedor">
        <table class="tabla" id="tablaPersonas">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tipo</th>
                    <th>Nombre / Razón social</th>
                    <th>Documento</th>
                    <th>Contacto</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${personas}">
                    <c:choose>
                        <c:when test="${p.codigoTipo == 'TRABAJADOR'}"><c:set var="grupo" value="TRABAJADOR" /><c:set var="claseTipo" value="tipo-trabajador" /></c:when>
                        <c:when test="${p.codigoTipo == 'CLIENTE_NATURAL'}"><c:set var="grupo" value="CLIENTE" /><c:set var="claseTipo" value="tipo-natural" /></c:when>
                        <c:when test="${p.codigoTipo == 'CLIENTE_JURIDICO'}"><c:set var="grupo" value="CLIENTE" /><c:set var="claseTipo" value="tipo-juridico" /></c:when>
                        <c:otherwise><c:set var="grupo" value="OTRO" /><c:set var="claseTipo" value="tipo-ninguno" /></c:otherwise>
                    </c:choose>
                    <tr data-grupo="${grupo}"
                        data-tipo="${p.codigoTipo}"
                        data-texto="${fn:escapeXml(fn:toLowerCase(p.nombreCompleto))} ${fn:escapeXml(fn:toLowerCase(p.numeroDocumento))} ${fn:escapeXml(fn:toLowerCase(p.correo))} ${fn:escapeXml(fn:toLowerCase(p.codigoTipo == 'TRABAJADOR' ? p.codigoEmpleado : ''))}">
                        <td class="celda-id">${p.personaId}</td>
                        <td><span class="tipo ${claseTipo}"><c:out value="${p.tipoRegistro}" /></span></td>
                        <td>
                            <span class="celda-principal"><c:out value="${p.nombreCompleto}" /></span>
                            <c:if test="${p.codigoTipo == 'TRABAJADOR'}">
                                <span class="celda-secundaria">Cód. <c:out value="${p.codigoEmpleado}" /></span>
                            </c:if>
                        </td>
                        <td>
                            <c:out value="${p.numeroDocumento}" />
                            <span class="celda-secundaria"><c:out value="${p.nombreTipoDocumento}" /></span>
                        </td>
                        <td>
                            <c:out value="${p.correo}" />
                            <c:if test="${not empty p.telefono}">
                                <span class="celda-secundaria"><c:out value="${p.telefono}" /></span>
                            </c:if>
                        </td>
                        <td><span class="badge ${p.estadoUsuario == 'Activo' ? 'badge-activo' : 'badge-inactivo'}"><c:out value="${p.estadoUsuario}" /></span></td>
                        <td>
                            <div class="acciones">
                                <a class="btn btn-sm btn-secundario" href="${ctx}/personas?accion=editar&id=${p.personaId}">Editar</a>
                                <form method="post" action="${ctx}/personas" onsubmit="return confirm('¿Eliminar a la persona con ID ${p.personaId}?');">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="${p.personaId}">
                                    <button type="submit" class="btn btn-sm btn-peligro">Eliminar</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty personas and empty error}">
                    <tr><td colspan="7" class="vacio">No hay personas registradas.</td></tr>
                </c:if>
                <tr id="filaSinResultados" style="display:none;">
                    <td colspan="7" class="vacio">Ninguna persona coincide con los filtros.</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<script src="${ctx}/assets/js/personas.js"></script>
</body>
</html>