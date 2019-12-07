package com.example.eval2_2411.dto;

import java.io.Serializable;

public class Equipo implements Serializable {
    private int id;
    private String marca;
    private String descripcion;

    public Equipo() {
    }

    public Equipo(int id, String marca, String descripcion) {
        this.id = id;
        this.marca = marca;
        this.descripcion = descripcion;
    }

    public Equipo(String marca, String descripcion) {
        this.marca = marca;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Id = " + id + " | Marca= " + marca + " | Descripción= "+ descripcion;
    }
}
