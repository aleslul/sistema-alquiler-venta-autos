<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="esEdicion" value="${persona.personaId > 0}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${esEdicion ? 'Editar' : 'Nueva'} Persona</title>
    <link rel="stylesheet" href="${ctx}/assets/css/estilos.css">
</head>
<body>
<div class="contenedor contenedor-angosto">
    <div class="encabezado">
        <h1>${esEdicion ? 'Editar Persona' : 'Nueva Persona'}</h1>
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

    <form class="formulario" method="post" action="${ctx}/personas">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="personaId" value="${esEdicion ? persona.personaId : ''}">

        <div class="grid-2">
            <div class="campo">
                <label for="tipoDocumentoId">Tipo de Documento</label>
                <select id="tipoDocumentoId" name="tipoDocumentoId" required>
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="td" items="${tiposDocumento}">
                        <option value="${td.tipoDocumentoId}" ${td.tipoDocumentoId == persona.tipoDocumentoId ? 'selected' : ''}>
                            <c:out value="${td.nombre}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="campo">
                <label for="numeroDocumento">Número de Documento</label>
                <input type="text" id="numeroDocumento" name="numeroDocumento" maxlength="50" required value="${fn:escapeXml(persona.numeroDocumento)}">
            </div>
        </div>

        <div class="grid-2">
            <div class="campo">
                <label for="correo">Correo Electrónico</label>
                <input type="email" id="correo" name="correo" maxlength="100" required value="${fn:escapeXml(persona.correo)}">
            </div>
            <div class="campo">
                <label for="telefono">Teléfono</label>
                <input type="text" id="telefono" name="telefono" maxlength="20" value="${fn:escapeXml(persona.telefono)}">
            </div>
        </div>

        <div class="campo">
            <label for="direccion">Dirección</label>
            <input type="text" id="direccion" name="direccion" maxlength="200" value="${fn:escapeXml(persona.direccion)}">
        </div>

        <div class="grid-2">
            <div class="campo">
                <label for="estadoUsuario">Estado de Usuario</label>
                <select id="estadoUsuario" name="estadoUsuario" required>
                    <option value="Activo" ${persona.estadoUsuario == 'Activo' ? 'selected' : ''}>Activo</option>
                    <option value="Inactivo" ${persona.estadoUsuario == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                </select>
            </div>
            <div class="campo">
                <label for="passwordHash">Contraseña (Opcional)</label>
                <input type="password" id="passwordHash" name="passwordHash" maxlength="255" value="${fn:escapeXml(persona.passwordHash)}">
            </div>
        </div>

        <div class="acciones acciones-formulario">
            <a class="btn btn-secundario" href="${ctx}/personas">Cancelar</a>
            <button type="submit" class="btn">${esEdicion ? 'Actualizar' : 'Registrar'}</button>
        </div>
    </form>
</div>
</body>
</html>