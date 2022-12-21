
package edu.ulatina.proyecto.service;

import java.sql.*;

/**
 * @author laboratorio
 * @author davidgarcia
 */
public class Servicio {
    protected Connection conexion = null;
    private final String host = "localhost";
    private final String puerto = "3306";
    private final String sid = "proyectopro4"; //nombre del esquema
    private final String usuario = "root";
    private final String clave = "adminadmin";//adminadmin, root, Ywfkus15, admin

    public void conectar() {
        try {
            //Paso 1: llamar al driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Paso 2: conectar el driver
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + puerto + "/" + sid + /*"?autoReconnect=true"*/ "?serverTimezone=UTC",
                    usuario, clave);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void cerrarStatement(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarResultSet(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexion = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
