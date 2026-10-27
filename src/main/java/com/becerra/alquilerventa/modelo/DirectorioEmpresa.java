package com.becerra.alquilerventa.modelo;

public class DirectorioEmpresa {
    private int miembroId;
    private int empresaId;
    private String nombresApellidos;
    private String cargo;
    private String fotoUrl;
    private String correoCorporativo;
    private int ordenJerarquico = 1;

    public DirectorioEmpresa() {}

    public DirectorioEmpresa(int miembroId, int empresaId, String nombresApellidos, String cargo, String fotoUrl, String correoCorporativo, int ordenJerarquico) {
        this.miembroId = miembroId;
        this.empresaId = empresaId;
        this.nombresApellidos = nombresApellidos;
        this.cargo = cargo;
        this.fotoUrl = fotoUrl;
        this.correoCorporativo = correoCorporativo;
        this.ordenJerarquico = ordenJerarquico;
    }

    public int getMiembroId() { return miembroId; }
    public void setMiembroId(int miembroId) { this.miembroId = miembroId; }
    public int getEmpresaId() { return empresaId; }
    public void setEmpresaId(int empresaId) { this.empresaId = empresaId; }
    public String getNombresApellidos() { return nombresApellidos; }
    public void setNombresApellidos(String nombresApellidos) { this.nombresApellidos = nombresApellidos; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
    public String getCorreoCorporativo() { return correoCorporativo; }
    public void setCorreoCorporativo(String correoCorporativo) { this.correoCorporativo = correoCorporativo; }
    public int getOrdenJerarquico() { return ordenJerarquico; }
    public void setOrdenJerarquico(int ordenJerarquico) { this.ordenJerarquico = ordenJerarquico; }
}