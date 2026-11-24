package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Trabajador")
@PrimaryKeyJoinColumn(name = "Trabajador_ID")
public class Trabajador extends Persona {
    @Column(name = "Sucursal_ID", nullable = false)
    private int sucursalId;

    @Column(name = "Rol_Trabajador_ID", nullable = false)
    private int rolTrabajadorId;

    @Column(name = "Nombres", length = 100, nullable = false)
    private String nombres;

    @Column(name = "Apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "Codigo_Empleado", length = 50, nullable = false, unique = true)
    private String codigoEmpleado;

    public Trabajador() {}

    @Override
    public String getCodigoTipo() { return TRABAJADOR; }

    public int getTrabajadorId() { return getPersonaId(); }
    public void setTrabajadorId(int trabajadorId) { setPersonaId(trabajadorId); }
    public int getSucursalId() { return sucursalId; }
    public void setSucursalId(int sucursalId) { this.sucursalId = sucursalId; }
    public int getRolTrabajadorId() { return rolTrabajadorId; }
    public void setRolTrabajadorId(int rolTrabajadorId) { this.rolTrabajadorId = rolTrabajadorId; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getCodigoEmpleado() { return codigoEmpleado; }
    public void setCodigoEmpleado(String codigoEmpleado) { this.codigoEmpleado = codigoEmpleado; }
}