package com.becerra.alquilerventa.modelo;

import java.time.LocalDateTime;

public class MovimientoFlota {
    private int movimientoId;
    private int vehiculoId;
    private int sucursalId;
    private Integer reservaId; // Integer objeto porque en la BD permite NULL
    private int trabajadorId;
    private String tipoMovimiento;
    private String motivo;
    private LocalDateTime fechaHoraMovimiento;

    public MovimientoFlota() {}

    public int getMovimientoId() { return movimientoId; }
    public void setMovimientoId(int movimientoId) { this.movimientoId = movimientoId; }
    public int getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(int vehiculoId) { this.vehiculoId = vehiculoId; }
    public int getSucursalId() { return sucursalId; }
    public void setSucursalId(int sucursalId) { this.sucursalId = sucursalId; }
    public Integer getReservaId() { return reservaId; }
    public void setReservaId(Integer reservaId) { this.reservaId = reservaId; }
    public int getTrabajadorId() { return trabajadorId; }
    public void setTrabajadorId(int trabajadorId) { this.trabajadorId = trabajadorId; }
    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public LocalDateTime getFechaHoraMovimiento() { return fechaHoraMovimiento; }
    public void setFechaHoraMovimiento(LocalDateTime fechaHoraMovimiento) { this.fechaHoraMovimiento = fechaHoraMovimiento; }
}