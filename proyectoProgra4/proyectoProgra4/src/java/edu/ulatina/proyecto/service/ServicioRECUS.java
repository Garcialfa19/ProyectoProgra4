/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.proyecto.service;


import edu.ulatina.proyecto.model.IngreTO;
import edu.ulatina.proyecto.model.PasosTO;
import edu.ulatina.proyecto.model.RecetaTO;
import edu.ulatina.proyecto.controller.PasosController;
import edu.ulatina.proyecto.controller.IngreController;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ServicioRECUS extends Servicio implements Serializable {

    PasosController pc = new PasosController();
    IngreController ic = new IngreController();
    ServicioReceta sr = new ServicioReceta();
    

    public ServicioRECUS() {
    }
    

    public void agregarRECUS() {
        PreparedStatement ps = null;


        try {
            conectar();
            //for para que ejecute el insert mientras aun hayan recetas por usuario que agregar.
            for (int x = 0; x < sr.listaRetorno.size() ; x++) {
                //for para que ejecute el insert mientras aun hayan ingredientes que agregar.
                for (int i = 0; i < ic.listaIngredientes.size(); i++) {
                    //for para que ejecute el insert mientras aun hayan pasos que agregar.
                    for (int y = 0; y < pc.listaPasos.size(); y++) {
                        String sql = "INSERT INTO proyectopro4.rec_us (fechaCreacion, fechaEdicion, REC_idREC, US_idUS, ING_idING, PA_idPA) VALUES ('2022-11-26', '2022-11-26', ?, ?, ?, ?)";
                        ps = conexion.prepareStatement(sql);
                        ps.setInt(1, this.ConvertirREC(sr.listaRetorno.get(x)));
                        System.out.println(this.ConvertirREC(sr.listaRetorno.get(x)));
                        ps.setInt(2, this.ConvertirUS(sr.listaRetorno.get(x)));
                        System.out.println(this.ConvertirUS(sr.listaRetorno.get(x)));
                        ps.setInt(3, ic.listaIngredientes.get(i));
                        System.out.println(ic.listaIngredientes.get(i));
                        ps.setInt(4, pc.listaPasos.get(y));
                        System.out.println( pc.listaPasos.get(y));
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("se acabó");
        } finally {
            //Paso 5
            System.out.println("se murió");
            cerrarStatement(ps);
            desconectar();
        }
    }

    public int ConvertirIng(IngreTO ingreTO) {
        return ingreTO.getId();
    }

    public int ConvertirPA(PasosTO pasosTO) {
        return pasosTO.getIdPA();
    }
    
    public int ConvertirREC(RecetaTO recetaTO) {
        return recetaTO.getId();
    }
    
    public int ConvertirUS(RecetaTO recetaTO) {
        return recetaTO.getIdUS();
    }
}
