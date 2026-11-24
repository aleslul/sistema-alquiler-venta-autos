<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="esEdicion" value="${persona.personaId > 0}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${esEdicion ? 'Editar' : 'Nueva'} Persona</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fraunces:wght@600;700&family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/assets/css/estilos.css">
    <link rel="stylesheet" href="${ctx}/assets/css/personas.css">
</head>
<body>
<div class="contenedor contenedor-angosto">
    <div class="encabezado">
        <div>
            <h1>${esEdicion ? 'Editar Persona' : 'Nueva Persona'}</h1>
            <p class="subtitulo">Complete los datos generales y los del rol seleccionado</p>
        </div>
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

    <form class="formulario" method="post" action="${ctx}/personas">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="personaId" value="${esEdicion ? persona.personaId : ''}">

        <!-- ===== Datos generales de la persona ===== -->
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

        <!-- ===== Tipo de registro ===== -->
        <div class="campo">
            <label for="tipoRegistro">¿Qué es esta persona?</label>
            <select id="tipoRegistro" name="tipoRegistro" required>
                <option value="">-- Seleccione un rol --</option>
                <option value="CLIENTE_NATURAL" ${tipoRegistro == 'CLIENTE_NATURAL' ? 'selected' : ''}>Cliente natural</option>
                <option value="CLIENTE_JURIDICO" ${tipoRegistro == 'CLIENTE_JURIDICO' ? 'selected' : ''}>Cliente jurídico</option>
                <option value="TRABAJADOR" ${tipoRegistro == 'TRABAJADOR' ? 'selected' : ''}>Trabajador</option>
            </select>
        </div>

        <!-- ===== Cliente natural ===== -->
        <div class="seccion-tipo" data-tipo="CLIENTE_NATURAL" style="display:none;">
            <h3>Datos del cliente natural</h3>
            <div class="grid-2">
                <div class="campo">
                    <label for="cn_nombres">Nombres</label>
                    <input type="text" id="cn_nombres" name="cn_nombres" maxlength="100" value="${fn:escapeXml(clienteNatural.nombres)}">
                </div>
                <div class="campo">
                    <label for="cn_apellidos">Apellidos</label>
                    <input type="text" id="cn_apellidos" name="cn_apellidos" maxlength="100" value="${fn:escapeXml(clienteNatural.apellidos)}">
                </div>
            </div>
            <div class="grid-2">
                <div class="campo">
                    <label for="cn_fechaNacimiento">Fecha de nacimiento</label>
                    <input type="date" id="cn_fechaNacimiento" name="cn_fechaNacimiento" value="${clienteNatural.fechaNacimiento}">
                </div>
                <div class="campo">
                    <label for="cn_licencia">Licencia de conducir (opcional)</label>
                    <input type="text" id="cn_licencia" name="cn_licencia" maxlength="50" value="${fn:escapeXml(clienteNatural.licenciaConducir)}">
                </div>
            </div>
            <div class="campo">
                <label for="cn_fechaVencLicencia">Vencimiento de la licencia (opcional)</label>
                <input type="date" id="cn_fechaVencLicencia" name="cn_fechaVencLicencia" value="${clienteNatural.fechaVencimientoLicencia}">
            </div>
        </div>

        <!-- ===== Cliente jurídico ===== -->
        <div class="seccion-tipo" data-tipo="CLIENTE_JURIDICO" style="display:none;">
            <h3>Datos del cliente jurídico</h3>
            <div class="campo">
                <label for="cj_razonSocial">Razón social</label>
                <input type="text" id="cj_razonSocial" name="cj_razonSocial" maxlength="150" value="${fn:escapeXml(clienteJuridico.razonSocial)}">
            </div>
            <div class="grid-2">
                <div class="campo">
                    <label for="cj_representante">Representante legal</label>
                    <input type="text" id="cj_representante" name="cj_representante" maxlength="150" value="${fn:escapeXml(clienteJuridico.representanteLegal)}">
                </div>
                <div class="campo">
                    <label for="cj_giro">Giro del negocio (opcional)</label>
                    <input type="text" id="cj_giro" name="cj_giro" maxlength="100" value="${fn:escapeXml(clienteJuridico.giroNegocio)}">
                </div>
            </div>
        </div>

        <!-- ===== Trabajador ===== -->
        <div class="seccion-tipo" data-tipo="TRABAJADOR" style="display:none;">
            <h3>Datos del trabajador</h3>
            <div class="grid-2">
                <div class="campo">
                    <label for="tr_nombres">Nombres</label>
                    <input type="text" id="tr_nombres" name="tr_nombres" maxlength="100" value="${fn:escapeXml(trabajador.nombres)}">
                </div>
                <div class="campo">
                    <label for="tr_apellidos">Apellidos</label>
                    <input type="text" id="tr_apellidos" name="tr_apellidos" maxlength="100" value="${fn:escapeXml(trabajador.apellidos)}">
                </div>
            </div>
            <div class="grid-2">
                <div class="campo">
                    <label for="tr_codigo">Código de empleado</label>
                    <input type="text" id="tr_codigo" name="tr_codigo" readonly
                           placeholder="Autogenerado al registrar"
                           value="${fn:escapeXml(trabajador.codigoEmpleado)}">
                </div>
                <div class="campo">
                    <label for="tr_rolId">Rol</label>
                    <select id="tr_rolId" name="tr_rolId">
                        <option value="">-- Seleccione --</option>
                        <c:forEach var="r" items="${rolesTrabajador}">
                            <option value="${r.rolTrabajadorId}" ${r.rolTrabajadorId == trabajador.rolTrabajadorId ? 'selected' : ''}>
                                <c:out value="${r.nombre}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="campo">
                <label for="tr_sucursalId">Sucursal</label>
                <select id="tr_sucursalId" name="tr_sucursalId">
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="s" items="${sucursales}">
                        <option value="${s.sucursalId}" ${s.sucursalId == trabajador.sucursalId ? 'selected' : ''}>
                            <c:out value="${s.nombre}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="acciones acciones-formulario">
            <a class="btn btn-secundario" href="${ctx}/personas">Cancelar</a>
            <button type="submit" class="btn">${esEdicion ? 'Actualizar' : 'Registrar'}</button>
        </div>
    </form>
</div>

<script src="${ctx}/assets/js/formulario.js"></script>
</body>
</html>