<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Personas</title>
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
                    <th>Tipo</th>
                    <th>Nombre / Razón social</th>
                    <th>Tipo Doc.</th>
                    <th>N° Documento</th>
                    <th>Correo</th>
                    <th>Teléfono</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${personas}">
                    <tr>
                        <td>${p.personaId}</td>
                        <td><c:out value="${p.tipoRegistro}" /></td>
                        <td><c:out value="${p.nombreCompleto}" /></td>
                        <td><c:out value="${p.nombreTipoDocumento}" /></td>
                        <td><c:out value="${p.numeroDocumento}" /></td>
                        <td><c:out value="${p.correo}" /></td>
                        <td><c:out value="${p.telefono}" /></td>
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
                    <tr><td colspan="9" class="vacio">No hay personas registradas.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
