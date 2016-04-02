/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.coneccion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ClienteVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author capacacitaciones
 */
public class ClienteDao extends Conexion implements GenericoDao<ClienteVo> {

    @Override
    public void insertar(ClienteVo object) {

        PreparedStatement sentencia = null;
        try {

            conectar();
            //crear consulta de insercion

            String sql = "insert into cliente(nombre,correo,telefono,estado) values(?,?,?,?,?)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // agregar parametros de insercion
            sentencia.setString(1, object.getNombre());
            sentencia.setString(2, object.getCorreo());
            sentencia.setString(3, object.getTelefono());
            sentencia.setBoolean(4, object.isEstado());

            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro del cliente
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdCliente(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }

    }

    ////////////////////////////////////////
    @Override
    public void editar(ClienteVo object) {
        PreparedStatement sentencia = null;
        try {

            conectar();
            //crear consulta de insercion

            String sql = "update cliente set id_cliente = ?,  nombre = ?, correo = ? , telefono= ? , estado = ? , where id_cliente = ?";
            sentencia = cnn.prepareStatement(sql);
            // agregar parametros de insercion
            sentencia.setInt(1, object.getIdCliente());
            sentencia.setString(2, object.getNombre());
            sentencia.setString(3, object.getCorreo());
            sentencia.setString(4, object.getTelefono());
            sentencia.setBoolean(5, object.isEstado());
            sentencia.setInt(6, object.getIdCliente());

            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro del cliente
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }

    }

    /////////////////////////////////////////////////////
    @Override
    public List<ClienteVo> consultar() {

        PreparedStatement sentencia;
        List<ClienteVo> lista = new ArrayList<>();
        try {
            conectar();
            String sql = " select * from cliente";
            sentencia = cnn.prepareStatement(sql);
            //resulset recive las respuestas satisfactorias de la base de datos 
            // las ecepciones se pueden controlar con el trycatch
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                ClienteVo cliente = new ClienteVo();
                //obtener el id de la mascota del cursor y asignarlo al atributo id mascota de un objeto de la clase MascotaVo
                
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstado(rs.getBoolean("estado"));
                lista.add(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        } finally// despues del trycatch+tab se crea "finally" y el metodo "desconectar();"
        {
 desconectar();
        }
        return lista;

    }

    @Override
    public ClienteVo consultar(int id) {

        PreparedStatement sentencia;
        ClienteVo obj = new ClienteVo();
        try {
            conectar();
            //consulta de un registro de la tabla segun la llave
            //primaria
            String sql = " select * from cliente where id_cliente= ?";
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
                obj.setIdCliente(rs.getInt("id_cliente"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCorreo(rs.getString("correo"));
                obj.setTelefono(rs.getString("telefono"));
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
