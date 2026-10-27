package com.becerra.alquilerventa.modelo;

import java.time.LocalDate;

public class ClienteNatural extends Cliente {
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String licenciaConducir;
    private LocalDate fechaVencimientoLicencia;

    public ClienteNatural() {}

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