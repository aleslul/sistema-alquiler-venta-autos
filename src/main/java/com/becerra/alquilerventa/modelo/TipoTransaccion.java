package com.becerra.alquilerventa.modelo;

public class TipoTransaccion {
    private int tipoTransaccionId;
    private String nombre;

    public TipoTransaccion() {}

    public TipoTransaccion(int tipoTransaccionId, String nombre) {
        this.tipoTransaccionId = tipoTransaccionId;
        this.nombre = nombre;
    }

    public int getTipoTransaccionId() { return tipoTransaccionId; }
    public void setTipoTransaccionId(int tipoTransaccionId) { this.tipoTransaccionId = tipoTransaccionId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}