package edu.ulatina.proyecto.service;

import edu.ulatina.proyecto.model.IngreTO;
import edu.ulatina.proyecto.model.PasosTO;
import edu.ulatina.proyecto.model.RecetaTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
@ManagedBean(name = "servicioReceta")
@ViewScoped
public class ServicioReceta extends Servicio implements Serializable {

    RecetaTO recetaTO = new RecetaTO();
    List<IngreTO> listaIngre = new ArrayList<IngreTO>();
    List<PasosTO> listaPasos = new ArrayList<PasosTO>();

    public ServicioReceta() {
    }

    public RecetaTO agregarReceta(int id, String nombre, String categoria, String imagen, String dificultad, String descripcion, int puntuacion, int idUS) {
        RecetaTO recetaTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "INSERT INTO proyectopro4.recetas ( nombreREC, categoriaREC, imagenREC, dificultadREC, descripcionREC, puntuacionREC, idUS_REC, estadoREC) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            //ps.setInt(1, id);
            ps.setString(1, nombre);
            ps.setString(2, categoria);
            ps.setString(3, imagen);
            ps.setString(4, dificultad);
            ps.setString(5, descripcion);
            ps.setInt(6, puntuacion);
            ps.setInt(7, idUS);
            ps.setInt(8, 1);
       
            ps.executeUpdate();
            
            recetaTO = new RecetaTO(id, nombre, categoria, imagen, dificultad, descripcion, puntuacion, idUS);

            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
        return recetaTO;

    }

    public List<RecetaTO> listaRecetasTodos() {
        List<RecetaTO> listaRetornoTodos = new ArrayList<RecetaTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "SELECT idREC, nombreREC, categoriaREC, imagenREC, dificultadREC, descripcionREC, puntuacionREC, idUS_REC FROM recetas WHERE estadoREC=1";
            pstmt = conexion.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idREC");
                String nombre = rs.getString("nombreREC");
                String categoria = rs.getString("categoriaREC");
                String imagen = rs.getString("imagenREC");
                String dificultad = rs.getString("dificultadREC");
                String descripcion = rs.getString("descripcionREC");
                int puntuacion = rs.getInt("puntuacionREC");
                int idUS = rs.getInt("idUS_REC");
                RecetaTO recetaTO = new RecetaTO(id, nombre, categoria, imagen, dificultad, descripcion, puntuacion, idUS);
                listaRetornoTodos.add(recetaTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(pstmt);
            desconectar();
        }
        return listaRetornoTodos;
    }

    public List<RecetaTO> listaRecetas(int idUS_REC) {
        List<RecetaTO> listaRetorno = new ArrayList<RecetaTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "SELECT idREC, nombreREC, categoriaREC, imagenREC, dificultadREC, descripcionREC, puntuacionREC, idUS_REC FROM recetas WHERE idUS_REC=? and estadoREC=1";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, idUS_REC);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idREC");
                String nombre = rs.getString("nombreREC");
                String categoria = rs.getString("categoriaREC");
                String imagen = rs.getString("imagenREC");
                String dificultad = rs.getString("dificultadREC");
                String descripcion = rs.getString("descripcionREC");
                int puntuacion = rs.getInt("puntuacionREC");
                int idUS = rs.getInt("idUS_REC");
                RecetaTO recetaTO = new RecetaTO(id, nombre, categoria, imagen, dificultad, descripcion, puntuacion, idUS);
                listaRetorno.add(recetaTO);
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
    
    public List<RecetaTO> listaIng(int idREC) {
        List<RecetaTO> listaRetorno = new ArrayList<RecetaTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "SELECT idREC, nombreREC, categoriaREC, imagenREC, dificultadREC, descripcionREC, puntuacionREC, idUS_REC FROM recetas WHERE idUS_REC=? and estadoREC=1";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, idREC);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idREC");
                String nombre = rs.getString("nombreREC");
                String categoria = rs.getString("categoriaREC");
                String imagen = rs.getString("imagenREC");
                String dificultad = rs.getString("dificultadREC");
                String descripcion = rs.getString("descripcionREC");
                int puntuacion = rs.getInt("puntuacionREC");
                int idUS = rs.getInt("idUS_REC");
                RecetaTO recetaTO = new RecetaTO(id, nombre, categoria, imagen, dificultad, descripcion, puntuacion, idUS);
                listaRetorno.add(recetaTO);
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

    public List<RecetaTO> listaRecetasAdmin() {
        List<RecetaTO> listaRetorno = new ArrayList<RecetaTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "SELECT idREC, nombreREC, categoriaREC, imagenREC, dificultadREC, descripcionREC, puntuacionREC, idUS_REC FROM recetas";
            pstmt = conexion.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idREC");
                String nombre = rs.getString("nombreREC");
                String categoria = rs.getString("categoriaREC");
                String imagen = rs.getString("imagenREC");
                String dificultad = rs.getString("dificultadREC");
                String descripcion = rs.getString("descripcionREC");
                int puntuacion = rs.getInt("puntuacionREC");
                int idUS = rs.getInt("idUS_REC");
                RecetaTO recetaTO = new RecetaTO(id, nombre, categoria, imagen, dificultad, descripcion, puntuacion, idUS);
                listaRetorno.add(recetaTO);
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

    public RecetaTO actualizarReceta(int id, String nombre, String categoria, String imagen, String dificultad, String descripcion, int puntuacion, int idUS) {
        RecetaTO recetaTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "UPDATE proyectopro4.recetas SET nombreREC = ?, categoriaREC = ?, imagenREC = ?, dificultadREC = ?, descripcionREC = ?, puntuacionREC = ?, idUS_REC = ? WHERE idREC = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(8, id);
            ps.setString(1, nombre);
            ps.setString(2, categoria);
            ps.setString(3, imagen);
            ps.setString(4, dificultad);
            ps.setString(5, descripcion);
            ps.setInt(6, puntuacion);
            ps.setInt(7, idUS);

            ps.executeUpdate();
            recetaTO = new RecetaTO(id, nombre, categoria, imagen, dificultad, descripcion, puntuacion, idUS);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
        return recetaTO;

    }


    public RecetaTO actualizarRecetaAdmin(int id, String nombre, String categoria, String imagen, String dificultad, String descripcion, int puntuacion, int idUS, int estado) {
        RecetaTO recetaTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "UPDATE proyectopro4.recetas SET nombreREC = ?, categoriaREC = ?, imagenREC = ?, dificultadREC = ?, descripcionREC = ?, puntuacionREC = ?, idUS_REC = ?, estadoREC = ? WHERE idREC = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(9, id);
            ps.setString(1, nombre);
            ps.setString(2, categoria);
            ps.setString(3, imagen);
            ps.setString(4, dificultad);
            ps.setString(5, descripcion);
            ps.setInt(6, puntuacion);
            ps.setInt(7, idUS);
            ps.setInt(8,estado);

            ps.executeUpdate();
            recetaTO = new RecetaTO(id, nombre, categoria, imagen, dificultad, descripcion, puntuacion, idUS, estado);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
        return recetaTO;

    }




    public void ocultarReceta(int idREC) {
        RecetaTO recetaTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "UPDATE proyectopro4.recetas SET estadoREC=0 WHERE idREC = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idREC);
            ps.executeUpdate();
            System.out.println("hola");
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
    }

    public void ingresarIngreALista(int idING) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conectar();
            String sql = "SELECT idING, nombreING, cantidadING FROM ingredientes WHERE idING=?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idING);

            rs = ps.executeQuery();

            int id = rs.getInt("idING");
            String nombre = rs.getString("nombreING");
            String cantidad = rs.getString("cantidadING");
            IngreTO ingreTO = new IngreTO(id, nombre, cantidad);
            listaIngre.add(ingreTO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }

    }
    
     public void ingresarPasoALista(int idPaso) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conectar();
            String sql = "SELECT idPA, pasos FROM pasos WHERE idPA=?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idPaso);

            rs = ps.executeQuery();

            int id = rs.getInt("idPA");
            String paso = rs.getString("pasos");
            PasosTO pasoTO = new PasosTO(id, paso);
            listaPasos.add(pasoTO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }

    }

    public RecetaTO getRecetaTO() {
        return recetaTO;
    }

    public void setRecetaTO(RecetaTO recetaTO) {
        this.recetaTO = recetaTO;
    }

    public List<IngreTO> getListaIngre() {
        return listaIngre;
    }

    public void setListaIngredientes(List<IngreTO> listaIngre) {
        this.listaIngre = listaIngre;
    }

    public List<PasosTO> getListaPasos() {
        return listaPasos;
    }

    public void setListaPasos(List<PasosTO> listaPasos) {
        this.listaPasos = listaPasos;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    

}
