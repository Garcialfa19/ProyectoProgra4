/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.service;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author usuario
 */
public class ServicioRECUS extends Servicio implements Serializable {
    ServicioING sing = new ServicioING();
    ServicioPasos spa = new ServicioPasos();
    ServicioReceta srec = new ServicioReceta();

    public ServicioRECUS() {
    }
    
    public void agregarRECUS() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conectar();
            String sql = "INSERT INTO proyectopro4.rec_us (fechaCreacion, fechaEdicion, REC_idREC, US_idUS, ING_idING, PA_idPA) VALUES ('2022-11-26', '2022-11-26', ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, srec.getRecetaTO().getId());
            ps.setInt(2, srec.getRecetaTO().getIdUS());
            /*ps.setInt(3, srec.getListaIngre().get(1));//como recorro toda la lista de ingredientes de una sola receta? con un for?
            ocupo un metodo que recorra la lista de ingredientes y extraiga unicamente el id del ingrediente, no ocupo un IngreTO
            ocupo hacer el insert mientras haya ingredientes y pasos asociados a la receta, para eso ocupo el tamano de la lista de ingredientes y el tamano de la lista de pasos
            todo esto mientras aun haya recetas asociadas a un solo usuario (todas las recetas del usuario)
            ps.setInt(4, spa.listaPasos());*/
            ps.executeUpdate();

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
    }
}
