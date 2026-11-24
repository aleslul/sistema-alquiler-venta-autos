package com.becerra.alquilerventa.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Rol_Trabajador")
public class RolTrabajador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Rol_Trabajador_ID")
    private int rolTrabajadorId;

    @Column(name = "Nombre", length = 50, nullable = false, unique = true)
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