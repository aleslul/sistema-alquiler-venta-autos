package com.becerra.alquilerventa.modelo;

public class Marca {
    private int marcaId;
    private String nombre;

    public Marca() {}

    public Marca(int marcaId, String nombre) {
        this.marcaId = marcaId;
        this.nombre = nombre;
    }

    public int getMarcaId() { return marcaId; }
    public void setMarcaId(int marcaId) { this.marcaId = marcaId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}