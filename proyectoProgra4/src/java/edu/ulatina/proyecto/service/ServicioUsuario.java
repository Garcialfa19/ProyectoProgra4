/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.service;

import edu.ulatina.proyecto.model.UsuarioTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author davidgarcia
 */
@ManagedBean(name = "servicioUsuario")
@SessionScoped

public class ServicioUsuario extends Servicio {

    UsuarioTO usuarioTO = new UsuarioTO();

    
    public ServicioUsuario() {
    }

    public UsuarioTO validar(String correo, String clave) {
        UsuarioTO usuarioTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "SELECT idUS, nombreUS, correoUS, contrasenaUS, edadUS, apellidoUS, tipo FROM usuarios WHERE correoUS=? AND contrasenaUS =?  ";
            ps = conexion.prepareStatement(sql);

            //le paso la verificacion de usuario al statement
            ps.setString(1, correo);
            ps.setString(2, clave);

            //ejecuto el statement
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idUS");
                String nombre = rs.getString("nombreUS");
                String correoS = rs.getString("correoUS");
                String contrasenaS = rs.getString("contrasenaUS");
                int edad = rs.getInt("edadUS");
                String apellido = rs.getString("apellidoUS");
                int tipo = rs.getInt("tipo");

                usuarioTO = new UsuarioTO(id, nombre, correoS, contrasenaS, edad, apellido, tipo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
        return usuarioTO;
    }



    public UsuarioTO editarUsuario(int idusuario, String nombre, String correo, String contrasena, int edad, String apellido, int tipo){
        UsuarioTO usuarioTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "UPDATE proyectopro4.usuarios SET idUS = ?, nombreUS = ?, apellidoUS = ?, edadUS = ?, correoUS = ?, contrasenaUS = ?, tipo =? WHERE idUS = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1,idusuario);
            ps.setString(2,nombre);
            ps.setString(3,apellido);
            ps.setInt(4,edad);
            ps.setString(5,correo);
            ps.setString(6,contrasena);
            ps.setInt(7,tipo);
            ps.setInt(8,idusuario);

            ps.executeUpdate();
            usuarioTO = new UsuarioTO(idusuario, nombre, correo, contrasena, edad, apellido, tipo);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        cerrarResultSet(rs);
        cerrarStatement(ps);
        desconectar();
        }

        return usuarioTO;
    }

    public UsuarioTO agregarUsuario(int idusuario, String nombre, String correo, String contrasena, int edad, String apellido, int tipo) {
        UsuarioTO usuarioTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "INSERT INTO proyectopro4.usuarios (idUS, nombreUS, correoUS, contrasenaUS, edadUS, apellidoUS, tipo) VALUES (?, ?, ?, ?, ?, ?, ?);";

            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setString(2, nombre);
            ps.setString(3, correo);
            ps.setString(4, contrasena);
            ps.setInt(5, edad);
            ps.setString(6, apellido);
            ps.setInt(7, 2);

            ps.executeUpdate();

            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();

            usuarioTO = new UsuarioTO(idusuario, nombre, correo, contrasena, edad, apellido, tipo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
        return usuarioTO;

    }

    public List<UsuarioTO> listaUsuariosDB() {

        List<UsuarioTO> listaRetorno = new ArrayList<UsuarioTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conectar();
            //Paso 3
            String sql = "SELECT idUS, nombreUS, correoUS, contrasenaUS, edadUS, apellidoUS, tipo FROM usuarios";
            pstmt = conexion.prepareStatement(sql);
            //Paso 4
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idUS");
                String nombre = rs.getString("nombreUS");
                String apellido = rs.getString("apellidoUS");
                String correoRs = rs.getString("correoUS");
                String contrasena = rs.getString("contrasenaUS");
                int edad = rs.getInt("edadUS");
                int tipo = rs.getInt("tipo");
                UsuarioTO usuarioTO = new UsuarioTO(id, nombre, correoRs, contrasena, edad, apellido, tipo);
                listaRetorno.add(usuarioTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(pstmt);
            desconectar();
        }
        return listaRetorno;
    }

    public UsuarioTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }
}


