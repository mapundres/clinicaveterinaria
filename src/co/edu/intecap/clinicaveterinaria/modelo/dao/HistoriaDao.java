/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.coneccion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.Historia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class HistoriaDao extends Conexion implements GenericoDao<Historia> {

    @Override
    public void insertar(Historia object) {

        PreparedStatement sentencia = null;
        try {

            conectar();
            //crear consulta de insercion

            String sql = "insert into historia(fecha_apertura,fecha_cierre,estado,id_mascota) values(?,?,?,?)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // agregar parametros de insercion
            sentencia.setDate(1, object.getFechaApertura());
            sentencia.setDate(2, object.getFechaCierre());
            sentencia.setBoolean(3, object.isEstado());
            sentencia.setInt(4, object.getIdMascota());

            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro del cliente
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdMascota(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }

    }

    ///////////////////////////////////////////
    @Override
    public void editar(Historia object) {

        PreparedStatement sentencia;
        try {
            conectar();
            //crear string del sql de actualizacion
            String sql = "update historia set id_historia = ?,  fecha_´pertura = ?, fecha_cierra = ? , estado= ? , id_mscota = ? , where id_histori = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdHistoria());
            sentencia.setDate(2, object.getFechaApertura());
            sentencia.setDate(3, object.getFechaCierre());
            sentencia.setBoolean(4, object.isEstado());
            sentencia.setInt(5, object.getIdMascota());
            sentencia.setInt(7, object.getIdHistoria());
            //ejecutar la actualizacion
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }

    }

    ///////////////////////////////////////////
    @Override
    public List<Historia> consultar() {

        PreparedStatement sentencia;
        List<Historia> lista = new ArrayList<>();
        try {
            conectar();
            String sql = " select * from historia";
            sentencia = cnn.prepareStatement(sql);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Historia histoia = new Historia();
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo
                histoia.setIdHistoria(rs.getInt("id_historia"));
                histoia.setFechaApertura(rs.getDate("fecha_apertura"));
                histoia.setFechaCierre(rs.getDate("fecha_cierra"));
                histoia.setEstado(rs.getBoolean("estado"));
                histoia.setIdMascota(rs.getInt("id_mascota"));
                lista.add(histoia);

            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        } finally// despues del trycatch+tab se crea "finally" y el metodo "desconectar();"
        {
            desconectar();
        }
        return lista;

    }

    ///////////////////////////////////////////
    @Override
    public Historia consultar(int id) {

        PreparedStatement sentencia;
        Historia obj = new Historia();
        try {
            conectar();
            //consulta de un registro de la tabla segun la llave
            //primaria
            String sql = " select * from historia where id_historia= ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo      
                obj.setIdHistoria(rs.getInt("id_historia"));
                obj.setFechaApertura(rs.getDate("fecha_apertura"));
                obj.setFechaCierre(rs.getDate("fecha_cierra"));
                obj.setEstado(rs.getBoolean("estado"));
                obj.setIdMascota(rs.getInt("id_mascota"));
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        } finally// despues del trycatch+tab se crea "finally" y el metodo "desconectar();"
        {
            desconectar();
        }
        return obj;
    }

    public Historia historia(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
