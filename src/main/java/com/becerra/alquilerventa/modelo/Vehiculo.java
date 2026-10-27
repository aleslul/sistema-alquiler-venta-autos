package com.becerra.alquilerventa.modelo;

public class Vehiculo {
    private int vehiculoId;
    private int categoriaId;
    private int marcaId;
    private int estadoVehiculoId;
    private int tipoTransmisionId;
    private int tipoCombustibleId;
    private int sucursalActualId;
    private String placa;
    private String modelo;
    private int anio;
    private int numPasajeros;
    private int numPuertas;
    private boolean aireAcondicionado;
    private String modalidad; // 'Alquiler' o 'Venta'
    private String nombreCategoria;
    private String nombreMarca;
    private String nombreEstado;
    private String nombreTransmision;
    private String nombreCombustible;
    private String nombreSucursal;

    public Vehiculo() {}

    public int getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(int vehiculoId) { this.vehiculoId = vehiculoId; }
    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }
    public int getMarcaId() { return marcaId; }
    public void setMarcaId(int marcaId) { this.marcaId = marcaId; }
    public int getEstadoVehiculoId() { return estadoVehiculoId; }
    public void setEstadoVehiculoId(int estadoVehiculoId) { this.estadoVehiculoId = estadoVehiculoId; }
    public int getTipoTransmisionId() { return tipoTransmisionId; }
    public void setTipoTransmisionId(int tipoTransmisionId) { this.tipoTransmisionId = tipoTransmisionId; }
    public int getTipoCombustibleId() { return tipoCombustibleId; }
    public void setTipoCombustibleId(int tipoCombustibleId) { this.tipoCombustibleId = tipoCombustibleId; }
    public int getSucursalActualId() { return sucursalActualId; }
    public void setSucursalActualId(int sucursalActualId) { this.sucursalActualId = sucursalActualId; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public int getNumPasajeros() { return numPasajeros; }
    public void setNumPasajeros(int numPasajeros) { this.numPasajeros = numPasajeros; }
    public int getNumPuertas() { return numPuertas; }
    public void setNumPuertas(int numPuertas) { this.numPuertas = numPuertas; }
    public boolean isAireAcondicionado() { return aireAcondicionado; }
    public void setAireAcondicionado(boolean aireAcondicionado) { this.aireAcondicionado = aireAcondicionado; }
    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }
    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
    public String getNombreMarca() { return nombreMarca; }
    public void setNombreMarca(String nombreMarca) { this.nombreMarca = nombreMarca; }
    public String getNombreEstado() { return nombreEstado; }
    public void setNombreEstado(String nombreEstado) { this.nombreEstado = nombreEstado; }
    public String getNombreTransmision() { return nombreTransmision; }
    public void setNombreTransmision(String nombreTransmision) { this.nombreTransmision = nombreTransmision; }
    public String getNombreCombustible() { return nombreCombustible; }
    public void setNombreCombustible(String nombreCombustible) { this.nombreCombustible = nombreCombustible; }
    public String getNombreSucursal() { return nombreSucursal; }
    public void setNombreSucursal(String nombreSucursal) { this.nombreSucursal = nombreSucursal; }
}