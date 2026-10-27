package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;

public class VehiculoAlquiler extends Vehiculo {
    private BigDecimal tarifaDiariaEspecifica;
    private int kilometrajeActual;
    private BigDecimal depositoGarantiaBase;

    public VehiculoAlquiler() {}

    public BigDecimal getTarifaDiariaEspecifica() { return tarifaDiariaEspecifica; }
    public void setTarifaDiariaEspecifica(BigDecimal tarifaDiariaEspecifica) { this.tarifaDiariaEspecifica = tarifaDiariaEspecifica; }
    public int getKilometrajeActual() { return kilometrajeActual; }
    public void setKilometrajeActual(int kilometrajeActual) { this.kilometrajeActual = kilometrajeActual; }
    public BigDecimal getDepositoGarantiaBase() { return depositoGarantiaBase; }
    public void setDepositoGarantiaBase(BigDecimal depositoGarantiaBase) { this.depositoGarantiaBase = depositoGarantiaBase; }
}