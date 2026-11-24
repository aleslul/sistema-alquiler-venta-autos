package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente_Juridico")
@PrimaryKeyJoinColumn(name = "Cliente_ID")
public class ClienteJuridico extends Cliente {
    @Column(name = "Razon_Social", length = 150, nullable = false)
    private String razonSocial;

    @Column(name = "Representante_Legal", length = 150, nullable = false)
    private String representanteLegal;

    @Column(name = "Giro_Negocio", length = 100)
    private String giroNegocio;

    public ClienteJuridico() { setTipoCliente("Juridico"); }

    @Override
    public String getCodigoTipo() { return CLIENTE_JURIDICO; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getRepresentanteLegal() { return representanteLegal; }
    public void setRepresentanteLegal(String representanteLegal) { this.representanteLegal = representanteLegal; }
    public String getGiroNegocio() { return giroNegocio; }
    public void setGiroNegocio(String giroNegocio) { this.giroNegocio = giroNegocio; }
}