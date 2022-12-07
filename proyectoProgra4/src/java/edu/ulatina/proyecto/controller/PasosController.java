package edu.ulatina.proyecto.controller;

import edu.ulatina.proyecto.model.PasosTO;
import edu.ulatina.proyecto.service.ServicioPasos;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author davidgarcia
 */
@ManagedBean(name = "pasosController")
@SessionScoped
public class PasosController {
    private String paso;
    private int idPA;
    private PasosTO pasosTO = new PasosTO();

    public PasosController() {

    }

    public PasosController(String paso, int idPA) {
        this.paso = paso;
        this.idPA = idPA;
    }

    public void agregarPasosTO() {
        ServicioPasos servicioPasos = new ServicioPasos(this.paso, this.idPA);
        this.pasosTO = servicioPasos.agregarPaso(this.paso, this.idPA);

        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrediente agregado", "El ingrediente fue agregado correctamente"));
    }
}
