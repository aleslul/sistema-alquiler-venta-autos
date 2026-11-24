package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String NINGUNO = "NINGUNO";
    public static final String CLIENTE_NATURAL = "CLIENTE_NATURAL";
    public static final String CLIENTE_JURIDICO = "CLIENTE_JURIDICO";
    public static final String TRABAJADOR = "TRABAJADOR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Persona_ID")
    private int personaId;

    @Column(name = "Tipo_Documento_ID", nullable = false)
    private int tipoDocumentoId;

    @Column(name = "Numero_Documento", length = 50, nullable = false, unique = true)
    private String numeroDocumento;

    @Column(name = "Correo", length = 100, nullable = false, unique = true)
    private String correo;

    @Column(name = "Telefono", length = 20)
    private String telefono;

    @Column(name = "Direccion", length = 200)
    private String direccion;

    @Column(name = "Estado_Usuario", length = 50, nullable = false)
    private String estadoUsuario;

    @Column(name = "Password_Hash", length = 255)
    private String passwordHash;

    // Campos auxiliares para la vista (no son columnas de la tabla Persona)
    @Transient
    private String nombreTipoDocumento;
    @Transient
    private String tipoRegistro;
    @Transient
    private String nombreCompleto;

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

    public String getCodigoTipo() { return NINGUNO; }

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
    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
}