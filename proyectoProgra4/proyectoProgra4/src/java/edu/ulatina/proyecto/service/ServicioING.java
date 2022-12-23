/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.service;

import edu.ulatina.proyecto.model.IngreTO;
import org.primefaces.model.DualListModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "servicioING")
@ViewScoped
public class ServicioING extends Servicio implements Serializable {
    IngreTO ingTO = new IngreTO();

    public ServicioING() {
    }


    public ServicioING(int id, String nombre, String cantidad) {
    }

    public IngreTO agregarIngre(int id, String nombre, String cantidad) {
        IngreTO ingTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "INSERT INTO proyectopro4.ingredientes ( nombreING, cantidadING) VALUES ( ?, ?);";
            ps = conexion.prepareStatement(sql);
            //ps.setInt(1, id);
            ps.setString(1, nombre);
            ps.setString(2,cantidad);

            ps.executeUpdate();
            ingTO = new IngreTO(id, nombre, cantidad);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
        return ingTO;
    }
    
    
    public IngreTO actualizarIngrediente(int id, String nombre, String cantidad){
        IngreTO ingTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "UPDATE proyectopro4.ingredientes SET nombreING = ?, cantidadING = ? WHERE idING = ?;";
            ps = conexion.prepareStatement(sql);
            //ps.setInt(1, id);
            ps.setString(1, nombre);
            ps.setString(2,cantidad);
            ps.setInt(3,id);

            ps.executeUpdate();
            ingTO = new IngreTO(id, nombre, cantidad);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
        return ingTO;
    }
    





    public List<IngreTO> listaIngredientes(int id) {
        List<IngreTO> listaRetorno = new ArrayList<IngreTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "SELECT idING, nombreING, cantidadING FROM ingredientes where idING IN (SELECT ING_idING FROM rec_US WHERE (REC_idREC =?))";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int idING = rs.getInt("idING");
                String nombre = rs.getString("nombreING");
                String cantidad = rs.getString("cantidadING");
                System.out.println(nombre);
                IngreTO ingreTO = new IngreTO(idING, nombre, cantidad);
                listaRetorno.add(ingreTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(pstmt);
            desconectar();
        }
        return listaRetorno;
    }


    
    public List<IngreTO> listaSola() {
        List<IngreTO> listaRetorno = new ArrayList<IngreTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
          
         

        try {
            conectar();
            String sql = "SELECT idING, nombreING, cantidadING FROM ingredientes ";
            pstmt = conexion.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idING");
                String nombre = rs.getString("nombreING");
                String cantidad = rs.getString("cantidadING");
                IngreTO ingTO = new IngreTO(id, nombre, cantidad);
                listaRetorno.add(ingTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(pstmt);
            desconectar();
        }
        return listaRetorno;
    }
    
    
    
    
    
    public DualListModel<IngreTO> listaIng() {
        List<IngreTO> listaEntrada = new ArrayList<IngreTO>();
        List<IngreTO> listaSalida = new ArrayList<IngreTO>(); 
        PreparedStatement pstmt = null;
        ResultSet rs = null;
          
         

        try {
            conectar();
            String sql = "SELECT idING, nombreING, cantidadING FROM ingredientes ";
            pstmt = conexion.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idING");
                String nombre = rs.getString("nombreING");
                String cantidad = rs.getString("cantidadING");
                IngreTO ingTO = new IngreTO(id, nombre, cantidad);
                listaEntrada.add(ingTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(pstmt);
            desconectar();
        }
        DualListModel<IngreTO> listaRetorno = new DualListModel<IngreTO>(listaEntrada, listaSalida); 
        return listaRetorno;
    }
    
    public IngreTO getIngTO() {
        return ingTO;
    }

    public void setIngTO(IngreTO ingTO) {
        this.ingTO = ingTO;
    }
}
