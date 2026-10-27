package com.becerra.alquilerventa.modelo;

public class TipoInspeccion {
    private int tipoInspeccionId;
    private String nombre;

    public TipoInspeccion() {}

    public TipoInspeccion(int tipoInspeccionId, String nombre) {
        this.tipoInspeccionId = tipoInspeccionId;
        this.nombre = nombre;
    }

    public int getTipoInspeccionId() { return tipoInspeccionId; }
    public void setTipoInspeccionId(int tipoInspeccionId) { this.tipoInspeccionId = tipoInspeccionId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}