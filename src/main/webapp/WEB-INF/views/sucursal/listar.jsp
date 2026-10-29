<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Directorio de Sucursales</title>
    <style>
    :root {
        --suc-primario: #d35400;
        --suc-secundario: #e67e22;
        --suc-fondo: #fcf6f2;
        --suc-tarjeta: #ffffff;
        --suc-texto: #2c3e50;
        --suc-gris: #7f8c8d;
    }

    * { 
        box-sizing: border-box; 
    }

    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: var(--suc-fondo);
        background-image: radial-gradient(#e67e2222 1px, transparent 1px);
        background-size: 20px 20px;
        color: var(--suc-texto);
        margin: 0;
        padding: 40px 20px;
        min-height: 100vh;
    }

    .top-bar {
        max-width: 1200px;
        margin: 0 auto 30px auto;
        display: flex;
        justify-content: space-between;
        align-items: flex-end;
        border-bottom: 3px solid var(--suc-primario);
        padding-bottom: 15px;
    }

    .top-bar h1 {
        margin: 0;
        font-size: 32px;
        font-weight: 800;
        color: var(--suc-primario);
        text-transform: uppercase;
        letter-spacing: -1px;
    }

    .top-bar-acciones {
        display: flex;
        gap: 12px;
    }

    .btn {
        padding: 10px 22px;
        border-radius: 30px;
        font-weight: bold;
        text-decoration: none;
        cursor: pointer;
        border: none;
        transition: all 0.2s;
        font-size: 14px;
        text-transform: uppercase;
        letter-spacing: 0.5px;
    }

    .btn-principal {
        background-color: var(--suc-primario);
        color: white;
        box-shadow: 0 4px 15px rgba(211, 84, 0, 0.3);
    }

    .btn-principal:hover { 
        background-color: var(--suc-secundario); 
        transform: translateY(-3px); 
    }
    
    .btn-outline {
        background-color: transparent;
        color: var(--suc-primario);
        border: 2px solid var(--suc-primario);
    }
    
    .btn-outline:hover { 
        background-color: var(--suc-primario); 
        color: white; 
    }

    .alerta { 
        padding: 15px 20px; 
        border-radius: 10px; 
        margin: 0 auto 25px auto; 
        max-width: 1200px; 
        font-weight: 600; 
    }

    .alerta-exito { 
        background: #d5f5e3; 
        color: #27ae60; 
        border: 1px solid #27ae60; 
    }

    .alerta-error { 
        background: #fadbd8; 
        color: #c0392b; 
        border: 1px solid #c0392b; 
    }

    .grid-sucursales {
        max-width: 1200px;
        margin: 0 auto;
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(340px, 1fr));
        gap: 25px;
    }
    .card-sucursal {
        background: var(--suc-tarjeta);
        border-radius: 16px;
        padding: 25px;
        box-shadow: 0 8px 25px rgba(0,0,0,0.06);
        border-top: 5px solid var(--suc-secundario);
        position: relative;
        display: flex;
        flex-direction: column;
        transition: transform 0.3s;
    }

    .card-sucursal:hover { 
        transform: translateY(-5px); 
        box-shadow: 0 12px 30px rgba(0,0,0,0.1); 
    }
    
    .card-badge {
        position: absolute;
        top: 20px;
        right: 20px;
        padding: 5px 12px;
        border-radius: 20px;
        font-size: 11px;
        font-weight: 800;
        text-transform: uppercase;
    }

    .badge-activa { 
        background: #e8f8f5; 
        color: #16a085; 
    }
    
    .badge-inactiva { 
        background: #f9ebea; 
        color: #c0392b; 
    }

    .card-empresa { 
        font-size: 12px; 
        color: var(--suc-gris); 
        font-weight: bold; 
        letter-spacing: 1px; 
        text-transform: uppercase; 
        margin-bottom: 5px;
    }

    .card-titulo { 
        font-size: 22px; 
        color: var(--suc-texto); 
        margin: 0 0 15px 0; 
        font-weight: 700; 
        padding-right: 70px;
    }
    
    .card-info { 
        margin-bottom: 25px; 
        flex-grow: 1; 
    }

    .info-item {
        display: flex;
        align-items: flex-start;
        margin-bottom: 12px;
        font-size: 14px;
        color: #555;
    }

    .info-label { 
        font-weight: 700; 
        min-width: 75px; 
        color: var(--suc-primario); 
    }

    .card-acciones {
        display: flex;
        gap: 10px;
        border-top: 1px solid #eee;
        padding-top: 15px;
    }

    .btn-card {
        flex: 1;
        text-align: center;
        padding: 8px 0;
        border-radius: 8px;
        font-size: 13px;
    }

    .btn-editar { 
        background: #f0f3f4; 
        color: #34495e; 
        text-decoration: none; 
        font-weight: bold; 
        transition: 0.2s;
    }

    .btn-editar:hover { 
        background: #d5d8dc; 
    }

    .btn-borrar { 
        background: #fdedec; 
        color: #c0392b; 
        border: none; 
        font-weight: bold; 
        cursor: pointer; 
        transition: 0.2s;
    }

    .btn-borrar:hover { 
        background: #e6b0aa; 
    }

    .card-acciones form { 
        flex: 1; 
        display: flex; 
        margin: 0; 
    }

    .vacio { 
        text-align: center; 
        grid-column: 1 / -1; 
        padding: 50px; 
        background: white; 
        border-radius: 12px; 
        color: var(--suc-gris); 
    }
    
    @media (max-width: 768px) {
        .top-bar { 
            flex-direction: column; 
            align-items: flex-start; 
            gap: 20px; 
        }

        .grid-sucursales { 
            grid-template-columns: 1fr; 
        }
    }
    </style>
</head>
<body>

    <div class="top-bar">
        <h1>Red de Sucursales</h1>
        <div class="top-bar-acciones">
            <a class="btn btn-outline" href="${ctx}/index.jsp">Volver al Inicio</a>
            <a class="btn btn-principal" href="${ctx}/sucursales?accion=nuevo">+ Registrar Sede</a>
        </div>
    </div>

    <c:if test="${not empty mensaje}">
        <div class="alerta alerta-exito"><c:out value="${mensaje}" /></div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alerta alerta-error"><c:out value="${error}" /></div>
    </c:if>

    <div class="grid-sucursales">
        <c:forEach var="s" items="${sucursales}">
            <div class="card-sucursal">
                <span class="card-badge ${s.estadoSucursal == 'Activa' ? 'badge-activa' : 'badge-inactiva'}">
                    <c:out value="${s.estadoSucursal}" />
                </span>
                
                <div class="card-empresa"><c:out value="${s.nombreEmpresa}" /> (ID: ${s.sucursalId})</div>
                <h3 class="card-titulo"><c:out value="${s.nombre}" /></h3>
                
                <div class="card-info">
                    <div class="info-item">
                        <span class="info-label">Dirección:</span>
                        <span><c:out value="${s.direccion}" /></span>
                    </div>
                    <div class="info-item">
                        <span class="info-label">Horario:</span>
                        <span><c:out value="${s.horarioAtencion}" /></span>
                    </div>
                </div>
                
                <div class="card-acciones">
                    <a class="btn-card btn-editar" href="${ctx}/sucursales?accion=editar&id=${s.sucursalId}">Editar Datos</a>
                    <form method="post" action="${ctx}/sucursales" onsubmit="return confirm('¿Eliminar definitivamente la sucursal ${s.nombre}?');">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="id" value="${s.sucursalId}">
                        <button type="submit" class="btn-card btn-borrar">Dar de Baja</button>
                    </form>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty sucursales and empty error}">
            <div class="vacio">
                <h2>No hay sucursales operativas</h2>
                <p>El sistema aún no tiene sedes registradas para el alquiler o venta.</p>
            </div>
        </c:if>
    </div>

</body>
</html>