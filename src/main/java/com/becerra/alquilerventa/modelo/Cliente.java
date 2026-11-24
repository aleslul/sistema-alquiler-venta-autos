package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cliente")
@PrimaryKeyJoinColumn(name = "Cliente_ID")
public class Cliente extends Persona {
    @Column(name = "Tipo_Cliente", length = 20, nullable = false)
    private String tipoCliente;

    @Column(name = "Fecha_Registro", nullable = false)
    private LocalDateTime fechaRegistro;

    public Cliente() {}

    public int getClienteId() { return getPersonaId(); }
    public void setClienteId(int clienteId) { setPersonaId(clienteId); }
    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}