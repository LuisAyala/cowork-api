package com.codigo.cowork.model;

public class Sala {
    private Long id;
    private String codigo;      // ej: "SALA-A1"
    private String nombre;      // ej: "Sala Andes"
    private Integer capacidad;  // número de personas
    private String ubicacion;   // ej: "Piso 3"
    private boolean activa;     // si está habilitada para reservar

    public Sala() {
    }

    public Sala(Long id, String codigo, String nombre, Integer capacidad, String ubicacion, boolean activa) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.activa = activa;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
