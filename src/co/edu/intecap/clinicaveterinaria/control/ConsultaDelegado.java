/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.ConsultaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ConsultaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ConsultaDelegado {
    
    
     private final JFrame contenedor;
    private final ConsultaDao consultaDao;
    
     public ConsultaDelegado(JFrame contenedor) {
        // las constantes se como final double pi =3.1416
        //ASIGNACION INICIAL DE LAS CONSTANTES
        this.contenedor = contenedor;
        this.consultaDao =  new ConsultaDao();
     }
   
     
     public void insertarConsulta(ConsultaVo consultaVo)
 {
     try {
         this.consultaDao.insertar(consultaVo);
     } catch (Exception e) {
         JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
 }

    ///////////////////////////////////////////
     
     public void editarConsulta(ConsultaVo consultaVo)
 {
     try {
      
         this.consultaDao.editar(consultaVo);
     } catch (Exception e) {
         JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
 }
     public List<ConsultaVo> consultarConsulta(){
     List<ConsultaVo> listaConsulta;
     try {
       listaConsulta = this.consultaDao.consultar();
     } catch (Exception e) {
         listaConsulta= new ArrayList<>();
          JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
     return listaConsulta;
 }
 
     public ConsultaVo consultarConsulta(int id)
 {
     ConsultaVo consultaVo;
     try {
         consultaVo= this.consultaDao.consultar(id);
     } catch (Exception e) {
         consultaVo= new ConsultaVo();
          JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersion", JOptionPane.ERROR_MESSAGE);
     }
     return consultaVo;
 }      
     
}
