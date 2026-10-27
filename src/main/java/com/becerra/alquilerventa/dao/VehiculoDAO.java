package com.becerra.alquilerventa.dao;

import com.becerra.alquilerventa.modelo.*;
import java.sql.SQLException;
import java.util.List;

public interface VehiculoDAO {
    List<Vehiculo> listar() throws SQLException;
    Vehiculo obtenerPorId(int id) throws SQLException;
    void insertar(Vehiculo v) throws SQLException;
    void actualizar(Vehiculo v) throws SQLException;
    void eliminar(int id) throws SQLException;

    // Métodos para llenar los <select> del formulario
    List<Categoria> listarCategorias() throws SQLException;
    List<Marca> listarMarcas() throws SQLException;
    List<EstadoVehiculo> listarEstadosVehiculo() throws SQLException;
    List<TipoTransmision> listarTiposTransmision() throws SQLException;
    List<TipoCombustible> listarTiposCombustible() throws SQLException;
    List<Sucursal> listarSucursales() throws SQLException;
}