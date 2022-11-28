package co.edu.poli.parkingproyect.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

// CLASE ENCARGADA DE LA CONEXION CON BD MYSQL
public class Conexion{
	
	//IMPLEMENTACION DEL PATRON SINGLETON
    private static Connection cnx = null;

    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                // ENLACE/NOMBREDB/ROOT/PASSWORD
                cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingdb", "root", "root");
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } 
        }
        return cnx;
    }

    //METODO PARA CERRAR LA CONEXION CON MYSQL
    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }
}