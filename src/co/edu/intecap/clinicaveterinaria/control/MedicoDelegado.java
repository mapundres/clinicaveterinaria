/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.MedicoDao;
import co.edu.intecap.clinicaveterinaria.modelo.dao.TipoMascotaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MedicoVo;
import co.edu.intecap.clinicaveterinaria.modelo.vo.TipoMascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author capacacitaciones
 */
public class MedicoDelegado {
    
        
     private final JPanel contenedor;
    private final MedicoDao medicoDao;
    private MedicoVo MedicoVo;
    private List<MedicoVo> listaMedico;

    public MedicoDelegado(JPanel contenedor) {
        // las constantes se como final double pi =3.1416
        //ASIGNACION INICIAL DE LAS CONSTANTES
        this.contenedor = contenedor;
        this.medicoDao = new MedicoDao();
    }

       public void insertarMedico(MedicoVo medicoVo)
 {
     try {
         this.medicoDao.insertar(medicoVo);
     } catch (Exception e) {
         JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
 }

    ///////////////////////////////////////////
    public void editarHistoria(MedicoVo medicoVo) {
        try {

            this.medicoDao.editar(medicoVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<MedicoVo> consultarHistoria() {
        List<MedicoVo> listaMedico;
        try {
            listaMedico = this.medicoDao.consultar();
        } catch (Exception e) {
            listaMedico = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
        return listaMedico;
    }

    public MedicoVo consultarHistoria(int id) {
        MedicoVo medicoVo;
        try {
            medicoVo = this.medicoDao.consultar(id);
        } catch (Exception e) {
            medicoVo  = new MedicoVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
        return medicoVo;
    }
    
}
