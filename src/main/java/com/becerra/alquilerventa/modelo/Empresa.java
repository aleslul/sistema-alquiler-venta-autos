package com.becerra.alquilerventa.modelo;

public class Empresa {
    private int empresaId;
    private String razonSocial;
    private String ruc;
    private String logoUrl;
    private String descripcionNosotros;

    public Empresa() {}

    public Empresa(int empresaId, String razonSocial, String ruc, String logoUrl, String descripcionNosotros) {
        this.empresaId = empresaId;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.logoUrl = logoUrl;
        this.descripcionNosotros = descripcionNosotros;
    }

    public int getEmpresaId() { return empresaId; }
    public void setEmpresaId(int empresaId) { this.empresaId = empresaId; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getDescripcionNosotros() { return descripcionNosotros; }
    public void setDescripcionNosotros(String descripcionNosotros) { this.descripcionNosotros = descripcionNosotros; }
}