package com.becerra.alquilerventa.modelo;

public class SucursalTelefono {
    private int telefonoId;
    private int sucursalId;
    private String telefono;
    private String tipo;

    public SucursalTelefono() {}

    public SucursalTelefono(int telefonoId, int sucursalId, String telefono, String tipo) {
        this.telefonoId = telefonoId;
        this.sucursalId = sucursalId;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public int getTelefonoId() { return telefonoId; }
    public void setTelefonoId(int telefonoId) { this.telefonoId = telefonoId; }
    public int getSucursalId() { return sucursalId; }
    public void setSucursalId(int sucursalId) { this.sucursalId = sucursalId; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}