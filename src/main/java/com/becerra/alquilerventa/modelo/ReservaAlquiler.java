package com.becerra.alquilerventa.modelo;

import java.time.LocalDateTime;

public class ReservaAlquiler extends Reserva {
    private int sucursalRecogidaId;
    private int sucursalDevolucionId;
    private LocalDateTime fechaHoraRecogida;
    private LocalDateTime fechaHoraDevPrevista;
    private LocalDateTime fechaHoraDevReal;

    public ReservaAlquiler() {}

    public int getSucursalRecogidaId() { return sucursalRecogidaId; }
    public void setSucursalRecogidaId(int sucursalRecogidaId) { this.sucursalRecogidaId = sucursalRecogidaId; }
    public int getSucursalDevolucionId() { return sucursalDevolucionId; }
    public void setSucursalDevolucionId(int sucursalDevolucionId) { this.sucursalDevolucionId = sucursalDevolucionId; }
    public LocalDateTime getFechaHoraRecogida() { return fechaHoraRecogida; }
    public void setFechaHoraRecogida(LocalDateTime fechaHoraRecogida) { this.fechaHoraRecogida = fechaHoraRecogida; }
    public LocalDateTime getFechaHoraDevPrevista() { return fechaHoraDevPrevista; }
    public void setFechaHoraDevPrevista(LocalDateTime fechaHoraDevPrevista) { this.fechaHoraDevPrevista = fechaHoraDevPrevista; }
    public LocalDateTime getFechaHoraDevReal() { return fechaHoraDevReal; }
    public void setFechaHoraDevReal(LocalDateTime fechaHoraDevReal) { this.fechaHoraDevReal = fechaHoraDevReal; }
}