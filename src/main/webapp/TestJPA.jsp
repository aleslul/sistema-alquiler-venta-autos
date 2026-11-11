<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="jakarta.persistence.EntityManager,
                 com.becerra.alquilerventa.config.JPAUtil,
                 com.becerra.alquilerventa.modelo.Empresa,
                 com.becerra.alquilerventa.modelo.Sucursal,
                 java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Prueba de JPA - Sucursales</title>
</head>
<body style="font-family: Segoe UI, sans-serif; padding: 30px;">
<%
    EntityManager em = null;
    try {
        em = JPAUtil.getEntityManager();

        List<?> empresas = em.createStoredProcedureQuery("sp_listar_empresas", Empresa.class)
                             .getResultList();
        List<?> sucursales = em.createStoredProcedureQuery("sp_listar_sucursales", Sucursal.class)
                               .getResultList();

        out.println("<h2 style='color:green'>✓ JPA (Hibernate) y procedimientos funcionando correctamente</h2>");
        out.println("<p><strong>Empresas registradas:</strong> " + empresas.size() + "</p>");
        out.println("<p><strong>Sucursales registradas:</strong> " + sucursales.size() + "</p>");
        out.println("<p><a href='" + request.getContextPath() + "/sucursales'>Ir al CRUD de Sucursales</a></p>");
    } catch (Exception e) {
        out.println("<h2 style='color:red'>✕ Error en JPA: " + e.getMessage() + "</h2>");
        Throwable causa = e.getCause();
        while (causa != null) {
            out.println("<p><strong>Causa:</strong> " + causa.getMessage() + "</p>");
            causa = causa.getCause();
        }
    } finally {
        if (em != null) em.close();
    }
%>
</body>
</html>