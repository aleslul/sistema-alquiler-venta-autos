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

    <style>
    :root {
        --azul-oscuro: #0b3c5d;
        --azul: #1d6fa5;
        --azul-claro: #e8f4fb;
        --aqua: #1abc9c;
        --aqua-oscuro: #12957c;
        --aqua-claro: #d5f5ee;
        --texto: #1f2d3d;
        --borde: #c9dfec;
    }
    * { box-sizing: border-box; }
    body {
        font-family: "Segoe UI", Roboto, Arial, sans-serif;
        background: linear-gradient(135deg, #e8f4fb 0%, #d5f5ee 100%);
        background-attachment: fixed;
        color: var(--texto);
        margin: 0;
        padding: 32px 16px;
        min-height: 100vh;
    }
    .contenedor {
        max-width: 1150px;
        margin: 0 auto;
        background: #fff;
        padding: 28px 32px;
        border-radius: 14px;
        border-top: 6px solid var(--aqua);
        box-shadow: 0 10px 30px rgba(11, 60, 93, .15);
    }
    .contenedor-angosto { max-width: 780px; }

    .encabezado {
        display: flex; justify-content: space-between; align-items: center;
        flex-wrap: wrap; gap: 12px;
        margin-bottom: 22px; padding-bottom: 14px;
        border-bottom: 2px solid var(--aqua-claro);
    }
    h1 { margin: 0; font-size: 24px; color: var(--azul-oscuro); letter-spacing: .3px; }
    h3 {
        margin: 14px 0 10px; font-size: 16px; color: var(--azul);
        padding-left: 10px; border-left: 4px solid var(--aqua);
    }

    .acciones { display: flex; gap: 8px; align-items: center; flex-wrap: wrap; }
    .acciones form { margin: 0; }
    .acciones-formulario { justify-content: flex-end; margin-top: 24px; }
    .btn {
        display: inline-block; padding: 9px 18px; border: 0; border-radius: 8px;
        background: linear-gradient(135deg, var(--azul) 0%, var(--aqua) 100%);
        color: #fff; font-size: 14px; font-weight: 600; text-decoration: none;
        cursor: pointer; transition: transform .15s, box-shadow .15s, filter .15s;
        box-shadow: 0 3px 8px rgba(29, 111, 165, .3);
    }
    .btn:hover { transform: translateY(-2px); filter: brightness(1.08); box-shadow: 0 6px 14px rgba(29, 111, 165, .35); }
    .btn-secundario { background: #fff; color: var(--azul); border: 2px solid var(--azul); box-shadow: none; }
    .btn-secundario:hover { background: var(--azul-claro); }
    .btn-peligro { background: linear-gradient(135deg, #c0392b, #e74c3c); box-shadow: 0 3px 8px rgba(192, 57, 43, .3); }
    .btn-sm { padding: 5px 12px; font-size: 13px; border-radius: 6px; }

    .alerta { padding: 12px 16px; border-radius: 8px; margin-bottom: 16px; font-size: 14px; }
    .alerta-exito { background: var(--aqua-claro); color: var(--aqua-oscuro); border-left: 5px solid var(--aqua); }
    .alerta-error { background: #fdecea; color: #a93226; border-left: 5px solid #e74c3c; }
    .lista-errores { margin: 6px 0 0 18px; padding: 0; }

    .tabla-contenedor { overflow-x: auto; border-radius: 10px; border: 1px solid var(--borde); }
    .tabla { width: 100%; border-collapse: collapse; font-size: 14px; }
    .tabla thead th {
        background: linear-gradient(135deg, var(--azul-oscuro), var(--azul));
        color: #fff; text-align: left; padding: 12px 14px;
        font-weight: 600; white-space: nowrap;
    }
    .tabla td { padding: 11px 14px; border-bottom: 1px solid #e3eef5; }
    .tabla tbody tr:nth-child(even) { background: #f5fafd; }
    .tabla tbody tr:hover { background: var(--aqua-claro); }
    .vacio { text-align: center; color: #6c8498; padding: 28px !important; font-style: italic; }

    .badge { padding: 3px 12px; border-radius: 999px; font-size: 12px; font-weight: 600; }
    .badge-activo { background: var(--aqua-claro); color: var(--aqua-oscuro); }
    .badge-inactivo { background: #e3e8ec; color: #5b6b78; }

    .formulario .campo { margin-bottom: 14px; }
    .formulario label { display: block; margin-bottom: 5px; font-size: 13px; font-weight: 600; color: var(--azul-oscuro); }
    .formulario input[type=text], .formulario input[type=email], .formulario input[type=password],
    .formulario input[type=date], .formulario input[type=number], .formulario select {
        width: 100%; padding: 10px 12px; font-size: 14px; color: var(--texto);
        border: 1.5px solid var(--borde); border-radius: 8px; background: #fbfdff;
        transition: border-color .2s, box-shadow .2s;
    }
    .formulario input:focus, .formulario select:focus {
        outline: none; border-color: var(--aqua);
        box-shadow: 0 0 0 4px rgba(26, 188, 156, .2); background: #fff;
    }
    .grid-2 { display: grid; grid-template-columns: 1fr 1fr; gap: 14px; }

    .seccion-tipo {
        background: var(--azul-claro); border: 1px solid var(--borde);
        border-left: 5px solid var(--aqua); border-radius: 10px;
        padding: 6px 18px 12px; margin: 6px 0 16px;
    }

    @media (max-width: 640px) {
        .grid-2 { grid-template-columns: 1fr; }
        .contenedor { padding: 20px 16px; }
    }
</style>
</head>


<body>
<div class="contenedor contenedor-angosto">
    <div class="encabezado">
        <h1>${esEdicion ? 'Editar Persona' : 'Nueva Persona'}</h1>
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
                    value="${fn:escapeXml(trabajador.codigoEmpleado)}"
                    style="background-color: #e9ecef; cursor: not-allowed;">
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

<script>
    var selectTipo = document.getElementById('tipoRegistro');
    function mostrarSeccion() {
        var tipo = selectTipo.value;
        document.querySelectorAll('.seccion-tipo').forEach(function (s) {
            s.style.display = (s.getAttribute('data-tipo') === tipo) ? 'block' : 'none';
        });
    }
    selectTipo.addEventListener('change', mostrarSeccion);
    mostrarSeccion();
</script>
</body>
</html>
