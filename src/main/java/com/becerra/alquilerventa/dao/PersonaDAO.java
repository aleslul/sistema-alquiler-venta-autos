package com.becerra.alquilerventa.dao;

import com.becerra.alquilerventa.modelo.Persona;
import com.becerra.alquilerventa.modelo.RolTrabajador;
import com.becerra.alquilerventa.modelo.Sucursal;
import com.becerra.alquilerventa.modelo.TipoDocumento;
import java.util.List;

public interface PersonaDAO {
    List<Persona> listar();
    Persona obtenerPorId(int id);
    void insertar(Persona p);
    void actualizar(Persona p);
    void eliminar(int id);

    List<TipoDocumento> listarTiposDocumento();
    List<RolTrabajador> listarRolesTrabajador();
    List<Sucursal> listarSucursales();
}