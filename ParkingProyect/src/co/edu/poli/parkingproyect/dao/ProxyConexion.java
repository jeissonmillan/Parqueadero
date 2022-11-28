package co.edu.poli.parkingproyect.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ProxyConexion {
	
	public static Connection conectar() throws ClassNotFoundException, SQLException{
		
		JFrame frame = new JFrame("JoptionPane Test");
		
		int result = JOptionPane.showConfirmDialog(null, "¿Eres el encargado del parqueadero?");
	    if(result==0) {
	    	return Conexion.obtener();
	    }
	    else if(result==1) {
	    	JOptionPane.showMessageDialog(frame, "Solo el encargado puede acceder");
	    	System.exit(1);
	    	return null;
	    }
	    else {
	    	JOptionPane.showMessageDialog(frame, "Solo el encargado puede acceder");
	    	System.exit(1);
	    	return null;
	    }
	    
	}

}
