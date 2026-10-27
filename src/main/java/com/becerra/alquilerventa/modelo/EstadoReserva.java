package com.becerra.alquilerventa.modelo;

public class EstadoReserva {
    private int estadoReservaId;
    private String nombre;

    public EstadoReserva() {}

    public EstadoReserva(int estadoReservaId, String nombre) {
        this.estadoReservaId = estadoReservaId;
        this.nombre = nombre;
    }

    public int getEstadoReservaId() { return estadoReservaId; }
    public void setEstadoReservaId(int estadoReservaId) { this.estadoReservaId = estadoReservaId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}