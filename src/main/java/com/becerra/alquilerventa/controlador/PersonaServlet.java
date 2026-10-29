package com.becerra.alquilerventa.controlador;
import com.becerra.alquilerventa.dao.PersonaDAO;
import com.becerra.alquilerventa.dao.impl.PersonaDAOImpl;
import com.becerra.alquilerventa.modelo.ClienteJuridico;
import com.becerra.alquilerventa.modelo.ClienteNatural;
import com.becerra.alquilerventa.modelo.Persona;
import com.becerra.alquilerventa.modelo.Trabajador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
            errores.add("Error al guardar (verifique que el Documento, el Correo o el Código de empleado no estén duplicados): " + e.getMessage());
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
        request.setAttribute("tipoRegistro", persona.getCodigoTipo());
        request.setAttribute("clienteNatural",
                persona instanceof ClienteNatural c ? c : new ClienteNatural());
        request.setAttribute("clienteJuridico",
                persona instanceof ClienteJuridico c ? c : new ClienteJuridico());
        request.setAttribute("trabajador",
                persona instanceof Trabajador t ? t : new Trabajador());

        try {
            request.setAttribute("tiposDocumento", personaDAO.listarTiposDocumento());
            request.setAttribute("rolesTrabajador", personaDAO.listarRolesTrabajador());
            request.setAttribute("sucursales", personaDAO.listarSucursales());
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudieron cargar las listas desplegables: " + e.getMessage());
        }
        request.getRequestDispatcher(VISTA_FORMULARIO).forward(request, response);
    }


    private Persona leerFormulario(HttpServletRequest request, List<String> errores) {
        Persona p = switch (texto(request, "tipoRegistro")) {
            case Persona.CLIENTE_NATURAL -> leerClienteNatural(request, errores);
            case Persona.CLIENTE_JURIDICO -> leerClienteJuridico(request, errores);
            case Persona.TRABAJADOR -> leerTrabajador(request, errores);
            default -> new Persona();
        };

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

    private ClienteNatural leerClienteNatural(HttpServletRequest request, List<String> errores) {
        ClienteNatural c = new ClienteNatural();
        c.setNombres(texto(request, "cn_nombres"));
        c.setApellidos(texto(request, "cn_apellidos"));
        c.setFechaNacimiento(fecha(request, "cn_fechaNacimiento", "La fecha de nacimiento no es válida.", errores));
        c.setLicenciaConducir(texto(request, "cn_licencia"));
        c.setFechaVencimientoLicencia(fecha(request, "cn_fechaVencLicencia", "La fecha de vencimiento de la licencia no es válida.", errores));

        if (c.getNombres().isEmpty()) errores.add("Los nombres del cliente son obligatorios.");
        if (c.getApellidos().isEmpty()) errores.add("Los apellidos del cliente son obligatorios.");
        if (c.getFechaNacimiento() == null && texto(request, "cn_fechaNacimiento").isEmpty()) {
            errores.add("La fecha de nacimiento es obligatoria.");
        }
        return c;
    }

    private ClienteJuridico leerClienteJuridico(HttpServletRequest request, List<String> errores) {
        ClienteJuridico c = new ClienteJuridico();
        c.setRazonSocial(texto(request, "cj_razonSocial"));
        c.setRepresentanteLegal(texto(request, "cj_representante"));
        c.setGiroNegocio(texto(request, "cj_giro"));

        if (c.getRazonSocial().isEmpty()) errores.add("La razón social es obligatoria.");
        if (c.getRepresentanteLegal().isEmpty()) errores.add("El representante legal es obligatorio.");
        return c;
    }

    private Trabajador leerTrabajador(HttpServletRequest request, List<String> errores) {
        Trabajador t = new Trabajador();
        t.setNombres(texto(request, "tr_nombres"));
        t.setApellidos(texto(request, "tr_apellidos"));
        t.setCodigoEmpleado(texto(request, "tr_codigo"));

        try {
            t.setSucursalId(Integer.parseInt(texto(request, "tr_sucursalId")));
            if (t.getSucursalId() <= 0) errores.add("Seleccione una sucursal.");
        } catch (NumberFormatException e) {
            errores.add("Seleccione una sucursal.");
        }
        try {
            t.setRolTrabajadorId(Integer.parseInt(texto(request, "tr_rolId")));
            if (t.getRolTrabajadorId() <= 0) errores.add("Seleccione un rol de trabajador.");
        } catch (NumberFormatException e) {
            errores.add("Seleccione un rol de trabajador.");
        }

        if (t.getNombres().isEmpty()) errores.add("Los nombres del trabajador son obligatorios.");
        if (t.getApellidos().isEmpty()) errores.add("Los apellidos del trabajador son obligatorios.");
        if (t.getCodigoEmpleado().isEmpty()) errores.add("El código de empleado es obligatorio.");
        return t;
    }

    private LocalDate fecha(HttpServletRequest request, String nombre, String mensajeError, List<String> errores) {
        String valor = texto(request, nombre);
        if (valor.isEmpty()) return null;
        try {
            return LocalDate.parse(valor);
        } catch (DateTimeParseException e) {
            errores.add(mensajeError);
            return null;
        }
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
