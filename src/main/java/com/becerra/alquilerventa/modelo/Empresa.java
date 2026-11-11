package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Empresa_ID")
    private int empresaId;

    @Column(name = "Razon_Social", length = 150, nullable = false)
    private String razonSocial;

    @Column(name = "RUC", length = 11, nullable = false, unique = true)
    private String ruc;

    @Column(name = "Logo_URL", length = 255)
    private String logoUrl;

    @Column(name = "Descripcion_Nosotros", columnDefinition = "TEXT")
    private String descripcionNosotros;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private List<Sucursal> sucursales = new ArrayList<>();

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
    public List<Sucursal> getSucursales() { return sucursales; }
    public void setSucursales(List<Sucursal> sucursales) { this.sucursales = sucursales; }
}