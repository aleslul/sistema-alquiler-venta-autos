package com.becerra.alquilerventa.modelo;

public class Trabajador extends Persona {
    private int sucursalId;
    private int rolTrabajadorId;
    private String nombres;
    private String apellidos;
    private String codigoEmpleado;

    public Trabajador() {}

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