package edu.ulatina.proyecto.model;

import java.io.Serializable;

/**
 * @author davidgarcia
 */
public class PasosTO implements Serializable {
    private String paso;
    private int idPA;


    public PasosTO() {
    }

    public PasosTO( int idPA, String paso) {
        this.paso = paso;
        this.idPA = idPA;
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
}


