package com.becerra.alquilerventa.dao.impl;

import com.becerra.alquilerventa.config.Conexion;
import com.becerra.alquilerventa.dao.PersonaDAO;
import com.becerra.alquilerventa.modelo.ClienteJuridico;
import com.becerra.alquilerventa.modelo.ClienteNatural;
import com.becerra.alquilerventa.modelo.Persona;
import com.becerra.alquilerventa.modelo.RolTrabajador;
import com.becerra.alquilerventa.modelo.Sucursal;
import com.becerra.alquilerventa.modelo.TipoDocumento;
import com.becerra.alquilerventa.modelo.Trabajador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAOImpl implements PersonaDAO {

    @Override
    public List<Persona> listar() throws SQLException {
        List<Persona> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_personas()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                String tipoReg = rs.getString("Tipo_Registro");
                Persona p = switch (tipoReg != null ? tipoReg : "") {
                    case "Trabajador" -> {
                        Trabajador t = new Trabajador();
                        t.setCodigoEmpleado(rs.getString("Codigo_Empleado"));
                        yield t;
                    }
                    case "Cliente Natural" -> new ClienteNatural();
                    case "Cliente Jurídico" -> new ClienteJuridico();
                    default -> new Persona();
                };

                llenarBase(rs, p);
                p.setNombreTipoDocumento(rs.getString("Nombre_Tipo_Documento"));
                p.setTipoRegistro(tipoReg);
                p.setNombreCompleto(rs.getString("Nombre_Completo"));
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public Persona obtenerPorId(int id) throws SQLException {
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_obtener_persona(?)}")) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (!rs.next()) return null;

                String codigo = rs.getString("Codigo_Tipo");
                Persona p = switch (codigo) {
                    case Persona.CLIENTE_NATURAL -> {
                        ClienteNatural c = new ClienteNatural();
                        c.setNombres(rs.getString("CN_Nombres"));
                        c.setApellidos(rs.getString("CN_Apellidos"));
                        c.setFechaNacimiento(fecha(rs, "CN_Fecha_Nacimiento"));
                        c.setLicenciaConducir(rs.getString("CN_Licencia_Conducir"));
                        c.setFechaVencimientoLicencia(fecha(rs, "CN_Fecha_Vencimiento_Licencia"));
                        yield c;
                    }
                    case Persona.CLIENTE_JURIDICO -> {
                        ClienteJuridico c = new ClienteJuridico();
                        c.setRazonSocial(rs.getString("CJ_Razon_Social"));
                        c.setRepresentanteLegal(rs.getString("CJ_Representante_Legal"));
                        c.setGiroNegocio(rs.getString("CJ_Giro_Negocio"));
                        yield c;
                    }
                    case Persona.TRABAJADOR -> {
                        Trabajador t = new Trabajador();
                        t.setSucursalId(rs.getInt("T_Sucursal_ID"));
                        t.setRolTrabajadorId(rs.getInt("T_Rol_Trabajador_ID"));
                        t.setNombres(rs.getString("T_Nombres"));
                        t.setApellidos(rs.getString("T_Apellidos"));
                        t.setCodigoEmpleado(rs.getString("T_Codigo_Empleado"));
                        yield t;
                    }
                    default -> new Persona();
                };
                llenarBase(rs, p);
                return p;
            }
        }
    }

    @Override
    public void insertar(Persona p) throws SQLException {
        try (Connection cn = Conexion.getConexion()) {
            cn.setAutoCommit(false);
            try {
                try (CallableStatement cs = cn.prepareCall("{CALL sp_insertar_persona(?, ?, ?, ?, ?, ?, ?, ?)}")) {
                    cs.setInt(1, p.getTipoDocumentoId());
                    cs.setString(2, p.getNumeroDocumento());
                    cs.setString(3, p.getCorreo());
                    cs.setString(4, p.getTelefono());
                    cs.setString(5, p.getDireccion());
                    cs.setString(6, p.getEstadoUsuario());
                    cs.setString(7, p.getPasswordHash());
                    cs.registerOutParameter(8, Types.INTEGER);
                    cs.executeUpdate();
                    p.setPersonaId(cs.getInt(8));
                }
                guardarRol(cn, p);
                cn.commit();
            } catch (SQLException | RuntimeException e) {
                cn.rollback();
                throw e;
            }
        }
    }

    @Override
    public void actualizar(Persona p) throws SQLException {
        try (Connection cn = Conexion.getConexion()) {
            cn.setAutoCommit(false);
            try {
                try (CallableStatement cs = cn.prepareCall("{CALL sp_actualizar_persona(?, ?, ?, ?, ?, ?, ?, ?)}")) {
                    cs.setInt(1, p.getPersonaId());
                    cs.setInt(2, p.getTipoDocumentoId());
                    cs.setString(3, p.getNumeroDocumento());
                    cs.setString(4, p.getCorreo());
                    cs.setString(5, p.getTelefono());
                    cs.setString(6, p.getDireccion());
                    cs.setString(7, p.getEstadoUsuario());
                    cs.setString(8, p.getPasswordHash());
                    cs.executeUpdate();
                }
                // Quita los roles que ya no corresponden (por si cambió de tipo)
                try (CallableStatement cs = cn.prepareCall("{CALL sp_limpiar_roles_persona(?, ?)}")) {
                    cs.setInt(1, p.getPersonaId());
                    cs.setString(2, p.getCodigoTipo());
                    cs.executeUpdate();
                }
                guardarRol(cn, p);
                cn.commit();
            } catch (SQLException | RuntimeException e) {
                cn.rollback();
                throw e;
            }
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_eliminar_persona(?)}")) {
            cs.setInt(1, id);
            cs.executeUpdate();
        }
    }

    @Override
    public List<TipoDocumento> listarTiposDocumento() throws SQLException {
        List<TipoDocumento> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_tipos_documento()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(new TipoDocumento(
                    rs.getInt("Tipo_Documento_ID"),
                    rs.getString("Nombre")
                ));
            }
        }
        return lista;
    }

    @Override
    public List<RolTrabajador> listarRolesTrabajador() throws SQLException {
        List<RolTrabajador> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_roles_trabajador()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(new RolTrabajador(
                    rs.getInt("Rol_Trabajador_ID"),
                    rs.getString("Nombre")
                ));
            }
        }
        return lista;
    }

    @Override
    public List<Sucursal> listarSucursales() throws SQLException {
        List<Sucursal> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_sucursales_combo()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Sucursal s = new Sucursal();
                s.setSucursalId(rs.getInt("Sucursal_ID"));
                s.setNombre(rs.getString("Nombre"));
                lista.add(s);
            }
        }
        return lista;
    }


    private void guardarRol(Connection cn, Persona p) throws SQLException {
        if (p instanceof ClienteNatural c) {
            try (CallableStatement cs = cn.prepareCall("{CALL sp_guardar_cliente_natural(?, ?, ?, ?, ?, ?)}")) {
                cs.setInt(1, c.getPersonaId());
                cs.setString(2, c.getNombres());
                cs.setString(3, c.getApellidos());
                setFecha(cs, 4, c.getFechaNacimiento());
                cs.setString(5, c.getLicenciaConducir());
                setFecha(cs, 6, c.getFechaVencimientoLicencia());
                cs.executeUpdate();
            }
        } else if (p instanceof ClienteJuridico c) {
            try (CallableStatement cs = cn.prepareCall("{CALL sp_guardar_cliente_juridico(?, ?, ?, ?)}")) {
                cs.setInt(1, c.getPersonaId());
                cs.setString(2, c.getRazonSocial());
                cs.setString(3, c.getRepresentanteLegal());
                cs.setString(4, c.getGiroNegocio());
                cs.executeUpdate();
            }
        } else if (p instanceof Trabajador t) {
            try (CallableStatement cs = cn.prepareCall("{CALL sp_guardar_trabajador(?, ?, ?, ?, ?, ?)}")) {
                cs.setInt(1, t.getPersonaId());
                cs.setInt(2, t.getSucursalId());
                cs.setInt(3, t.getRolTrabajadorId());
                cs.setString(4, t.getNombres());
                cs.setString(5, t.getApellidos());
                cs.setString(6, t.getCodigoEmpleado());
                cs.executeUpdate();
            }
        }
    }

    private void llenarBase(ResultSet rs, Persona p) throws SQLException {
        p.setPersonaId(rs.getInt("Persona_ID"));
        p.setTipoDocumentoId(rs.getInt("Tipo_Documento_ID"));
        p.setNumeroDocumento(rs.getString("Numero_Documento"));
        p.setCorreo(rs.getString("Correo"));
        p.setTelefono(rs.getString("Telefono"));
        p.setDireccion(rs.getString("Direccion"));
        p.setEstadoUsuario(rs.getString("Estado_Usuario"));
        p.setPasswordHash(rs.getString("Password_Hash"));
    }

    private LocalDate fecha(ResultSet rs, String columna) throws SQLException {
        Date d = rs.getDate(columna);
        return d == null ? null : d.toLocalDate();
    }

    private void setFecha(CallableStatement cs, int indice, LocalDate valor) throws SQLException {
        if (valor == null) {
            cs.setNull(indice, Types.DATE);
        } else {
            cs.setDate(indice, Date.valueOf(valor));
        }
    }
}
