package com.becerra.alquilerventa.modelo;

public class SucursalAccesorio {
    private int sucursalId;
    private int accesorioId;
    private int stockTotal;
    private int stockDisponible;

    public SucursalAccesorio() {}

    public SucursalAccesorio(int sucursalId, int accesorioId, int stockTotal, int stockDisponible) {
        this.sucursalId = sucursalId;
        this.accesorioId = accesorioId;
        this.stockTotal = stockTotal;
        this.stockDisponible = stockDisponible;
    }

    public int getSucursalId() { return sucursalId; }
    public void setSucursalId(int sucursalId) { this.sucursalId = sucursalId; }
    public int getAccesorioId() { return accesorioId; }
    public void setAccesorioId(int accesorioId) { this.accesorioId = accesorioId; }
    public int getStockTotal() { return stockTotal; }
    public void setStockTotal(int stockTotal) { this.stockTotal = stockTotal; }
    public int getStockDisponible() { return stockDisponible; }
    public void setStockDisponible(int stockDisponible) { this.stockDisponible = stockDisponible; }
}