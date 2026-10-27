package com.becerra.alquilerventa.controlador;

import com.becerra.alquilerventa.dao.SucursalDAO;
import com.becerra.alquilerventa.dao.impl.SucursalDAOImpl;
import com.becerra.alquilerventa.modelo.Sucursal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sucursales")
public class SucursalServlet extends HttpServlet {

    private static final String VISTA_LISTAR = "/WEB-INF/views/sucursal/listar.jsp";
    private static final String VISTA_FORMULARIO = "/WEB-INF/views/sucursal/formulario.jsp";

    private final SucursalDAO sucursalDAO = new SucursalDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo" -> mostrarFormulario(request, response, new Sucursal(), null);
            case "editar" -> editar(request, response);
            default -> listar(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            guardar(request, response);
        } else if ("eliminar".equals(accion)) {
            eliminar(request, response);
        } else {
            redirigirAlListado(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        moverMensajeFlash(request);
        try {
            request.setAttribute("sucursales", sucursalDAO.listar());
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo cargar las sucursales: " + e.getMessage());
        }
        request.getRequestDispatcher(VISTA_LISTAR).forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Sucursal s = sucursalDAO.obtenerPorId(id);
            if (s == null) {
                guardarMensajeFlash(request, "error", "La sucursal no existe.");
                redirigirAlListado(request, response);
                return;
            }
            mostrarFormulario(request, response, s, null);
        } catch (NumberFormatException e) {
            guardarMensajeFlash(request, "error", "ID de sucursal no válido.");
            redirigirAlListado(request, response);
        } catch (SQLException e) {
            guardarMensajeFlash(request, "error", "Error al obtener la sucursal: " + e.getMessage());
            redirigirAlListado(request, response);
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        Sucursal s = leerFormulario(request, errores);

        if (!errores.isEmpty()) {
            mostrarFormulario(request, response, s, errores);
            return;
        }

        try {
            if (s.getSucursalId() > 0) {
                sucursalDAO.actualizar(s);
                guardarMensajeFlash(request, "mensaje", "Sucursal actualizada correctamente.");
            } else {
                sucursalDAO.insertar(s);
                guardarMensajeFlash(request, "mensaje", "Sucursal registrada correctamente.");
            }
            redirigirAlListado(request, response);
        } catch (SQLException e) {
            errores.add("Error al guardar la sucursal: " + e.getMessage());
            mostrarFormulario(request, response, s, errores);
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            sucursalDAO.eliminar(id);
            guardarMensajeFlash(request, "mensaje", "Sucursal eliminada correctamente.");
        } catch (NumberFormatException e) {
            guardarMensajeFlash(request, "error", "ID no válido.");
        } catch (SQLException e) {
            guardarMensajeFlash(request, "error", "No se puede eliminar la sucursal porque tiene vehículos o registros asociados.");
        }
        redirigirAlListado(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response,
                                   Sucursal sucursal, List<String> errores)
            throws ServletException, IOException {
        request.setAttribute("sucursal", sucursal);
        request.setAttribute("errores", errores);
        try {
            request.setAttribute("empresas", sucursalDAO.listarEmpresas());
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo cargar las empresas: " + e.getMessage());
        }
        request.getRequestDispatcher(VISTA_FORMULARIO).forward(request, response);
    }

    private Sucursal leerFormulario(HttpServletRequest request, List<String> errores) {
        Sucursal s = new Sucursal();
        String id = request.getParameter("sucursalId");
        if (id != null && !id.isBlank()) {
            s.setSucursalId(Integer.parseInt(id));
        }

        try {
            s.setEmpresaId(Integer.parseInt(texto(request, "empresaId")));
            if (s.getEmpresaId() <= 0) errores.add("Seleccione una empresa.");
        } catch (NumberFormatException e) {
            errores.add("Seleccione una empresa.");
        }

        s.setNombre(texto(request, "nombre"));
        s.setDireccion(texto(request, "direccion"));
        s.setHorarioAtencion(texto(request, "horarioAtencion"));
        s.setEstadoSucursal(texto(request, "estadoSucursal"));

        if (s.getNombre().isEmpty()) errores.add("El nombre de la sucursal es obligatorio.");
        if (s.getDireccion().isEmpty()) errores.add("La dirección es obligatoria.");
        if (s.getEstadoSucursal().isEmpty()) errores.add("Seleccione el estado de la sucursal.");

        return s;
    }

    private String texto(HttpServletRequest request, String nombre) {
        String valor = request.getParameter(nombre);
        return valor == null ? "" : valor.trim();
    }

    private void guardarMensajeFlash(HttpServletRequest request, String tipo, String texto) {
        request.getSession().setAttribute(tipo, texto);
    }

    private void moverMensajeFlash(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return;
        for (String tipo : new String[]{"mensaje", "error"}) {
            Object valor = session.getAttribute(tipo);
            if (valor != null) {
                request.setAttribute(tipo, valor);
                session.removeAttribute(tipo);
            }
        }
    }

    private void redirigirAlListado(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/sucursales");
    }
}