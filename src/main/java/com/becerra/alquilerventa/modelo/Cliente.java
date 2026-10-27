package com.becerra.alquilerventa.modelo;

import java.time.LocalDateTime;

public class Cliente extends Persona {
    private String tipoCliente; // 'Natural' o 'Juridico'
    private LocalDateTime fechaRegistro;

    public Cliente() {}

    public int getClienteId() { return getPersonaId(); }
    public void setClienteId(int clienteId) { setPersonaId(clienteId); }
    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}