package com.becerra.alquilerventa.modelo;

public class TipoCombustible {
    private int tipoCombustibleId;
    private String nombre;

    public TipoCombustible() {}

    public TipoCombustible(int tipoCombustibleId, String nombre) {
        this.tipoCombustibleId = tipoCombustibleId;
        this.nombre = nombre;
    }

    public int getTipoCombustibleId() { return tipoCombustibleId; }
    public void setTipoCombustibleId(int tipoCombustibleId) { this.tipoCombustibleId = tipoCombustibleId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}