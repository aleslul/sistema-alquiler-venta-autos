package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Tipo_Documento")
public class TipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tipo_Documento_ID")
    private int tipoDocumentoId;

    @Column(name = "Nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    public TipoDocumento() {}

    public TipoDocumento(int tipoDocumentoId, String nombre) {
        this.tipoDocumentoId = tipoDocumentoId;
        this.nombre = nombre;
    }

    public int getTipoDocumentoId() { return tipoDocumentoId; }
    public void setTipoDocumentoId(int tipoDocumentoId) { this.tipoDocumentoId = tipoDocumentoId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}