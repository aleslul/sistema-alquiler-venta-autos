package com.becerra.alquilerventa.modelo;

import java.math.BigDecimal;

public class Categoria {
    private int categoriaId;
    private int estadoCategoriaId;
    private String nombre;
    private BigDecimal tarifaDiariaBase;
    private int capacidadMaletas;

    public Categoria() {}

    public Categoria(int categoriaId, int estadoCategoriaId, String nombre, BigDecimal tarifaDiariaBase, int capacidadMaletas) {
        this.categoriaId = categoriaId;
        this.estadoCategoriaId = estadoCategoriaId;
        this.nombre = nombre;
        this.tarifaDiariaBase = tarifaDiariaBase;
        this.capacidadMaletas = capacidadMaletas;
    }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }
    public int getEstadoCategoriaId() { return estadoCategoriaId; }
    public void setEstadoCategoriaId(int estadoCategoriaId) { this.estadoCategoriaId = estadoCategoriaId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getTarifaDiariaBase() { return tarifaDiariaBase; }
    public void setTarifaDiariaBase(BigDecimal tarifaDiariaBase) { this.tarifaDiariaBase = tarifaDiariaBase; }
    public int getCapacidadMaletas() { return capacidadMaletas; }
    public void setCapacidadMaletas(int capacidadMaletas) { this.capacidadMaletas = capacidadMaletas; }
}