package edu.ulatina.proyecto.controller;

import edu.ulatina.proyecto.model.PasosTO;
import edu.ulatina.proyecto.service.ServicioING;
import edu.ulatina.proyecto.service.ServicioPasos;
import edu.ulatina.proyecto.service.ServicioReceta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author davidgarcia
 */
@ManagedBean(name = "pasosController")
@SessionScoped
public class PasosController implements Serializable{
    private String paso;
    private int idPA;
    private ServicioReceta sr = new ServicioReceta();
    private PasosTO pasosTO = new PasosTO();
    private List<PasosTO> listaPasos = new ArrayList<PasosTO>();
    
    public PasosController() {

    }

    public PasosController(int idPA, String paso) {
        this.paso = paso;
        this.idPA = idPA;
    }
    
    public void index() {
        this.redireccionar("/");
    }


    public void agregarPasosTO() {
        ServicioPasos servicioPasos = new ServicioPasos();
        this.pasosTO = servicioPasos.agregarPaso(this.idPA, this.paso);
        sr.ingresarPasoALista(this.idPA);
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Paso agregado", "El paso fue agregado correctamente"));
        this.paso="";
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

    public String getPaso() {
        return paso;
    }

    public void setPaso(String paso) {
        this.paso = paso;
    }

    public int getIdPA() {
        return idPA;
    }

    public void setIdPA(int idPA) {
        this.idPA = idPA;
    }

    public ServicioReceta getSr() {
        return sr;
    }

    public void setSr(ServicioReceta sr) {
        this.sr = sr;
    }

    public PasosTO getPasosTO() {
        return pasosTO;
    }

    public void setPasosTO(PasosTO pasosTO) {
        this.pasosTO = pasosTO;
    }

    public List<PasosTO> getListaPasos() {
        return listaPasos;
    }

    public void setListaPasos(List<PasosTO> listaPasos) {
        this.listaPasos = listaPasos;
    }
    
    
}
