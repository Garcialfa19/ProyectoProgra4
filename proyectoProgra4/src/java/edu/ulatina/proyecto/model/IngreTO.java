/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.model;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class IngreTO implements Serializable {
    private int id;
    private String nombre;
    private String cantidad;

    public IngreTO() {
    }

    public IngreTO(int id, String nombre, String cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public IngreTO clone(){
        return new IngreTO(getId(), getNombre(), getCantidad());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }


}
