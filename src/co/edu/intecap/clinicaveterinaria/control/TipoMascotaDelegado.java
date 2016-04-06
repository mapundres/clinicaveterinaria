/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.TipoMascotaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.TipoMascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TipoMascotaDelegado {

    private final JPanel contenedor;
    private final TipoMascotaDao tipomascotaDao;

    public TipoMascotaDelegado(JPanel contenedor) {
        // las constantes se como final double pi =3.1416
        //ASIGNACION INICIAL DE LAS CONSTANTES
        this.contenedor = contenedor;
        this.tipomascotaDao = new TipoMascotaDao();
    }

    public void insertarTipoMascota(TipoMascotaVo tipomascotaVo) {
        try {
            this.tipomascotaDao.insertar(tipomascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
    }

    ///////////////////////////////////////////
    public void editarTipoMascota(TipoMascotaVo tipomascotaVo) {
        try {
            this.tipomascotaDao.editar(tipomascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<TipoMascotaVo> consultarTipoMascotas() {
        List<TipoMascotaVo> listaTipoMascota;
        try {
            listaTipoMascota = this.tipomascotaDao.consultar();
        } catch (Exception e) {
            listaTipoMascota = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
        return listaTipoMascota;
    }

    public TipoMascotaVo consultarTipoMascota(int id) {
        TipoMascotaVo tipomascotaVo;
        try {
            tipomascotaVo = this.tipomascotaDao.consultar(id);
        } catch (Exception e) {
            tipomascotaVo = new TipoMascotaVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
        }
        return tipomascotaVo;
    }

    }
