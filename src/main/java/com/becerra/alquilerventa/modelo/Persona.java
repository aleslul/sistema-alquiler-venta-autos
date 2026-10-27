package com.becerra.alquilerventa.modelo;

public class Persona {
    private int personaId;
    private int tipoDocumentoId;
    private String numeroDocumento;
    private String correo;
    private String telefono;
    private String direccion;
    private String estadoUsuario;
    private String passwordHash;
    private String nombreTipoDocumento;

    public Persona() {}

    public Persona(int personaId, int tipoDocumentoId, String numeroDocumento, String correo, String telefono, String direccion, String estadoUsuario, String passwordHash) {
        this.personaId = personaId;
        this.tipoDocumentoId = tipoDocumentoId;
        this.numeroDocumento = numeroDocumento;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estadoUsuario = estadoUsuario;
        this.passwordHash = passwordHash;
    }

    public int getPersonaId() { return personaId; }
    public void setPersonaId(int personaId) { this.personaId = personaId; }
    public int getTipoDocumentoId() { return tipoDocumentoId; }
    public void setTipoDocumentoId(int tipoDocumentoId) { this.tipoDocumentoId = tipoDocumentoId; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEstadoUsuario() { return estadoUsuario; }
    public void setEstadoUsuario(String estadoUsuario) { this.estadoUsuario = estadoUsuario; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getNombreTipoDocumento() { return nombreTipoDocumento; }
    public void setNombreTipoDocumento(String nombreTipoDocumento) { this.nombreTipoDocumento = nombreTipoDocumento; }
}