package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Cliente_Natural")
@PrimaryKeyJoinColumn(name = "Cliente_ID")
public class ClienteNatural extends Cliente {
    @Column(name = "Nombres", length = 100, nullable = false)
    private String nombres;

    @Column(name = "Apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "Fecha_Nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "Licencia_Conducir", length = 50)
    private String licenciaConducir;

    @Column(name = "Fecha_Vencimiento_Licencia")
    private LocalDate fechaVencimientoLicencia;

    public ClienteNatural() { setTipoCliente("Natural"); }

    @Override
    public String getCodigoTipo() { return CLIENTE_NATURAL; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getLicenciaConducir() { return licenciaConducir; }
    public void setLicenciaConducir(String licenciaConducir) { this.licenciaConducir = licenciaConducir; }
    public LocalDate getFechaVencimientoLicencia() { return fechaVencimientoLicencia; }
    public void setFechaVencimientoLicencia(LocalDate fechaVencimientoLicencia) { this.fechaVencimientoLicencia = fechaVencimientoLicencia; }
}