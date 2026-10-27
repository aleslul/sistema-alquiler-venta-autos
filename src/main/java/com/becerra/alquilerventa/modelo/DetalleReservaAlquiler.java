package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalleReservaAlquiler {
    private int detalleAlquilerId;
    private int reservaId;
    private int vehiculoId;
    private String nombreConductor;
    private String licenciaConductor;
    private LocalDate fechaVencLicencia;
    private BigDecimal tarifaDiariaAplicada;
    private BigDecimal subtotalVehiculo;

    public DetalleReservaAlquiler() {}

    public int getDetalleAlquilerId() { return detalleAlquilerId; }
    public void setDetalleAlquilerId(int detalleAlquilerId) { this.detalleAlquilerId = detalleAlquilerId; }
    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public int getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(int vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getNombreConductor() { return nombreConductor; }
    public void setNombreConductor(String nombreConductor) { this.nombreConductor = nombreConductor; }
    public String getLicenciaConductor() { return licenciaConductor; }
    public void setLicenciaConductor(String licenciaConductor) { this.licenciaConductor = licenciaConductor; }
    public LocalDate getFechaVencLicencia() { return fechaVencLicencia; }
    public void setFechaVencLicencia(LocalDate fechaVencLicencia) { this.fechaVencLicencia = fechaVencLicencia; }
    public BigDecimal getTarifaDiariaAplicada() { return tarifaDiariaAplicada; }
    public void setTarifaDiariaAplicada(BigDecimal tarifaDiariaAplicada) { this.tarifaDiariaAplicada = tarifaDiariaAplicada; }
    public BigDecimal getSubtotalVehiculo() { return subtotalVehiculo; }
    public void setSubtotalVehiculo(BigDecimal subtotalVehiculo) { this.subtotalVehiculo = subtotalVehiculo; }
}