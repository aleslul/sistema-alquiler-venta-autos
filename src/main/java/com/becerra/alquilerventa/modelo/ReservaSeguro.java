package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;

public class ReservaSeguro {
    private int reservaId;
    private int seguroId;
    private BigDecimal costoAplicado;

    public ReservaSeguro() {}

    public ReservaSeguro(int reservaId, int seguroId, BigDecimal costoAplicado) {
        this.reservaId = reservaId;
        this.seguroId = seguroId;
        this.costoAplicado = costoAplicado;
    }

    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public int getSeguroId() { return seguroId; }
    public void setSeguroId(int seguroId) { this.seguroId = seguroId; }
    public BigDecimal getCostoAplicado() { return costoAplicado; }
    public void setCostoAplicado(BigDecimal costoAplicado) { this.costoAplicado = costoAplicado; }
}