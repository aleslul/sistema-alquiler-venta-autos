package com.becerra.alquilerventa.modelo;

public class ClienteJuridico extends Cliente {
    private String razonSocial;
    private String representanteLegal;
    private String giroNegocio;

    public ClienteJuridico() {}

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getRepresentanteLegal() { return representanteLegal; }
    public void setRepresentanteLegal(String representanteLegal) { this.representanteLegal = representanteLegal; }
    public String getGiroNegocio() { return giroNegocio; }
    public void setGiroNegocio(String giroNegocio) { this.giroNegocio = giroNegocio; }
}