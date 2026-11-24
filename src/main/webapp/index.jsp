<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio - Alquiler y Venta de Autos</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fraunces:wght@600;700&family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/inicio.css">
</head>
<body class="pagina-inicio">
    <div class="tarjeta-inicio">
        <h1>Sistema de Alquiler y Venta de Autos</h1>
        <p class="descripcion">Módulo de Gestión de Personas<br>Clientes y Trabajadores</p>

        <div class="menu-inicio">
            <a class="btn" href="${pageContext.request.contextPath}/personas">Ir a Gestión de Personas</a>
            <a class="btn btn-secundario" href="${pageContext.request.contextPath}/TestJPA.jsp">Probar conexión JPA</a>
        </div>

        <p class="nota">
            <strong>Nota:</strong> el botón de prueba verifica la conexión JPA y los procedimientos almacenados.
        </p>
    </div>
</body>
</html>