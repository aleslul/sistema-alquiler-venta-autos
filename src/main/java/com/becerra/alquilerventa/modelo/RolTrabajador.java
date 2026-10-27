package com.becerra.alquilerventa.modelo;

public class RolTrabajador {
    private int rolTrabajadorId;
    private String nombre;

    public RolTrabajador() {}

    public RolTrabajador(int rolTrabajadorId, String nombre) {
        this.rolTrabajadorId = rolTrabajadorId;
        this.nombre = nombre;
    }

    public int getRolTrabajadorId() { return rolTrabajadorId; }
    public void setRolTrabajadorId(int rolTrabajadorId) { this.rolTrabajadorId = rolTrabajadorId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}