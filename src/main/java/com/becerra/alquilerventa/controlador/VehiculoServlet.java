package com.becerra.alquilerventa.controlador;

import com.becerra.alquilerventa.dao.VehiculoDAO;
import com.becerra.alquilerventa.dao.impl.VehiculoDAOImpl;
import com.becerra.alquilerventa.modelo.Vehiculo;
import com.becerra.alquilerventa.modelo.VehiculoVenta;
import com.becerra.alquilerventa.modelo.VehiculoAlquiler;
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
        String modalidad = texto(request, "modalidad");
        Vehiculo v;

        if ("Venta".equals(modalidad)) {
            VehiculoVenta vv = new VehiculoVenta();
            vv.setModalidad("Venta");
            try {
                vv.setPrecioVenta(new java.math.BigDecimal(texto(request, "precioVenta")));
                if (vv.getPrecioVenta().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                    errores.add("El precio de venta debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                errores.add("El precio de venta es obligatorio para vehículos en venta.");
            }
            vv.setCondicion(texto(request, "condicion"));
            try {
                vv.setMesesGarantia(Integer.parseInt(texto(request, "mesesGarantia")));
            } catch (NumberFormatException e) {
                errores.add("Los meses de garantía son obligatorios.");
            }
            v = vv;
        } else {
            VehiculoAlquiler va = new VehiculoAlquiler();
            va.setModalidad("Alquiler");
            String tarifaTxt = texto(request, "tarifaDiariaEspecifica");
            if (!tarifaTxt.isEmpty()) {
                try {
                    va.setTarifaDiariaEspecifica(new java.math.BigDecimal(tarifaTxt));
                } catch (NumberFormatException e) {
                    errores.add("La tarifa diaria no es válida.");
                }
            }
            try {
                va.setKilometrajeActual(Integer.parseInt(texto(request, "kilometrajeActual")));
            } catch (NumberFormatException e) {
                errores.add("El kilometraje actual es obligatorio para alquiler.");
            }
            try {
                va.setDepositoGarantiaBase(new java.math.BigDecimal(texto(request, "depositoGarantiaBase")));
            } catch (NumberFormatException e) {
                errores.add("El depósito de garantía es obligatorio para alquiler.");
            }
            v = va;
        }

        // Leer ID si es edición
        String id = request.getParameter("vehiculoId");
        if (id != null && !id.isBlank()) {
            v.setVehiculoId(Integer.parseInt(id));
        }

        // Leer atributos comunes
        try { v.setCategoriaId(Integer.parseInt(texto(request, "categoriaId"))); } catch (Exception e) { errores.add("Seleccione una categoría."); }
        try { v.setMarcaId(Integer.parseInt(texto(request, "marcaId"))); } catch (Exception e) { errores.add("Seleccione una marca."); }
        try { v.setEstadoVehiculoId(Integer.parseInt(texto(request, "estadoVehiculoId"))); } catch (Exception e) { errores.add("Seleccione un estado."); }
        try { v.setTipoTransmisionId(Integer.parseInt(texto(request, "tipoTransmisionId"))); } catch (Exception e) { errores.add("Seleccione una transmisión."); }
        try { v.setTipoCombustibleId(Integer.parseInt(texto(request, "tipoCombustibleId"))); } catch (Exception e) { errores.add("Seleccione un combustible."); }
        try { v.setSucursalActualId(Integer.parseInt(texto(request, "sucursalActualId"))); } catch (Exception e) { errores.add("Seleccione una sucursal."); }

        v.setPlaca(texto(request, "placa"));
        v.setModelo(texto(request, "modelo"));
        v.setAireAcondicionado("true".equals(request.getParameter("aireAcondicionado")) || "on".equals(request.getParameter("aireAcondicionado")));

        if (v.getPlaca().isEmpty()) errores.add("La placa es obligatoria.");
        if (v.getModelo().isEmpty()) errores.add("El modelo es obligatorio.");

        try { v.setAnio(Integer.parseInt(texto(request, "anio"))); } catch (Exception e) { errores.add("Año no válido."); }
        try { v.setNumPasajeros(Integer.parseInt(texto(request, "numPasajeros"))); } catch (Exception e) { errores.add("Pasajeros no válidos."); }
        try { v.setNumPuertas(Integer.parseInt(texto(request, "numPuertas"))); } catch (Exception e) { errores.add("Puertas no válidas."); }

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