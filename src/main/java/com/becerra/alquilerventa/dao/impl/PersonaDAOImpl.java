package com.becerra.alquilerventa.dao.impl;

import com.becerra.alquilerventa.config.Conexion;
import com.becerra.alquilerventa.dao.PersonaDAO;
import com.becerra.alquilerventa.modelo.Persona;
import com.becerra.alquilerventa.modelo.TipoDocumento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                Persona p = mapear(rs);
                p.setNombreTipoDocumento(rs.getString("Nombre_Tipo_Documento"));
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
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public void insertar(Persona p) throws SQLException {
        String sql = "{CALL sp_insertar_persona(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall(sql)) {
            cs.setInt(1, p.getTipoDocumentoId());
            cs.setString(2, p.getNumeroDocumento());
            cs.setString(3, p.getCorreo());
            cs.setString(4, p.getTelefono());
            cs.setString(5, p.getDireccion());
            cs.setString(6, p.getEstadoUsuario());
            cs.setString(7, p.getPasswordHash());
            cs.executeUpdate();
        }
    }

    @Override
    public void actualizar(Persona p) throws SQLException {
        String sql = "{CALL sp_actualizar_persona(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall(sql)) {
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

    private Persona mapear(ResultSet rs) throws SQLException {
        Persona p = new Persona();
        p.setPersonaId(rs.getInt("Persona_ID"));
        p.setTipoDocumentoId(rs.getInt("Tipo_Documento_ID"));
        p.setNumeroDocumento(rs.getString("Numero_Documento"));
        p.setCorreo(rs.getString("Correo"));
        p.setTelefono(rs.getString("Telefono"));
        p.setDireccion(rs.getString("Direccion"));
        p.setEstadoUsuario(rs.getString("Estado_Usuario"));
        p.setPasswordHash(rs.getString("Password_Hash"));
        return p;
    }
}