package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Sucursal")
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sucursal_ID")
    private int sucursalId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Empresa_ID", nullable = false)
    private Empresa empresa;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Direccion", length = 200, nullable = false)
    private String direccion;

    @Column(name = "Horario_Atencion", length = 100)
    private String horarioAtencion;

    @Column(name = "Estado_Sucursal", length = 50, nullable = false)
    private String estadoSucursal;

    @Transient
    private String nombreEmpresa;

    public Sucursal() {}

    public Sucursal(int sucursalId, int empresaId, String nombre, String direccion, String horarioAtencion, String estadoSucursal) {
        this.sucursalId = sucursalId;
        this.setEmpresaId(empresaId);
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarioAtencion = horarioAtencion;
        this.estadoSucursal = estadoSucursal;
    }

    public int getSucursalId() { return sucursalId; }
    public void setSucursalId(int sucursalId) { this.sucursalId = sucursalId; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    // Métodos puente para compatibilidad con tus JSP y Servlet actuales
    public int getEmpresaId() {
        return empresa != null ? empresa.getEmpresaId() : 0;
    }

    public void setEmpresaId(int empresaId) {
        if (this.empresa == null) {
            this.empresa = new Empresa();
        }
        this.empresa.setEmpresaId(empresaId);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getHorarioAtencion() { return horarioAtencion; }
    public void setHorarioAtencion(String horarioAtencion) { this.horarioAtencion = horarioAtencion; }
    public String getEstadoSucursal() { return estadoSucursal; }
    public void setEstadoSucursal(String estadoSucursal) { this.estadoSucursal = estadoSucursal; }

    public String getNombreEmpresa() {
        if (nombreEmpresa != null) return nombreEmpresa;
        return empresa != null ? empresa.getRazonSocial() : "";
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}