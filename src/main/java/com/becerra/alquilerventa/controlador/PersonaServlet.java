package com.becerra.alquilerventa.controlador;

import com.becerra.alquilerventa.dao.PersonaDAO;
import com.becerra.alquilerventa.dao.impl.PersonaDAOImpl;
import com.becerra.alquilerventa.modelo.Persona;
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

@WebServlet("/personas")
public class PersonaServlet extends HttpServlet {

    private static final String VISTA_LISTAR = "/WEB-INF/views/persona/listar.jsp";
    private static final String VISTA_FORMULARIO = "/WEB-INF/views/persona/formulario.jsp";

    private final PersonaDAO personaDAO = new PersonaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo" -> mostrarFormulario(request, response, new Persona(), null);
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
            request.setAttribute("personas", personaDAO.listar());
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo cargar las personas: " + e.getMessage());
        }
        request.getRequestDispatcher(VISTA_LISTAR).forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Persona p = personaDAO.obtenerPorId(id);
            if (p == null) {
                guardarMensajeFlash(request, "error", "La persona no existe.");
                redirigirAlListado(request, response);
                return;
            }
            mostrarFormulario(request, response, p, null);
        } catch (NumberFormatException e) {
            guardarMensajeFlash(request, "error", "ID de persona no válido.");
            redirigirAlListado(request, response);
        } catch (SQLException e) {
            guardarMensajeFlash(request, "error", "Error al obtener la persona: " + e.getMessage());
            redirigirAlListado(request, response);
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        Persona p = leerFormulario(request, errores);

        if (!errores.isEmpty()) {
            mostrarFormulario(request, response, p, errores);
            return;
        }

        try {
            if (p.getPersonaId() > 0) {
                personaDAO.actualizar(p);
                guardarMensajeFlash(request, "mensaje", "Persona actualizada correctamente.");
            } else {
                personaDAO.insertar(p);
                guardarMensajeFlash(request, "mensaje", "Persona registrada correctamente.");
            }
            redirigirAlListado(request, response);
        } catch (SQLException e) {
            errores.add("Error al guardar (verifique que el Documento o Correo no estén duplicados): " + e.getMessage());
            mostrarFormulario(request, response, p, errores);
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            personaDAO.eliminar(id);
            guardarMensajeFlash(request, "mensaje", "Persona eliminada correctamente.");
        } catch (NumberFormatException e) {
            guardarMensajeFlash(request, "error", "ID no válido.");
        } catch (SQLException e) {
            guardarMensajeFlash(request, "error", "No se puede eliminar porque tiene registros asociados: " + e.getMessage());
        }
        redirigirAlListado(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response,
                                   Persona persona, List<String> errores)
            throws ServletException, IOException {
        request.setAttribute("persona", persona);
        request.setAttribute("errores", errores);
        try {
            request.setAttribute("tiposDocumento", personaDAO.listarTiposDocumento());
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo cargar los tipos de documento: " + e.getMessage());
        }
        request.getRequestDispatcher(VISTA_FORMULARIO).forward(request, response);
    }

    private Persona leerFormulario(HttpServletRequest request, List<String> errores) {
        Persona p = new Persona();
        String id = request.getParameter("personaId");
        if (id != null && !id.isBlank()) {
            p.setPersonaId(Integer.parseInt(id));
        }

        try {
            p.setTipoDocumentoId(Integer.parseInt(texto(request, "tipoDocumentoId")));
            if (p.getTipoDocumentoId() <= 0) errores.add("Seleccione un tipo de documento.");
        } catch (NumberFormatException e) {
            errores.add("Seleccione un tipo de documento.");
        }

        p.setNumeroDocumento(texto(request, "numeroDocumento"));
        p.setCorreo(texto(request, "correo"));
        p.setTelefono(texto(request, "telefono"));
        p.setDireccion(texto(request, "direccion"));
        p.setEstadoUsuario(texto(request, "estadoUsuario"));
        p.setPasswordHash(texto(request, "passwordHash"));

        if (p.getNumeroDocumento().isEmpty()) errores.add("El número de documento es obligatorio.");
        if (p.getCorreo().isEmpty()) errores.add("El correo es obligatorio.");
        if (p.getEstadoUsuario().isEmpty()) errores.add("Seleccione el estado del usuario.");

        return p;
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
        response.sendRedirect(request.getContextPath() + "/personas");
    }
}