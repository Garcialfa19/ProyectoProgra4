package edu.ulatina.proyecto.model;

import edu.ulatina.proyecto.controller.LoginController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidgarcia
 */
public class RecetaTO implements Serializable {

    private int id;
    private String nombre;
    private String ingredientes;
    private String descripcion;
    private String pasos;
    private String categoria;
    private String imagen;
    private String dificultad;
    private int puntuacion;
    private int estado;
    public List<IngreTO> listaIngredientes = new ArrayList<IngreTO>();
    private List<PasosTO> listaPasos = new ArrayList<PasosTO>();

    LoginController lc = new LoginController();
    public int idUS = lc.getIdusuario();

    public RecetaTO() {
    }

    //constructor para TODA la receta
    /*public RecetaTO(int id, String nombre, String ingredientes, String descripcion,String pasos, String categoria, String imagen, String dificultad, int puntuacion){
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.descripcion = descripcion;
        this.pasos = pasos;
        this.categoria = categoria;
        this.imagen = imagen;
        this.dificultad = dificultad;
        this.puntuacion = puntuacion;
    }*/
    //constructor para la tabla SQL
    public RecetaTO(int id, String nombre, String categoria, String imagen, String dificultad, String descripcion, int puntuacion, int idUS) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.imagen = imagen;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.idUS = idUS;
    }

    public RecetaTO(int id, String nombre, String categoria, String imagen, String dificultad, String descripcion, int puntuacion, int idUS, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.imagen = imagen;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.idUS = idUS;
        this.estado = estado;
    }
    

    /*  @Override
    public RecetaTO clone(){
        return new RecetaTO(getId(), getNombre(), getIngredientes(), getDescripcion(), getPasos(), getCategoria(), getImagen(), getDificultad(), getPuntuacion());
    }*/
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

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public int getIdUS() {
        return idUS;
    }

    public void setIdUS(int idUS) {
        this.idUS = idUS;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<IngreTO> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<IngreTO> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public List<PasosTO> getListaPasos() {
        return listaPasos;
    }

    public void setListaPasos(List<PasosTO> listaPasos) {
        this.listaPasos = listaPasos;
    }
    
    
    
}
