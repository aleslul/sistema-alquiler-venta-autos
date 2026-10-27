package com.becerra.alquilerventa.modelo;

public class Sucursal {
    private int sucursalId;
    private int empresaId;
    private String nombre;
    private String direccion;
    private String horarioAtencion;
    private String estadoSucursal;
    private String nombreEmpresa;

    public Sucursal() {}

    public Sucursal(int sucursalId, int empresaId, String nombre, String direccion, String horarioAtencion, String estadoSucursal) {
        this.sucursalId = sucursalId;
        this.empresaId = empresaId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarioAtencion = horarioAtencion;
        this.estadoSucursal = estadoSucursal;
    }

    public int getSucursalId() { return sucursalId; }
    public void setSucursalId(int sucursalId) { this.sucursalId = sucursalId; }
    public int getEmpresaId() { return empresaId; }
    public void setEmpresaId(int empresaId) { this.empresaId = empresaId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getHorarioAtencion() { return horarioAtencion; }
    public void setHorarioAtencion(String horarioAtencion) { this.horarioAtencion = horarioAtencion; }
    public String getEstadoSucursal() { return estadoSucursal; }
    public void setEstadoSucursal(String estadoSucursal) { this.estadoSucursal = estadoSucursal; }
    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }
}