package com.becerra.alquilerventa.dao;

import com.becerra.alquilerventa.modelo.Empresa;
import com.becerra.alquilerventa.modelo.Sucursal;
import java.sql.SQLException;
import java.util.List;

public interface SucursalDAO {
    List<Sucursal> listar() throws SQLException;
    Sucursal obtenerPorId(int id) throws SQLException;
    void insertar(Sucursal s) throws SQLException;
    void actualizar(Sucursal s) throws SQLException;
    void eliminar(int id) throws SQLException;
    List<Empresa> listarEmpresas() throws SQLException;
}