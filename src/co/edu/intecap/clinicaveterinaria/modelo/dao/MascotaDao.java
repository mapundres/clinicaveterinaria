/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.coneccion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ClienteVo;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MascotaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author capacacitaciones
 */
public class MascotaDao extends Conexion implements GenericoDao<MascotaVo>{

    @Override
    public void insertar(MascotaVo object) {
        PreparedStatement sentencia = null;
        try {
            
            
         conectar();
         //crear consulta de insercion
         
        String sql="insert into mascota(nombre,edad,estado,id_tipo_mascota,id_cliente) values(?,?,?,?,?)"; 
        sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // agregar parametros de insercion
        sentencia.setString(1, object.getNombre());
        sentencia.setInt(2,object.getEdad());
        sentencia.setBoolean(3, object.isEstado());
        sentencia.setInt(4, object.getIdTipoMascota());
        sentencia.setInt(5,object.getIdCliente());
        //ejecutar la insercion
        sentencia.executeUpdate();
        //obtener la llave de registro de la mascota
        ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
               object.setIdMascota(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally{
         desconectar();
        }
    }

    
    //////////////////////////////////////////
    
    
    
    @Override
    public void editar(MascotaVo object) {
       PreparedStatement sentencia;
        try {
            conectar();
            //crear string del sql de actualizacion
            String sql="update mascota set id_mascota = ?,  nombre = ?, estado = ? , id_tipo_mascota= ? , id_cliente = ? , where id_mascota = ?";
         sentencia = cnn.prepareStatement(sql);
         sentencia.setInt(1, object.getIdMascota());
         sentencia.setString(2, object.getNombre());
         sentencia.setInt(3, object.getEdad());
         sentencia.setBoolean(4, object.isEstado());
         sentencia.setInt(5,object.getIdTipoMascota());
         sentencia.setInt(6,object.getIdCliente());
         sentencia.setInt(7,object.getIdMascota());
         //ejecutar la actualizacion
         sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally{
            desconectar();
        }
    }

    @Override
    public List<MascotaVo> consultar() {
        
        PreparedStatement sentencia;
        List<MascotaVo> lista = new ArrayList<>();
        try {
            conectar();
            String sql =" select * from mascota";
            sentencia= cnn.prepareStatement(sql);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs= sentencia.executeQuery();
            while (rs.next()) {
                MascotaVo mascota = new MascotaVo();
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo
                mascota.setIdMascota(rs.getInt(" id_mascota"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setEstado(rs.getBoolean("estado"));
                mascota.setIdTipoMascota((rs.getInt("id_tipo_mascota")));
                mascota.setIdCliente(rs.getInt("id_cliente"));
                lista.add(mascota);
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
            
        }finally// despues del trycatch+tab se crea "finally" y el metodo "desconectar();"
        {
            desconectar();
        }
        return lista;
    }
    
    ///////////////
    
    
    
    @Override
    public MascotaVo consultar(int id) {
         PreparedStatement sentencia;
       MascotaVo obj = new MascotaVo();
        try {
            conectar();
            //consulta de un registro de la tabla segun la llave
            //primaria
            String sql =" select * from mascota where id_mascota= ?";
            sentencia= cnn.prepareStatement(sql);
            sentencia.setInt(1,id);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs= sentencia.executeQuery();
            if (rs.next()) {
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo
                obj.setIdMascota(rs.getInt(" id_mascota"));
                obj.setNombre(rs.getString("nombre"));
                obj.setEdad(rs.getInt("edad"));
                obj.setEstado(rs.getBoolean("estado"));
                obj.setIdTipoMascota((rs.getInt("id_tipo_mascota")));
                obj.setIdCliente(rs.getInt("id_cliente"));
               
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
            
        }finally// despues del trycatch+tab se crea "finally" y el metodo "desconectar();"
        {
            desconectar();
        }
        return obj;
    }

    public void insertar(ClienteVo clienteVo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editar(ClienteVo clienteVo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
