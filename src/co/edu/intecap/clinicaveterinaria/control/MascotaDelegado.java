/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.MascotaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author capacacitaciones
 */
public class MascotaDelegado {
    //ATRIVUTOS DE CLASE QUE SON CONSTANTES(su valor como objeto no puede cambiar )
    private final JPanel contenedor;
    private final MascotaDao mascotaDao;

    public MascotaDelegado(JPanel contenedor) {
        // las constantes se como final double pi =3.1416
        //ASIGNACION INICIAL DE LAS CONSTANTES
        this.contenedor = contenedor;
        this.mascotaDao =  new MascotaDao();
     }
 public void insertarMascota(MascotaVo mascotaVo)
 {
     try {
         this.mascotaDao.insertar(mascotaVo);
     } catch (Exception e) {
         JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
 }
 public void editarMascota(MascotaVo mascotaVo)
 {
     try {
         this.mascotaDao.editar(mascotaVo);
     } catch (Exception e) {
         JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
 }
 
 public List<MascotaVo> consultarMascotas(){
     List<MascotaVo> listaMascotas;
     try {
         listaMascotas = this.mascotaDao.consultar();
     } catch (Exception e) {
         listaMascotas = new ArrayList<>();
          JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
     return listaMascotas;
 }
 
 public MascotaVo consultarMascota(int id)
 {
     MascotaVo mascotaVo;
     try {
         mascotaVo= this.mascotaDao.consultar(id);
     } catch (Exception e) {
         mascotaVo= new MascotaVo();
          JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
     return mascotaVo;
 }
 
}
