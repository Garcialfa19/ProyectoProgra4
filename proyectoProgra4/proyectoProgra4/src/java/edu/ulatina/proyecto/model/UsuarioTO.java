/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.model;

import java.io.Serializable;

/**
 * @author usuario
 */
public class UsuarioTO implements Serializable {
    private int idusuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private int edad;
    private String apellido;
    private int tipo;


    public UsuarioTO() {
    }

    public UsuarioTO(int idusuario, String nombre, String correo, String contrasena, int edad, String apellido, int tipo) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.edad = edad;
        this.apellido = apellido;
        this.tipo = tipo;
    }
     public UsuarioTO( String nombre, String correo, String contrasena, int edad, String apellido, int tipo) {
        
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.edad = edad;
        this.apellido = apellido;
        this.tipo = tipo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}
