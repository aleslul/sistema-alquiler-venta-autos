package com.becerra.alquilerventa.dao.impl;

import com.becerra.alquilerventa.config.Conexion;
import com.becerra.alquilerventa.dao.SucursalDAO;
import com.becerra.alquilerventa.modelo.Empresa;
import com.becerra.alquilerventa.modelo.Sucursal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAOImpl implements SucursalDAO {

    @Override
    public List<Sucursal> listar() throws SQLException {
        List<Sucursal> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_sucursales()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Sucursal s = mapear(rs);
                s.setNombreEmpresa(rs.getString("Nombre_Empresa"));
                lista.add(s);
            }
        }
        return lista;
    }

    @Override
    public Sucursal obtenerPorId(int id) throws SQLException {
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_obtener_sucursal(?)}")) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public void insertar(Sucursal s) throws SQLException {
        String sql = "{CALL sp_insertar_sucursal(?, ?, ?, ?, ?)}";
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall(sql)) {
            cs.setInt(1, s.getEmpresaId());
            cs.setString(2, s.getNombre());
            cs.setString(3, s.getDireccion());
            cs.setString(4, s.getHorarioAtencion());
            cs.setString(5, s.getEstadoSucursal());
            cs.executeUpdate();
        }
    }

    @Override
    public void actualizar(Sucursal s) throws SQLException {
        String sql = "{CALL sp_actualizar_sucursal(?, ?, ?, ?, ?, ?)}";
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall(sql)) {
            cs.setInt(1, s.getSucursalId());
            cs.setInt(2, s.getEmpresaId());
            cs.setString(3, s.getNombre());
            cs.setString(4, s.getDireccion());
            cs.setString(5, s.getHorarioAtencion());
            cs.setString(6, s.getEstadoSucursal());
            cs.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_eliminar_sucursal(?)}")) {
            cs.setInt(1, id);
            cs.executeUpdate();
        }
    }

    @Override
    public List<Empresa> listarEmpresas() throws SQLException {
        List<Empresa> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_empresas()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(new Empresa(
                    rs.getInt("Empresa_ID"),
                    rs.getString("Razon_Social"),
                    rs.getString("RUC"),
                    rs.getString("Logo_URL"),
                    rs.getString("Descripcion_Nosotros")
                ));
            }
        }
        return lista;
    }

    private Sucursal mapear(ResultSet rs) throws SQLException {
        Sucursal s = new Sucursal();
        s.setSucursalId(rs.getInt("Sucursal_ID"));
        s.setEmpresaId(rs.getInt("Empresa_ID"));
        s.setNombre(rs.getString("Nombre"));
        s.setDireccion(rs.getString("Direccion"));
        s.setHorarioAtencion(rs.getString("Horario_Atencion"));
        s.setEstadoSucursal(rs.getString("Estado_Sucursal"));
        return s;
    }
}