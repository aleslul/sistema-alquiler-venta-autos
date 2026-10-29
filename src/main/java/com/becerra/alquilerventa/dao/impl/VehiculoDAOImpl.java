package com.becerra.alquilerventa.dao.impl;

import com.becerra.alquilerventa.config.Conexion;
import com.becerra.alquilerventa.dao.VehiculoDAO;
import com.becerra.alquilerventa.modelo.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAOImpl implements VehiculoDAO {

    @Override
    public List<Vehiculo> listar() throws SQLException {
        List<Vehiculo> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_vehiculos()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Vehiculo v = mapear(rs);
                v.setNombreCategoria(rs.getString("Nombre_Categoria"));
                v.setNombreMarca(rs.getString("Nombre_Marca"));
                v.setNombreEstado(rs.getString("Nombre_Estado"));
                v.setNombreTransmision(rs.getString("Nombre_Transmision"));
                v.setNombreCombustible(rs.getString("Nombre_Combustible"));
                v.setNombreSucursal(rs.getString("Nombre_Sucursal"));
                lista.add(v);
            }
        }
        return lista;
    }

    @Override
    public Vehiculo obtenerPorId(int id) throws SQLException {
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_obtener_vehiculo(?)}")) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public void insertar(Vehiculo v) throws SQLException {
        String sql = "{CALL sp_insertar_vehiculo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection cn = Conexion.getConexion()) {
            cn.setAutoCommit(false);
            try (CallableStatement cs = cn.prepareCall(sql)) {
                cs.setInt(1, v.getCategoriaId());
                cs.setInt(2, v.getMarcaId());
                cs.setInt(3, v.getEstadoVehiculoId());
                cs.setInt(4, v.getTipoTransmisionId());
                cs.setInt(5, v.getTipoCombustibleId());
                cs.setInt(6, v.getSucursalActualId());
                cs.setString(7, v.getPlaca());
                cs.setString(8, v.getModelo());
                cs.setInt(9, v.getAnio());
                cs.setInt(10, v.getNumPasajeros());
                cs.setInt(11, v.getNumPuertas());
                cs.setBoolean(12, v.isAireAcondicionado());
                cs.setString(13, v.getModalidad());
                cs.registerOutParameter(14, Types.INTEGER);
                cs.executeUpdate();

                int nuevoId = cs.getInt(14);
                v.setVehiculoId(nuevoId);

                guardarModalidadEspecifica(cn, v);
                cn.commit();
            } catch (SQLException e) {
                cn.rollback();
                throw e;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    @Override
    public void actualizar(Vehiculo v) throws SQLException {
        String sql = "{CALL sp_actualizar_vehiculo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection cn = Conexion.getConexion()) {
            cn.setAutoCommit(false);
            try (CallableStatement cs = cn.prepareCall(sql)) {
                cs.setInt(1, v.getVehiculoId());
                cs.setInt(2, v.getCategoriaId());
                cs.setInt(3, v.getMarcaId());
                cs.setInt(4, v.getEstadoVehiculoId());
                cs.setInt(5, v.getTipoTransmisionId());
                cs.setInt(6, v.getTipoCombustibleId());
                cs.setInt(7, v.getSucursalActualId());
                cs.setString(8, v.getPlaca());
                cs.setString(9, v.getModelo());
                cs.setInt(10, v.getAnio());
                cs.setInt(11, v.getNumPasajeros());
                cs.setInt(12, v.getNumPuertas());
                cs.setBoolean(13, v.isAireAcondicionado());
                cs.setString(14, v.getModalidad());
                cs.executeUpdate();

                guardarModalidadEspecifica(cn, v);
                cn.commit();
            } catch (SQLException e) {
                cn.rollback();
                throw e;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    private void guardarModalidadEspecifica(Connection cn, Vehiculo v) throws SQLException {
        try (CallableStatement csLimpiar = cn.prepareCall("{CALL sp_limpiar_modalidad_vehiculo(?, ?)}")) {
            csLimpiar.setInt(1, v.getVehiculoId());
            csLimpiar.setString(2, v.getModalidad());
            csLimpiar.executeUpdate();
        }

        if (v instanceof VehiculoAlquiler) {
            VehiculoAlquiler va = (VehiculoAlquiler) v;
            try (CallableStatement csAlq = cn.prepareCall("{CALL sp_guardar_vehiculo_alquiler(?, ?, ?, ?)}")) {
                csAlq.setInt(1, v.getVehiculoId());
                if (va.getTarifaDiariaEspecifica() != null) {
                    csAlq.setBigDecimal(2, va.getTarifaDiariaEspecifica());
                } else {
                    csAlq.setNull(2, java.sql.Types.DECIMAL);
                }
                csAlq.setInt(3, va.getKilometrajeActual());
                csAlq.setBigDecimal(4, va.getDepositoGarantiaBase());
                csAlq.executeUpdate();
            }
        } else if (v instanceof VehiculoVenta) {
            VehiculoVenta vv = (VehiculoVenta) v;
            try (CallableStatement csVen = cn.prepareCall("{CALL sp_guardar_vehiculo_venta(?, ?, ?, ?)}")) {
                csVen.setInt(1, v.getVehiculoId());
                csVen.setBigDecimal(2, vv.getPrecioVenta());
                csVen.setString(3, vv.getCondicion());
                csVen.setInt(4, vv.getMesesGarantia());
                csVen.executeUpdate();
            }
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_eliminar_vehiculo(?)}")) {
            cs.setInt(1, id);
            cs.executeUpdate();
        }
    }

    @Override
    public List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_categorias()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(new Categoria(
                    rs.getInt("Categoria_ID"),
                    rs.getInt("Estado_Categoria_ID"),
                    rs.getString("Nombre"),
                    rs.getBigDecimal("Tarifa_Diaria_Base"),
                    rs.getInt("Capacidad_Maletas")
                ));
            }
        }
        return lista;
    }

    @Override
    public List<Marca> listarMarcas() throws SQLException {
        List<Marca> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_marcas()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(new Marca(rs.getInt("Marca_ID"), rs.getString("Nombre")));
            }
        }
        return lista;
    }

    @Override
    public List<EstadoVehiculo> listarEstadosVehiculo() throws SQLException {
        List<EstadoVehiculo> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_estados_vehiculo()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(new EstadoVehiculo(rs.getInt("Estado_Vehiculo_ID"), rs.getString("Nombre")));
            }
        }
        return lista;
    }

    @Override
    public List<TipoTransmision> listarTiposTransmision() throws SQLException {
        List<TipoTransmision> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_tipos_transmision()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(new TipoTransmision(rs.getInt("Tipo_Transmision_ID"), rs.getString("Nombre")));
            }
        }
        return lista;
    }

    @Override
    public List<TipoCombustible> listarTiposCombustible() throws SQLException {
        List<TipoCombustible> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConexion();
                CallableStatement cs = cn.prepareCall("{CALL sp_listar_tipos_combustible()}");
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(new TipoCombustible(rs.getInt("Tipo_Combustible_ID"), rs.getString("Nombre")));
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

    private Vehiculo mapear(ResultSet rs) throws SQLException {
        String modalidad = rs.getString("Modalidad");
        Vehiculo v;

        if ("Alquiler".equals(modalidad)) {
            VehiculoAlquiler va = new VehiculoAlquiler();
            va.setTarifaDiariaEspecifica(rs.getBigDecimal("Tarifa_Diaria_Especifica"));
            int km = rs.getInt("Kilometraje_Actual");
            if (!rs.wasNull()) va.setKilometrajeActual(km);
            va.setDepositoGarantiaBase(rs.getBigDecimal("Deposito_Garantia_Base"));
            v = va;
        } else if ("Venta".equals(modalidad)) {
            VehiculoVenta vv = new VehiculoVenta();
            vv.setPrecioVenta(rs.getBigDecimal("Precio_Venta"));
            vv.setCondicion(rs.getString("Condicion"));
            int garantia = rs.getInt("Meses_Garantia");
            if (!rs.wasNull()) vv.setMesesGarantia(garantia);
            v = vv;
        } else {
            v = new Vehiculo();
        }

        // Campos comunes de la clase padre Vehiculo
        v.setVehiculoId(rs.getInt("Vehiculo_ID"));
        v.setCategoriaId(rs.getInt("Categoria_ID"));
        v.setMarcaId(rs.getInt("Marca_ID"));
        v.setEstadoVehiculoId(rs.getInt("Estado_Vehiculo_ID"));
        v.setTipoTransmisionId(rs.getInt("Tipo_Transmision_ID"));
        v.setTipoCombustibleId(rs.getInt("Tipo_Combustible_ID"));
        v.setSucursalActualId(rs.getInt("Sucursal_Actual_ID"));
        v.setPlaca(rs.getString("Placa"));
        v.setModelo(rs.getString("Modelo"));
        v.setAnio(rs.getInt("Anio"));
        v.setNumPasajeros(rs.getInt("Num_Pasajeros"));
        v.setNumPuertas(rs.getInt("Num_Puertas"));
        v.setAireAcondicionado(rs.getBoolean("Aire_Acondicionado"));
        v.setModalidad(modalidad);

        return v;
    }
}