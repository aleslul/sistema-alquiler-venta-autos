package com.becerra.alquilerventa.modelo;

public class EstadoVehiculo {
    private int estadoVehiculoId;
    private String nombre;

    public EstadoVehiculo() {}

    public EstadoVehiculo(int estadoVehiculoId, String nombre) {
        this.estadoVehiculoId = estadoVehiculoId;
        this.nombre = nombre;
    }

    public int getEstadoVehiculoId() { return estadoVehiculoId; }
    public void setEstadoVehiculoId(int estadoVehiculoId) { this.estadoVehiculoId = estadoVehiculoId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}