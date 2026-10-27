package com.becerra.alquilerventa.modelo;

import java.time.LocalDateTime;

public class Inspeccion {
    private int inspeccionId;
    private int reservaId;
    private int vehiculoId;
    private int trabajadorId;
    private int tipoInspeccionId;
    private int kilometraje;
    private String nivelCombustible;
    private LocalDateTime fechaHora;
    private String observaciones;

    public Inspeccion() {}

    public int getInspeccionId() { return inspeccionId; }
    public void setInspeccionId(int inspeccionId) { this.inspeccionId = inspeccionId; }
    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public int getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(int vehiculoId) { this.vehiculoId = vehiculoId; }
    public int getTrabajadorId() { return trabajadorId; }
    public void setTrabajadorId(int trabajadorId) { this.trabajadorId = trabajadorId; }
    public int getTipoInspeccionId() { return tipoInspeccionId; }
    public void setTipoInspeccionId(int tipoInspeccionId) { this.tipoInspeccionId = tipoInspeccionId; }
    public int getKilometraje() { return kilometraje; }
    public void setKilometraje(int kilometraje) { this.kilometraje = kilometraje; }
    public String getNivelCombustible() { return nivelCombustible; }
    public void setNivelCombustible(String nivelCombustible) { this.nivelCombustible = nivelCombustible; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}