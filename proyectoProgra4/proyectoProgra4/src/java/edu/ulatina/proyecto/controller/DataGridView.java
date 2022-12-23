package edu.ulatina.proyecto.controller;

import edu.ulatina.proyecto.model.RecetaTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author davidgarcia
 */

@ManagedBean(name = "dataGridView")
@ViewScoped
public class DataGridView implements Serializable {
    private List<RecetaTO> recetas;
    private RecetaTO selectedReceta;

    public DataGridView() {
    }

    public List<RecetaTO> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<RecetaTO> recetas) {
        this.recetas = recetas;
    }

    public RecetaTO getSelectedReceta() {
        return selectedReceta;
    }

    public void setSelectedReceta(RecetaTO selectedReceta) {
        this.selectedReceta = selectedReceta;
    }

    public DataGridView(List<RecetaTO> recetas, RecetaTO selectedReceta) {
        this.recetas = recetas;
        this.selectedReceta = selectedReceta;
    }
}
