package com.becerra.alquilerventa.modelo;

public class EstadoCategoria {
    private int estadoCategoriaId;
    private String nombre;

    public EstadoCategoria() {}

    public EstadoCategoria(int estadoCategoriaId, String nombre) {
        this.estadoCategoriaId = estadoCategoriaId;
        this.nombre = nombre;
    }

    public int getEstadoCategoriaId() { return estadoCategoriaId; }
    public void setEstadoCategoriaId(int estadoCategoriaId) { this.estadoCategoriaId = estadoCategoriaId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}