/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.controller;

import edu.ulatina.proyecto.model.IngreTO;
import edu.ulatina.proyecto.model.PasosTO;
import edu.ulatina.proyecto.model.RecetaTO;
import edu.ulatina.proyecto.service.ServicioING;
import edu.ulatina.proyecto.service.ServicioPasos;
import edu.ulatina.proyecto.service.ServicioRECUS;
import edu.ulatina.proyecto.service.ServicioReceta;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "recetaController")
@SessionScoped
public class RecetaController implements Serializable {

    private int id;
    private String nombreR;
    private String ingredientes;
    private String descripcion;
    private String pasos;
    private String categoria;
    private String imagen;
    private String dificultad;
    private int puntuacion;
    private int estado;

    @ManagedProperty("#{loginController}")
    private LoginController loginController;

    private int idUS;
    private RecetaTO recetaSeleccionada = new RecetaTO();
    private IngreTO INGSeleccionada = new IngreTO();
    private PasosTO pasoSeleccionada = new PasosTO();
    private RecetaTO recetaTO = new RecetaTO();
    private PasosTO pasosTO = new PasosTO();
    private IngreTO ingreTO = new IngreTO();
    public List<RecetaTO> listaRecetas = new ArrayList<RecetaTO>();
    public List<PasosTO> pasosRecetas = new ArrayList<PasosTO>();
    public List<IngreTO> ingRecetas = new ArrayList<IngreTO>();
    private ServicioRECUS srecus = new ServicioRECUS();

    public RecetaController() {
    }

    @PostConstruct
    public void cargarLista() {
        this.listaRecetas = new ServicioReceta().listaRecetas(this.getLoginController().getUsuarioTO().getIdusuario());
        System.out.println("Cargar Lista");
    }

    public void nuevaReceta(FileUploadView fileUploadView) {

        System.out.println("este es el nombre del archivo: " + fileUploadView.getFile().getFileName());
        System.out.println("este es el contenido: " + fileUploadView.getFile().getContent());

        String fileName = fileUploadView.getFile().getFileName();
        imagen = fileName;
        byte[] image = fileUploadView.getFile().getContent();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
        try {
            BufferedImage savedImage = ImageIO.read(inputStream);
            ImageIO.write(savedImage, "jpeg", new File("//Mac/Home/Documents/Progra 4/Git/ProyectoProgra4/proyectoProgra4/web/resources/images/recetas/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ServicioReceta servicioReceta = new ServicioReceta();
        this.recetaTO = servicioReceta.agregarReceta(this.id, this.nombreR, this.categoria, this.imagen, this.dificultad, this.descripcion, this.puntuacion, this.getLoginController().getUsuarioTO().getIdusuario());
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receta agregada", "La receta fue agregada exitosamente!"));
        srecus.agregarRECUS();
        this.id = 0;
        this.nombreR = "";
        this.categoria = "";
        this.imagen = "";
        this.dificultad = "";
        this.descripcion = "";
        this.puntuacion = 0;

    }

    public RecetaTO agregarRecetaTO() {
        recetaTO = new RecetaTO(this.id, this.nombreR, this.categoria, this.imagen, this.dificultad, this.descripcion, this.puntuacion, idUS);
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receta actualizada", "La receta fue actualizada exitosamente!"));
        return recetaTO;
    }

    public void ocultarReceta(int idS) {
        ServicioReceta servicioReceta = new ServicioReceta();
        servicioReceta.ocultarReceta(idS);
        System.out.println(idS);
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receta actualizada", "La receta fue actualizada exitosamente!"));
        this.cargarLista();
    }

    public void vistaReceta() {
        ServicioReceta servicioReceta = new ServicioReceta();
        servicioReceta.listaRecetas(1);
    }

    public void editarRecetaTO() {
        
        ServicioReceta servicioReceta = new ServicioReceta();
        ServicioPasos servicioPasos = new ServicioPasos();
        ServicioING servicioING = new ServicioING();
        
        this.recetaTO = servicioReceta.actualizarReceta(this.recetaSeleccionada.getId(), this.recetaSeleccionada.getNombre(), this.recetaSeleccionada.getCategoria(), this.recetaSeleccionada.getImagen(), this.recetaSeleccionada.getDificultad(), this.recetaSeleccionada.getDescripcion(), this.recetaSeleccionada.getPuntuacion(), this.getLoginController().getUsuarioTO().getIdusuario());
        this.ingreTO=servicioING.actualizarIngrediente(this.ingreTO.getId(), this.ingreTO.getNombre(), this.ingreTO.getCantidad());
        this.pasosTO=servicioPasos.actualizarPaso(this.pasosTO.getIdPA(), this.pasosTO.getPaso());
        this.cargarLista();
        System.out.println("Identificador: " + servicioING.listaIngredientes(recetaSeleccionada.getId()));
        System.out.println("paso: " + this.pasosTO.getPaso());
        
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receta actualizada", "La receta fue actualizada exitosamente!"));
    }

    public void editarRecetaAdmin (){
       
       ServicioReceta servicioReceta = new ServicioReceta();
        ServicioPasos servicioPasos = new ServicioPasos();
        ServicioING servicioING = new ServicioING();
        
        this.recetaTO = servicioReceta.actualizarReceta(this.recetaSeleccionada.getId(), this.recetaSeleccionada.getNombre(), this.recetaSeleccionada.getCategoria(), this.recetaSeleccionada.getImagen(), this.recetaSeleccionada.getDificultad(), this.recetaSeleccionada.getDescripcion(), this.recetaSeleccionada.getPuntuacion(), this.getLoginController().getUsuarioTO().getIdusuario());
        this.ingreTO=servicioING.actualizarIngrediente(this.INGSeleccionada.getId(),this.INGSeleccionada.getNombre(),this.INGSeleccionada.getCantidad());
        //this.pasosTO=servicioPasos.actualizarPaso(servicioPasos.listaPasos(), servicioPasos.listaPasos(id));
        this.cargarLista();
        System.out.println("Identificador: " + this.pasoSeleccionada.getIdPA());
        System.out.println("paso: " + this.pasoSeleccionada.getPaso());
        
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receta actualizada", "La receta fue actualizada exitosamente!"));
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

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public RecetaTO getRecetaSeleccionada() {
        return recetaSeleccionada;
    }

    public void setRecetaSeleccionada(RecetaTO recetaSeleccionada) {
        this.recetaSeleccionada = recetaSeleccionada;
    }

    public int getId() {
        return id;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreR() {
        return nombreR;
    }

    public void setNombreR(String nombreR) {
        this.nombreR = nombreR;
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

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public RecetaTO getRecetaTO() {
        return recetaTO;
    }

    public void setRecetaTO(RecetaTO recetaTO) {
        this.recetaTO = recetaTO;
    }

    public List<RecetaTO> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<RecetaTO> listaRecetas) {
        this.listaRecetas = listaRecetas;
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


}
