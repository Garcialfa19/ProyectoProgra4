package edu.ulatina.proyecto.controller;

import edu.ulatina.proyecto.model.IngreTO;
import edu.ulatina.proyecto.model.PasosTO;
import edu.ulatina.proyecto.model.RecetaTO;
import edu.ulatina.proyecto.model.UsuarioTO;
import edu.ulatina.proyecto.service.ServicioUsuario;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "crudView")
@ViewScoped
public class CrudView implements Serializable {

    private UsuarioTO usuarioSeleccionado = new UsuarioTO();
    private RecetaTO recetaSeleccionada=new RecetaTO();
    private IngreTO ingreSeleccionado=new IngreTO();
    private PasosTO pasoSeleccionado=new PasosTO();
    private UsuarioTO usuarioTO = new UsuarioTO();
    private List<UsuarioTO> listaUsuarios = new ArrayList<UsuarioTO>();

    public CrudView() {
    }

    public void openNew() {
        this.usuarioSeleccionado = new UsuarioTO();
    }
    public void openNewReceta() {
        this.recetaSeleccionada = new RecetaTO();
    }
    public void openNewIngre() {
        this.ingreSeleccionado = new IngreTO();
    }
    
    public void openNewPaso() {
        this.pasoSeleccionado = new PasosTO();
    }

    @PostConstruct
    public void cargarLista() {
        this.listaUsuarios = new ServicioUsuario().listaUsuariosDB();
        System.out.println("Cargar Lista");
    }

    public void save() {
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        this.usuarioTO = servicioUsuario.editarUsuario(this.usuarioSeleccionado.getIdusuario(), this.usuarioSeleccionado.getNombre(), this.usuarioSeleccionado.getCorreo(), this.usuarioSeleccionado.getContrasena(), this.usuarioSeleccionado.getEdad(), this.usuarioSeleccionado.getApellido(), this.usuarioSeleccionado.getTipo());
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario actualizado", "El usuario fue actualizado exitosamente!"));
        this.cargarLista();
    }

    public void delete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Eliminado"));
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
    }

    public UsuarioTO getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioTO usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }


    public RecetaTO getRecetaSeleccionada() {
        return recetaSeleccionada;
    }

    public void setRecetaSeleccionada(RecetaTO recetaSeleccionada) {
        this.recetaSeleccionada = recetaSeleccionada;
    }

    public IngreTO getIngreSeleccionado() {
        return ingreSeleccionado;
    }

    public void setIngreSeleccionado(IngreTO ingreSeleccionado) {
        this.ingreSeleccionado = ingreSeleccionado;
    }

    public PasosTO getPasoSeleccionado() {
        return pasoSeleccionado;
    }

    public void setPasoSeleccionado(PasosTO pasoSeleccionado) {
        this.pasoSeleccionado = pasoSeleccionado;
    }

    public UsuarioTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public List<UsuarioTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
}
