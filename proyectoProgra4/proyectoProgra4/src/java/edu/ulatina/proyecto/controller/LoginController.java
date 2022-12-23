/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.controller;

import edu.ulatina.proyecto.model.UsuarioTO;
import edu.ulatina.proyecto.service.ServicioUsuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author usuario
 */
//anotaciones
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private int idusuario;
    private String nombre;
    private String correo;
    private int edad;
    private String apellido;
    private int tipo;
    private String contrasena;
    private UsuarioTO usuarioTO = new UsuarioTO();
    private List<UsuarioTO> listaUsuarios = new ArrayList<UsuarioTO>();
   

    public LoginController() {
    }

    public LoginController(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public void ingresar() {
        System.out.println("Username: " + this.correo);
        System.out.println("Password: " + this.contrasena);
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        this.usuarioTO = servicioUsuario.validar(this.correo, this.contrasena);
        if (this.usuarioTO != null) {
            this.listaUsuarios = servicioUsuario.listaUsuariosDB();
            this.redireccionar("/faces/bienvenido.xhtml");
            
        } else {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos inválidos", "La clave o correo no son correctos"));
        }
        this.correo="";

    }

    public void index() {
            this.redireccionar("/");
    }


    public void agregarUsuarioTO() {
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        this.usuarioTO = servicioUsuario.agregarUsuario( this.idusuario,this.nombre, this.correo, this.contrasena, this.edad, this.apellido, this.tipo);
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "UsuarioAgregado", "El usuario fue agregado correctamente"));
        this.nombre="";
        this.correo="";
        this.contrasena="";
        this.edad=0;
        this.apellido="";
        this.tipo=0;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
