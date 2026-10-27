package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaVenta extends Reserva {
    private int vehiculoId;
    private int sucursalEntregaId;
    private BigDecimal precioAcordado;
    private BigDecimal montoSeparacion;
    private String modalidadCompra;
    private LocalDate fechaEntregaPactada;

    public ReservaVenta() {}

    public int getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(int vehiculoId) { this.vehiculoId = vehiculoId; }
    public int getSucursalEntregaId() { return sucursalEntregaId; }
    public void setSucursalEntregaId(int sucursalEntregaId) { this.sucursalEntregaId = sucursalEntregaId; }
    public BigDecimal getPrecioAcordado() { return precioAcordado; }
    public void setPrecioAcordado(BigDecimal precioAcordado) { this.precioAcordado = precioAcordado; }
    public BigDecimal getMontoSeparacion() { return montoSeparacion; }
    public void setMontoSeparacion(BigDecimal montoSeparacion) { this.montoSeparacion = montoSeparacion; }
    public String getModalidadCompra() { return modalidadCompra; }
    public void setModalidadCompra(String modalidadCompra) { this.modalidadCompra = modalidadCompra; }
    public LocalDate getFechaEntregaPactada() { return fechaEntregaPactada; }
    public void setFechaEntregaPactada(LocalDate fechaEntregaPactada) { this.fechaEntregaPactada = fechaEntregaPactada; }
}