package com.becerra.alquilerventa.modelo;

public class TipoDocumento {
    private int tipoDocumentoId;
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