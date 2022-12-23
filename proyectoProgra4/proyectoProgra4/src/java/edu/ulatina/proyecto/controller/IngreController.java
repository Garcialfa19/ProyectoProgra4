/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.controller;

import edu.ulatina.proyecto.model.IngreTO;
import edu.ulatina.proyecto.service.ServicioING;
import edu.ulatina.proyecto.service.ServicioReceta;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "ingreController")
@SessionScoped
public class IngreController implements Serializable{
    private int id;
    private String nombre;
    private String cantidad;
    private IngreTO ingreTO = new IngreTO();
    private ServicioReceta sr = new ServicioReceta();
    public List<Integer> listaIngredientes = new ArrayList<Integer>(); //lista de ingredientes de la receta

    public IngreController() {
    }

    public IngreController(int id, String nombre, String cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public void index() {
        this.redireccionar("/");
    }


    public void agregarIngreTO() {
        ServicioING servicioING = new ServicioING(this.id, this.nombre, this.cantidad);
        this.ingreTO = servicioING.agregarIngre(this.id, this.nombre,this.cantidad);
        listaIngredientes.add(this.id);
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrediente agregado", "El ingrediente fue agregado correctamente"));
        this.nombre="";
        this.cantidad="";
    }


    //maneja el request, la ruta es la pagina donde queremos ir
    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {
        }
    }

    public ServicioReceta getSr() {
        return sr;
    }

    public void setSr(ServicioReceta sr) {
        this.sr = sr;
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

    public IngreTO getIngreTO() {
        return ingreTO;
    }

    public void setIngreTO(IngreTO ingreTO) {
        this.ingreTO = ingreTO;
    }

    public List<Integer> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<Integer> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }


}
