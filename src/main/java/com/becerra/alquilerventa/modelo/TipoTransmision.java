package com.becerra.alquilerventa.modelo;

public class TipoTransmision {
    private int tipoTransmisionId;
    private String nombre;

    public TipoTransmision() {}

    public TipoTransmision(int tipoTransmisionId, String nombre) {
        this.tipoTransmisionId = tipoTransmisionId;
        this.nombre = nombre;
    }

    public int getTipoTransmisionId() { return tipoTransmisionId; }
    public void setTipoTransmisionId(int tipoTransmisionId) { this.tipoTransmisionId = tipoTransmisionId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}