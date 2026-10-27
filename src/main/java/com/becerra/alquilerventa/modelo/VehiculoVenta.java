package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;

public class VehiculoVenta extends Vehiculo {
    private BigDecimal precioVenta;
    private String condicion; // 'Nuevo', 'Seminuevo', 'Usado'
    private int mesesGarantia = 12;

    public VehiculoVenta() {}

    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public String getCondicion() { return condicion; }
    public void setCondicion(String condicion) { this.condicion = condicion; }
    public int getMesesGarantia() { return mesesGarantia; }
    public void setMesesGarantia(int mesesGarantia) { this.mesesGarantia = mesesGarantia; }
}