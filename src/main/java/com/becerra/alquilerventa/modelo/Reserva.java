package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reserva {
    private int reservaId;
    private int clienteId;
    private int estadoReservaId;
    private LocalDateTime fechaCreacion;
    private String tipoReserva; // 'Alquiler' o 'Venta'
    private BigDecimal montoTotal;

    public Reserva() {}

    public Reserva(int reservaId, int clienteId, int estadoReservaId, LocalDateTime fechaCreacion, String tipoReserva, BigDecimal montoTotal) {
        this.reservaId = reservaId;
        this.clienteId = clienteId;
        this.estadoReservaId = estadoReservaId;
        this.fechaCreacion = fechaCreacion;
        this.tipoReserva = tipoReserva;
        this.montoTotal = montoTotal;
    }

    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public int getEstadoReservaId() { return estadoReservaId; }
    public void setEstadoReservaId(int estadoReservaId) { this.estadoReservaId = estadoReservaId; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getTipoReserva() { return tipoReserva; }
    public void setTipoReserva(String tipoReserva) { this.tipoReserva = tipoReserva; }
    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }
}