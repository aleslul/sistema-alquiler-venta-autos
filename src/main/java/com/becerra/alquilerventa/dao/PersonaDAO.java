package com.becerra.alquilerventa.dao;

import com.becerra.alquilerventa.modelo.Persona;
import com.becerra.alquilerventa.modelo.RolTrabajador;
import com.becerra.alquilerventa.modelo.Sucursal;
import com.becerra.alquilerventa.modelo.TipoDocumento;
import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO {
    List<Persona> listar() throws SQLException;
    Persona obtenerPorId(int id) throws SQLException;
    void insertar(Persona p) throws SQLException;
    void actualizar(Persona p) throws SQLException;
    void eliminar(int id) throws SQLException;

    List<TipoDocumento> listarTiposDocumento() throws SQLException;
    List<RolTrabajador> listarRolesTrabajador() throws SQLException;
    List<Sucursal> listarSucursales() throws SQLException;
}
