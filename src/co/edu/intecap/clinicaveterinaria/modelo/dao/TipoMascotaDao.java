/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.coneccion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.TipoMascotaVo;
import java.util.List;

/**
 *
 * @author capacacitaciones
 */
public class TipoMascotaDao extends Conexion implements GenericoDao<TipoMascotaVo> {

    @Override
    public void insertar(TipoMascotaVo object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(TipoMascotaVo object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoMascotaVo> consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoMascotaVo consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
