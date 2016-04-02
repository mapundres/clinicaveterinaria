/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.coneccion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ConsultaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author capacacitaciones
 */
public class ConsultaDao extends Conexion implements GenericoDao<ConsultaVo> {

    @Override
    public void insertar(ConsultaVo object) {

        PreparedStatement sentencia = null;
        try {

            conectar();
            //crear consulta de insercion

            String sql = "insert into consulta(fecha,motivo,descripcion,estado,id_medico,id_historia) values(?,?,?,?,?,?)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // agregar parametros de insercion
            sentencia.setDate(1, object.getFecha());
            sentencia.setString(2, object.getMotivo());
            sentencia.setString(3, object.getDescripcion());
            sentencia.setString(4, object.getEstado());
            sentencia.setInt(5, object.getIdMedico());
            sentencia.setInt(6, object.getIdHistoria());

            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro del cliente
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdConsulta(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }

    }

    /////////////////////////////////////////////////////////
    @Override
    public void editar(ConsultaVo object) {

        PreparedStatement sentencia;
        try {
            conectar();
            //crear string del sql de actualizacion
            String sql = "update consulta set id_consulta = ?,  fecha = ?, motivo = ?, descripcion=?, estado=? , id_medico= ? , id_historia = ? , where id_consulta = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdConsulta());
            sentencia.setString(2, object.getMotivo());
            sentencia.setString(3, object.getDescripcion());
            sentencia.setString(4, object.getEstado());
            sentencia.setInt(5, object.getIdMedico());
            sentencia.setInt(6, object.getIdHistoria());
            sentencia.setInt(7, object.getIdConsulta());
            //ejecutar la actualizacion
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }

    }

    ////////////////////////////////////////////////////////
    @Override
    public List<ConsultaVo> consultar() {

        PreparedStatement sentencia;
        List<ConsultaVo> lista = new ArrayList<>();
        try {
            conectar();
            String sql = " select * from consulta";
            sentencia = cnn.prepareStatement(sql);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                ConsultaVo consulta = new ConsultaVo();
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo

                consulta.setIdConsulta(rs.getInt("id_consulta"));
                consulta.setFecha(rs.getDate("fecha"));
                consulta.setMotivo(rs.getString("motivo"));
                consulta.setDescripcion(rs.getString("descripcion"));
                consulta.setEstado(rs.getString("estado"));
                consulta.setIdMedico(rs.getInt("id_medico"));
                consulta.setIdHistoria(rs.getInt("id_historia"));
                lista.add(consulta);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        } finally// despues del trycatch+tab se crea "finally" y el metodo "desconectar();"
        {
            desconectar();
        }
        return lista;

    }

    ///////////////////////////////////////////////////////
    @Override
    public ConsultaVo consultar(int id) {

        PreparedStatement sentencia;
        ConsultaVo obj = new ConsultaVo();
        try {
            conectar();
            //consulta de un registro de la tabla segun la llave
            //primaria
            String sql = " select * from consulta where id_consulta= ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo

                /*
                 cliente.setNombre(rs.getString("nombre"));
                 cliente.setCorreo(rs.getString("correo"));
                 cliente.setTelefono(rs.getString("telefono"));
                 cliente.setEstado(rs.getBoolean("estado"));
                 */
                obj.setIdConsulta(rs.getInt("id_consulta"));
                obj.setFecha(rs.getDate("fecha"));
                obj.setMotivo(rs.getString("motivo"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setEstado(rs.getString("estado"));
                obj.setIdMedico(rs.getInt("id_medico"));
                obj.setIdHistoria(rs.getInt("id_historia"));
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
