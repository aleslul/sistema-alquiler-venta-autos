package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;

public class ReservaAccesorio {
    private int reservaId;
    private int accesorioId;
    private int cantidad;
    private BigDecimal subtotalAccesorio;

    public ReservaAccesorio() {}

    public ReservaAccesorio(int reservaId, int accesorioId, int cantidad, BigDecimal subtotalAccesorio) {
        this.reservaId = reservaId;
        this.accesorioId = accesorioId;
        this.cantidad = cantidad;
        this.subtotalAccesorio = subtotalAccesorio;
    }

    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public int getAccesorioId() { return accesorioId; }
    public void setAccesorioId(int accesorioId) { this.accesorioId = accesorioId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getSubtotalAccesorio() { return subtotalAccesorio; }
    public void setSubtotalAccesorio(BigDecimal subtotalAccesorio) { this.subtotalAccesorio = subtotalAccesorio; }
}