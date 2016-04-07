/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.coneccion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.TipoMascotaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author capacacitaciones
 */
public class TipoMascotaDao extends Conexion implements GenericoDao<TipoMascotaVo> {

    @Override
    public void insertar(TipoMascotaVo object) {

        PreparedStatement sentencia = null;
        try {

            conectar();
         //crear consulta de insercion

            String sql = "insert into tipo_mascota(nombre,estado) values(?,?)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // agregar parametros de insercion
            sentencia.setString(1, object.getNombre());
            sentencia.setBoolean(2, object.isEstado());
            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro de la mascota
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdTipoMascota(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }

    }

    //////////////////////////////////////////////////////////////
    @Override
    public void editar(TipoMascotaVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            //crear string del sql de actualizacion
            String sql = "update tipo_mascota set id_tipo_mascota = ?,  nombre = ?, estado = ?  where id_tipo_mascota = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdTipoMascota());
            sentencia.setString(2, object.getNombre());
            sentencia.setBoolean(3, object.isEstado());
            sentencia.setInt(4, object.getIdTipoMascota());
            //ejecutar la actualizacion
             sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
    }

    //////////////////////////////////////////////////////////////
    @Override
    public List<TipoMascotaVo> consultar() {

        PreparedStatement sentencia;
        List<TipoMascotaVo> lista = new ArrayList<>();
        try {
            conectar();
            String sql = " select * from tipo_mascota";
            sentencia = cnn.prepareStatement(sql);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                TipoMascotaVo tipomascota = new TipoMascotaVo();
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo
                tipomascota.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
                tipomascota.setNombre(rs.getString("nombre"));
                tipomascota.setEstado(rs.getBoolean("estado"));
                lista.add(tipomascota);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        } finally// despues del trycatch+tab se crea "finally" y el metodo "desconectar();"
        {
            desconectar();
        }
        return lista;

    }

    //////////////////////////////////////////////////////////////
    @Override
    public TipoMascotaVo consultar(int id) {

        PreparedStatement sentencia;
        TipoMascotaVo obj = new TipoMascotaVo();
        try {
            conectar();
            //consulta de un registro de la tabla segun la llave
            //primaria
            String sql = " select * from tipo_mascota where id_tipo_mascota = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo
                obj.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
                obj.setNombre(rs.getString("nombre"));
                obj.setEstado(rs.getBoolean("estado"));
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        } finally// despues del trycatch+tab se crea "finally" y el metodo "desconectar();"
        {
            desconectar();
        }
        return obj;

    }

}
