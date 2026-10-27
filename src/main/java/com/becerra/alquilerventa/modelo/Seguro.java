package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;

public class Seguro {
    private int seguroId;
    private String nombre;
    private BigDecimal costoDiario;
    private BigDecimal responsabilidadDanios;
    private int limiteKilometraje;
    private BigDecimal costoKmExtra;

    public Seguro() {}

    public Seguro(int seguroId, String nombre, BigDecimal costoDiario, BigDecimal responsabilidadDanios, int limiteKilometraje, BigDecimal costoKmExtra) {
        this.seguroId = seguroId;
        this.nombre = nombre;
        this.costoDiario = costoDiario;
        this.responsabilidadDanios = responsabilidadDanios;
        this.limiteKilometraje = limiteKilometraje;
        this.costoKmExtra = costoKmExtra;
    }

    public int getSeguroId() { return seguroId; }
    public void setSeguroId(int seguroId) { this.seguroId = seguroId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getCostoDiario() { return costoDiario; }
    public void setCostoDiario(BigDecimal costoDiario) { this.costoDiario = costoDiario; }
    public BigDecimal getResponsabilidadDanios() { return responsabilidadDanios; }
    public void setResponsabilidadDanios(BigDecimal responsabilidadDanios) { this.responsabilidadDanios = responsabilidadDanios; }
    public int getLimiteKilometraje() { return limiteKilometraje; }
    public void setLimiteKilometraje(int limiteKilometraje) { this.limiteKilometraje = limiteKilometraje; }
    public BigDecimal getCostoKmExtra() { return costoKmExtra; }
    public void setCostoKmExtra(BigDecimal costoKmExtra) { this.costoKmExtra = costoKmExtra; }
}