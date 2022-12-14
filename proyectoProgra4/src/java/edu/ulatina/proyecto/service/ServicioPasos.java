package edu.ulatina.proyecto.service;

import edu.ulatina.proyecto.model.PasosTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author davidgarcia
 */
@ManagedBean(name = "servicioPasos")
@ViewScoped
public class ServicioPasos extends Servicio implements Serializable {
    PasosTO pasosTO = new PasosTO();


    public ServicioPasos(String paso, int idPA) {
    }

    public ServicioPasos() {

    }

    public PasosTO agregarPaso(int idPA,String paso) {
        PasosTO pasosTO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "INSERT INTO proyectopro4.pasos (pasos) VALUES (?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, paso);
            //ps.setInt(, idPA);

            ps.executeUpdate();
            pasosTO = new PasosTO(idPA,paso);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            cerrarResultSet(rs);
            cerrarStatement(ps);
            desconectar();
        }
        return pasosTO;
    }


    public List<PasosTO> listaPasos(int id) {
        List<PasosTO> listaRetorno = new ArrayList<PasosTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "SELECT idPA, pasos FROM proyectopro4.pasos WHERE idPA IN (SELECT PA_idPA FROM proyectopro4.rec_us WHERE REC_idREC=?)";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String paso = rs.getString("pasos");
                int idPA = rs.getInt("idPA");
                System.out.println(paso);
                PasosTO pasosTO = new PasosTO(idPA,paso);
                listaRetorno.add(pasosTO);
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


}
