<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="esEdicion" value="${sucursal.sucursalId > 0}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${esEdicion ? 'Actualizar' : 'Registrar'} Sucursal</title>
    <style>
    :root {
        --suc-primario: #d35400;
        --suc-secundario: #e67e22;
        --suc-fondo: #fcf6f2;
        --suc-texto: #2c3e50;
        --suc-input-bg: #fdfdfd;
        --suc-border: #f0b27a;
    }
    
    * { 
        box-sizing: border-box; 
    }

    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #e67e22 0%, #d35400 100%);
        color: var(--suc-texto);
        margin: 0;
        padding: 50px 20px;
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
    .contenedor-flotante {
        width: 100%;
        max-width: 850px;
        background: #ffffff;
        border-radius: 20px;
        box-shadow: 0 20px 50px rgba(0,0,0,0.3);
        overflow: hidden;
        display: flex;
        flex-direction: column;
    }

    .header-form {
        background-color: #2c3e50; 
        color: white;
        padding: 30px 40px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .header-form h1 {
        margin: 0;
        font-size: 26px;
        font-weight: 300;
    }

    .header-form h1 strong { 
        font-weight: 800; color: var(--suc-secundario); 
    }
    
    .btn-cerrar {
        color: white;
        text-decoration: none;
        font-size: 14px;
        border: 1px solid rgba(255,255,255,0.3);
        padding: 8px 15px;
        border-radius: 20px;
        transition: 0.3s;
    }

    .btn-cerrar:hover { 
        background: rgba(255,255,255,0.1); 
    }

    .cuerpo-form {
        padding: 40px;
    }

    .alerta-error { 
        background: #fadbd8; 
        color: #c0392b; 
        padding: 15px;
        border-radius: 10px; 
        margin-bottom: 25px; 
        font-size: 14px; 
    }

    .lista-errores {
        margin: 10px 0 0 20px; 
        padding: 0; 
    }

    .formulario-layout {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 25px 35px;
    }
    
    .campo-full { 
        grid-column: 1 / -1; 
    }
    
    .formulario label { 
        display: block; 
        margin-bottom: 8px; 
        font-size: 12px; 
        font-weight: 800; 
        text-transform: uppercase; 
        color: #7f8c8d;
        letter-spacing: 1px;
    }
    
    .formulario input[type=text], .formulario select {
        width: 100%; 
        padding: 14px 18px; 
        font-size: 15px; 
        color: var(--suc-texto);
        border: 2px solid #ebedef; 
        border-radius: 12px; 
        background: var(--suc-input-bg);
        transition: all 0.3s ease;
    }
    .formulario input:focus, .formulario select:focus {
        outline: none; 
        border-color: var(--suc-secundario);
        background: #fff;
        box-shadow: 0 5px 15px rgba(230, 126, 34, 0.1);
    }
    
    .footer-form {
        grid-column: 1 / -1;
        margin-top: 20px;
        padding-top: 30px;
        border-top: 2px dashed #ebedef;
    }

    .btn-submit {
        width: 100%;
        padding: 16px;
        background: var(--suc-texto); 
        color: white;
        border: none;
        border-radius: 12px;
        font-size: 16px;
        font-weight: bold;
        text-transform: uppercase;
        letter-spacing: 1px;
        cursor: pointer;
        transition: 0.3s;
    }

    .btn-submit:hover {
        background: var(--suc-primario);
        transform: translateY(-2px);
        box-shadow: 0 10px 20px rgba(211, 84, 0, 0.4);
    }

    @media (max-width: 650px) {
        .formulario-layout { 
            grid-template-columns: 1fr; 
            gap: 20px;
        }

        .cuerpo-form { 
            padding: 25px; 
        }

        .header-form { 
            padding: 25px; 
            flex-direction: column; 
            gap: 15px; 
            text-align: center;
        }
    }
    </style>
</head>
<body>

<div class="contenedor-flotante">
    <div class="header-form">
        <h1>${esEdicion ? 'Actualizar' : 'Registro de'} <strong>Sucursal</strong></h1>
        <a class="btn-cerrar" href="${ctx}/sucursales">✕ Cancelar</a>
    </div>

    <div class="cuerpo-form">
        <c:if test="${not empty errores}">
            <div class="alerta-error">
                <strong>No se pudo procesar la solicitud:</strong>
                <ul class="lista-errores">
                    <c:forEach var="e" items="${errores}">
                        <li><c:out value="${e}" /></li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form class="formulario formulario-layout" method="post" action="${ctx}/sucursales">
            <input type="hidden" name="accion" value="guardar">
            <input type="hidden" name="sucursalId" value="${esEdicion ? sucursal.sucursalId : ''}">

            <div class="campo">
                <label for="empresaId">Empresa / Holding</label>
                <select id="empresaId" name="empresaId" required>
                    <option value="">-- Seleccionar Organización --</option>
                    <c:forEach var="emp" items="${empresas}">
                        <option value="${emp.empresaId}" ${emp.empresaId == sucursal.empresaId ? 'selected' : ''}>
                            <c:out value="${emp.razonSocial}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="campo">
                <label for="nombre">Identificador de Sede</label>
                <input type="text" id="nombre" name="nombre" maxlength="100" placeholder="Ej. Agencia Norte (Aeropuerto)" required value="${fn:escapeXml(sucursal.nombre)}">
            </div>

            <div class="campo campo-full">
                <label for="direccion">Dirección Oficial</label>
                <input type="text" id="direccion" name="direccion" maxlength="200" placeholder="Ingresa la ubicación física exacta" required value="${fn:escapeXml(sucursal.direccion)}">
            </div>
            
            <div class="campo campo-full">
                <label>Horario de Atención</label>
                
                <!-- Contenedor visual moderno para armar el horario -->
                <div style="background: #f8f9fa; border: 2px solid #ebedef; border-radius: 12px; padding: 20px; display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 15px; align-items: center;">
                    
                    <!-- Selección de Días -->
                    <div>
                        <label for="selectDias" style="font-size: 11px; color: #7f8c8d; margin-bottom: 5px;">Días Operativos:</label>
                        <select id="selectDias" class="input-horario" onchange="actualizarHorarioFinal()">
                            <option value="L-V">Lunes a Viernes</option>
                            <option value="L-S">Lunes a Sábado</option>
                            <option value="L-D">Lunes a Domingo (24h)</option>
                            <option value="Fines de semana">Fines de Semana</option>
                        </select>
                    </div>

                    <!-- Hora de Apertura -->
                    <div>
                        <label for="horaApertura" style="font-size: 11px; color: #7f8c8d; margin-bottom: 5px;">Apertura:</label>
                        <input type="time" id="horaApertura" class="input-horario" value="08:00" onchange="actualizarHorarioFinal()">
                    </div>

                    <!-- Hora de Cierre -->
                    <div>
                        <label for="horaCierre" style="font-size: 11px; color: #7f8c8d; margin-bottom: 5px;">Cierre:</label>
                        <input type="time" id="horaCierre" class="input-horario" value="18:00" onchange="actualizarHorarioFinal()">
                    </div>
                </div>

                <!-- Input final que va a la base de datos -->
                <input type="text" id="horarioAtencion" name="horarioAtencion" required 
                       value="${fn:escapeXml(sucursal.horarioAtencion)}" 
                       placeholder="Ej. L-V: 08:00 a 18:00" 
                       style="margin-top: 10px; font-weight: 600; color: #d35400;">
            </div>

            <!-- Script inteligente de sincronización -->
            <script>
            function actualizarHorarioFinal() {
                const dias = document.getElementById('selectDias').value;
                const apertura = document.getElementById('horaApertura').value;
                const cierre = document.getElementById('horaCierre').value;
                
                if (apertura && cierre) {
                    const resultado = dias + ": " + apertura + " a " + cierre;
                    document.getElementById('horarioAtencion').value = resultado;
                }
            }

            window.addEventListener('DOMContentLoaded', () => {
                const inputHorario = document.getElementById('horarioAtencion');
                const val = inputHorario.value ? inputHorario.value.trim() : "";
                
                // Si está vacío o tiene datos corruptos previos (" a "), inicializamos por defecto
                if(!val || val === "a" || val === "a " || val === " a" || !val.includes(" a ")) {
                    actualizarHorarioFinal();
                } else {
                    // Si ya tiene un formato válido, intentamos desglosarlo para mover los selectores acorde
                    try {
                        const partes = val.split(" a ");
                        const cierre = partes[1].trim();
                        const subPartes = partes[0].split(":");
                        if (subPartes.length >= 2) {
                            const dias = subPartes[0].trim();
                            const apertura = subPartes[1].trim();
                            
                            document.getElementById('selectDias').value = dias;
                            document.getElementById('horaApertura').value = apertura;
                            document.getElementById('horaCierre').value = cierre;
                        }
                    } catch(e) {
                        console.log("No se pudo parsear el horario anterior, usando valores por defecto.");
                        actualizarHorarioFinal();
                    }
                }
            });
            </script>
            
            <div class="campo">
                <label for="estadoSucursal">Estatus Operativo</label>
                <select id="estadoSucursal" name="estadoSucursal" required>
                    <option value="Activa" ${sucursal.estadoSucursal == 'Activa' ? 'selected' : ''}>Activa (Acepta Reservas)</option>
                    <option value="Inactiva" ${sucursal.estadoSucursal == 'Inactiva' ? 'selected' : ''}>Inactiva (Cerrada temporalmente)</option>
                </select>
            </div>

            <div class="footer-form">
                <button type="submit" class="btn-submit">${esEdicion ? 'Guardar Cambios' : 'Procesar Alta de Sucursal'}</button>
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
        </form>
    </div>
</div>

</body>
</html>