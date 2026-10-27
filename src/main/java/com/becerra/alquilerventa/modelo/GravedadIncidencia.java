package com.becerra.alquilerventa.modelo;

public class GravedadIncidencia {
    private int gravedadIncidenciaId;
    private String nombre;

    public GravedadIncidencia() {}

    public GravedadIncidencia(int gravedadIncidenciaId, String nombre) {
        this.gravedadIncidenciaId = gravedadIncidenciaId;
        this.nombre = nombre;
    }

    public int getGravedadIncidenciaId() { return gravedadIncidenciaId; }
    public void setGravedadIncidenciaId(int gravedadIncidenciaId) { this.gravedadIncidenciaId = gravedadIncidenciaId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}