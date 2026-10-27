package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;

public class Accesorio {
    private int accesorioId;
    private String nombre;
    private BigDecimal costo;
    private String tipoCobro;

    public Accesorio() {}

    public Accesorio(int accesorioId, String nombre, BigDecimal costo, String tipoCobro) {
        this.accesorioId = accesorioId;
        this.nombre = nombre;
        this.costo = costo;
        this.tipoCobro = tipoCobro;
    }

    public int getAccesorioId() { return accesorioId; }
    public void setAccesorioId(int accesorioId) { this.accesorioId = accesorioId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
    public String getTipoCobro() { return tipoCobro; }
    public void setTipoCobro(String tipoCobro) { this.tipoCobro = tipoCobro; }
}