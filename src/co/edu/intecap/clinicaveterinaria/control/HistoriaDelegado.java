/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.HistoriaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.Historia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class HistoriaDelegado {

    private final JFrame contenedor;
    private final HistoriaDao historiaDao;

    public HistoriaDelegado(JFrame contenedor) {
        // las constantes se como final double pi =3.1416
        //ASIGNACION INICIAL DE LAS CONSTANTES
        this.contenedor = contenedor;
        this.historiaDao = new HistoriaDao();
    }

       public void insertarHistoria(Historia historia)
 {
     try {
         this.historiaDao.insertar(historia);
     } catch (Exception e) {
         JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
 }

    ///////////////////////////////////////////
    public void editarHistoria(Historia historia) {
        try {

            this.historiaDao.editar(historia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Historia> consultarHistoria() {
        List<Historia> listaHistoria;
        try {
            listaHistoria = this.historiaDao.consultar();
        } catch (Exception e) {
            listaHistoria = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
        return listaHistoria;
    }

    public Historia consultarHistoria(int id) {
        Historia historiaVo;
        try {
            historiaVo = this.historiaDao.consultar(id);
        } catch (Exception e) {
            historiaVo  = new Historia();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
        return historiaVo;
    }

   
    
    

}
