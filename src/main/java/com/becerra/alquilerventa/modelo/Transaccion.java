package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaccion {
    private int transaccionId;
    private int reservaId;
    private int tipoTransaccionId;
    private int estadoPagoId;
    private BigDecimal monto;
    private LocalDateTime fecha;

    public Transaccion() {}

    public int getTransaccionId() { return transaccionId; }
    public void setTransaccionId(int transaccionId) { this.transaccionId = transaccionId; }
    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public int getTipoTransaccionId() { return tipoTransaccionId; }
    public void setTipoTransaccionId(int tipoTransaccionId) { this.tipoTransaccionId = tipoTransaccionId; }
    public int getEstadoPagoId() { return estadoPagoId; }
    public void setEstadoPagoId(int estadoPagoId) { this.estadoPagoId = estadoPagoId; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}