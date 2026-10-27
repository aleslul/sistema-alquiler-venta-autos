package com.becerra.alquilerventa.modelo;

public class EstadoPago {
    private int estadoPagoId;
    private String nombre;

    public EstadoPago() {}

    public EstadoPago(int estadoPagoId, String nombre) {
        this.estadoPagoId = estadoPagoId;
        this.nombre = nombre;
    }

    public int getEstadoPagoId() { return estadoPagoId; }
    public void setEstadoPagoId(int estadoPagoId) { this.estadoPagoId = estadoPagoId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}