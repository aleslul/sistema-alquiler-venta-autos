package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;

public class Incidencia {
    private int incidenciaId;
    private int reservaId;
    private int vehiculoId;
    private int trabajadorId;
    private int gravedadId;
    private int estadoPagoId;
    private String descripcion;
    private BigDecimal costoPenalidad;

    public Incidencia() {}

    public int getIncidenciaId() { return incidenciaId; }
    public void setIncidenciaId(int incidenciaId) { this.incidenciaId = incidenciaId; }
    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public int getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(int vehiculoId) { this.vehiculoId = vehiculoId; }
    public int getTrabajadorId() { return trabajadorId; }
    public void setTrabajadorId(int trabajadorId) { this.trabajadorId = trabajadorId; }
    public int getGravedadId() { return gravedadId; }
    public void setGravedadId(int gravedadId) { this.gravedadId = gravedadId; }
    public int getEstadoPagoId() { return estadoPagoId; }
    public void setEstadoPagoId(int estadoPagoId) { this.estadoPagoId = estadoPagoId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getCostoPenalidad() { return costoPenalidad; }
    public void setCostoPenalidad(BigDecimal costoPenalidad) { this.costoPenalidad = costoPenalidad; }
}