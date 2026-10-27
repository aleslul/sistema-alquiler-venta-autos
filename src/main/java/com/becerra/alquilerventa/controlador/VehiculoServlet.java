package com.becerra.alquilerventa.controlador;

import com.becerra.alquilerventa.dao.VehiculoDAO;
import com.becerra.alquilerventa.dao.impl.VehiculoDAOImpl;
import com.becerra.alquilerventa.modelo.Vehiculo;
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

@WebServlet("/vehiculos")
public class VehiculoServlet extends HttpServlet {

    private static final String VISTA_LISTAR = "/WEB-INF/views/vehiculo/listar.jsp";
    private static final String VISTA_FORMULARIO = "/WEB-INF/views/vehiculo/formulario.jsp";

    private final VehiculoDAO vehiculoDAO = new VehiculoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo" -> mostrarFormulario(request, response, new Vehiculo(), null);
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
            request.setAttribute("vehiculos", vehiculoDAO.listar());
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo cargar los vehículos: " + e.getMessage());
        }
        request.getRequestDispatcher(VISTA_LISTAR).forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Vehiculo v = vehiculoDAO.obtenerPorId(id);
            if (v == null) {
                guardarMensajeFlash(request, "error", "El vehículo no existe.");
                redirigirAlListado(request, response);
                return;
            }
            mostrarFormulario(request, response, v, null);
        } catch (NumberFormatException e) {
            guardarMensajeFlash(request, "error", "ID de vehículo no válido.");
            redirigirAlListado(request, response);
        } catch (SQLException e) {
            guardarMensajeFlash(request, "error", "Error al obtener el vehículo: " + e.getMessage());
            redirigirAlListado(request, response);
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        Vehiculo v = leerFormulario(request, errores);

        if (!errores.isEmpty()) {
            mostrarFormulario(request, response, v, errores);
            return;
        }

        try {
            if (v.getVehiculoId() > 0) {
                vehiculoDAO.actualizar(v);
                guardarMensajeFlash(request, "mensaje", "Vehículo actualizado correctamente.");
            } else {
                vehiculoDAO.insertar(v);
                guardarMensajeFlash(request, "mensaje", "Vehículo registrado correctamente.");
            }
            redirigirAlListado(request, response);
        } catch (SQLException e) {
            errores.add("Error al guardar (verifique que la placa no esté duplicada): " + e.getMessage());
            mostrarFormulario(request, response, v, errores);
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            vehiculoDAO.eliminar(id);
            guardarMensajeFlash(request, "mensaje", "Vehículo eliminado correctamente.");
        } catch (NumberFormatException e) {
            guardarMensajeFlash(request, "error", "ID no válido.");
        } catch (SQLException e) {
            guardarMensajeFlash(request, "error", "No se puede eliminar el vehículo porque tiene registros asociados.");
        }
        redirigirAlListado(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response,
                                   Vehiculo vehiculo, List<String> errores)
            throws ServletException, IOException {
        request.setAttribute("vehiculo", vehiculo);
        request.setAttribute("errores", errores);
        try {
            request.setAttribute("categorias", vehiculoDAO.listarCategorias());
            request.setAttribute("marcas", vehiculoDAO.listarMarcas());
            request.setAttribute("estadosVehiculo", vehiculoDAO.listarEstadosVehiculo());
            request.setAttribute("tiposTransmision", vehiculoDAO.listarTiposTransmision());
            request.setAttribute("tiposCombustible", vehiculoDAO.listarTiposCombustible());
            request.setAttribute("sucursales", vehiculoDAO.listarSucursales());
        } catch (SQLException e) {
            request.setAttribute("error", "Error al cargar listas desplegables: " + e.getMessage());
        }
        request.getRequestDispatcher(VISTA_FORMULARIO).forward(request, response);
    }

    private Vehiculo leerFormulario(HttpServletRequest request, List<String> errores) {
        Vehiculo v = new Vehiculo();
        String id = request.getParameter("vehiculoId");
        if (id != null && !id.isBlank()) {
            v.setVehiculoId(Integer.parseInt(id));
        }

        try { v.setCategoriaId(Integer.parseInt(texto(request, "categoriaId"))); } catch (NumberFormatException e) { errores.add("Seleccione una categoría."); }
        try { v.setMarcaId(Integer.parseInt(texto(request, "marcaId"))); } catch (NumberFormatException e) { errores.add("Seleccione una marca."); }
        try { v.setEstadoVehiculoId(Integer.parseInt(texto(request, "estadoVehiculoId"))); } catch (NumberFormatException e) { errores.add("Seleccione un estado."); }
        try { v.setTipoTransmisionId(Integer.parseInt(texto(request, "tipoTransmisionId"))); } catch (NumberFormatException e) { errores.add("Seleccione una transmisión."); }
        try { v.setTipoCombustibleId(Integer.parseInt(texto(request, "tipoCombustibleId"))); } catch (NumberFormatException e) { errores.add("Seleccione un combustible."); }
        try { v.setSucursalActualId(Integer.parseInt(texto(request, "sucursalActualId"))); } catch (NumberFormatException e) { errores.add("Seleccione una sucursal."); }

        v.setPlaca(texto(request, "placa"));
        v.setModelo(texto(request, "modelo"));
        v.setModalidad(texto(request, "modalidad"));
        v.setAireAcondicionado("true".equals(request.getParameter("aireAcondicionado")) || "on".equals(request.getParameter("aireAcondicionado")));

        if (v.getPlaca().isEmpty()) errores.add("La placa es obligatoria.");
        if (v.getModelo().isEmpty()) errores.add("El modelo es obligatorio.");
        if (v.getModalidad().isEmpty()) errores.add("Seleccione la modalidad (Alquiler o Venta).");

        try {
            v.setAnio(Integer.parseInt(texto(request, "anio")));
            if (v.getAnio() < 1990 || v.getAnio() > 2028) errores.add("Ingrese un año válido.");
        } catch (NumberFormatException e) { errores.add("El año no es válido."); }

        try {
            v.setNumPasajeros(Integer.parseInt(texto(request, "numPasajeros")));
            if (v.getNumPasajeros() <= 0) errores.add("El número de pasajeros debe ser mayor a 0.");
        } catch (NumberFormatException e) { errores.add("Número de pasajeros inválido."); }

        try {
            v.setNumPuertas(Integer.parseInt(texto(request, "numPuertas")));
            if (v.getNumPuertas() <= 0) errores.add("El número de puertas debe ser mayor a 0.");
        } catch (NumberFormatException e) { errores.add("Número de puertas inválido."); }

        return v;
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
        response.sendRedirect(request.getContextPath() + "/vehiculos");
    }
}