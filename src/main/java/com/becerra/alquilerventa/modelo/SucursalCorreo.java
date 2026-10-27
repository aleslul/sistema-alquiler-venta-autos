package com.becerra.alquilerventa.modelo;

public class SucursalCorreo {
    private int correoId;
    private int sucursalId;
    private String correo;
    private String tipoArea;

    public SucursalCorreo() {}

    public SucursalCorreo(int correoId, int sucursalId, String correo, String tipoArea) {
        this.correoId = correoId;
        this.sucursalId = sucursalId;
        this.correo = correo;
        this.tipoArea = tipoArea;
    }

    public int getCorreoId() { return correoId; }
    public void setCorreoId(int correoId) { this.correoId = correoId; }
    public int getSucursalId() { return sucursalId; }
    public void setSucursalId(int sucursalId) { this.sucursalId = sucursalId; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTipoArea() { return tipoArea; }
    public void setTipoArea(String tipoArea) { this.tipoArea = tipoArea; }
}