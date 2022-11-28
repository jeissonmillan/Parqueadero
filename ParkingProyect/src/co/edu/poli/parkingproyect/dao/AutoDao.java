package co.edu.poli.parkingproyect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.parkingproyect.dao.Conexion;
import co.edu.poli.parkingproyect.model.Auto;
import javafx.collections.ObservableList;
import co.edu.poli.parkingproyect.controller.MainSceneController;

public class AutoDao {
	
public void Registrar(Auto auto) throws ClassNotFoundException {
		
    	//QUERY
    	String consulta = "insert into registros (nombre, apellido, placa, hora_ingreso, hora_salida) values ('"+ auto.getNombrePro() +"','"+ auto.getApellidoPro() +"','" + auto.getPlaca() +"'," + auto.getHora() + ", 0);";                  
    	try {
    		//INICIAMOS LA CONEXION
			Statement sentencia = ProxyConexion.conectar().createStatement();
			
			//EJECUTAMOS LA QUERY
			int resultado = sentencia.executeUpdate(consulta);

		} catch (SQLException e) {

			e.printStackTrace();
		}
    	
    }

public void RegistrarSalida(Auto auto) throws ClassNotFoundException {
	
	//QUERY
	String consulta = "delete from registros where placa ='" + auto.getPlaca() + "'";                  
	try {
		//INICIAMOS LA CONEXION
		Statement sentencia = ProxyConexion.conectar().createStatement();
		
		//EJECUTAMOS LA QUERY
		int resultado = sentencia.executeUpdate(consulta);

	} catch (SQLException e) {

		e.printStackTrace();
	}
	
}

public void RegistrarTarifa(Auto auto) throws ClassNotFoundException {
	
	// QUERY PARA MODIFICAR/AGREGAR LA HORA DE SALIDA DEL VEHICULO
	String consulta = "UPDATE registros set hora_salida = " + auto.getHora() + " where placa = '" + auto.getPlaca() +"';";
	
	//QUERY PARA OBTENER EL DATIO DE LA HORA DE INGRESO DEL VEHICULO
	String consulta2 = "Select hora_ingreso from registros where placa ='" + auto.getPlaca() + "' ; ";
	
	try {
		
		//INICIAMOS LA CONEXION
		Statement sentencia = ProxyConexion.conectar().createStatement();
		
		//EJECUTAMOS LA QUERY
		int resultado = sentencia.executeUpdate(consulta);
		
		//OBTENEMOS EL RESULTADO
		ResultSet hora_ingreso = sentencia.executeQuery(consulta2);
		String ingreso = "";
		while (hora_ingreso.next()) {
			  ingreso = hora_ingreso.getString(1);
		}
		int ingresoint = Integer.parseInt(ingreso);
		int salidaint = auto.getHora();
		double total = (salidaint-ingresoint);
	
	} catch (SQLException e) {

		e.printStackTrace();
	}
}


public Auto consultar(String placa) throws ClassNotFoundException, SQLException {
	Auto auto2=null;
	boolean isSelect = Conexion.obtener().prepareCall("select * from registros where placa = '"+placa+"'").execute();
	if(isSelect)
	{
	    //de ser verdadero isSelect debemos obtener los datos del Select con la clase ResultSet
	    ResultSet resultados = Conexion.obtener().prepareCall("select * from registros  where placa = '"+placa+"'").executeQuery();
	    //debemos iterar sobre resultados hasta que la condicion pase a false(ya no habran mas filas por recorrer)
	    while(resultados.next())
	    {
	    /*    //obtenemos el String de las columnas
	        System.out.print(resultados.getString(1) + " ");
	        System.out.print(resultados.getString(2) + " ");
	        System.out.print(resultados.getString(3) + " ");
	        System.out.print(resultados.getString(4) + " ");
	        System.out.println(resultados.getString(5));*/
	        
	         auto2 = new Auto(resultados.getString(1), resultados.getString(2), resultados.getString(3), Integer.parseInt(resultados.getString(4)));
	    }
	    return auto2;

	}
	else
	{
	    //en caso de que isSelect sea false, indica que la consulta SQL no es un Select , por lo tanto sera un INSERT,UPDATE O DELETE por lo que debemos validar que se hayan modificado las filas
	    //el metodo executeUpdate() nos retornara un long indicando la cantidad de filas afectadas en caso de ser 0 ninguna fila fue modificada, de caso contrario nos indicara la cantidad de filas afectadas
	    int filasModificadas = Conexion.obtener().prepareCall("select * from registros").executeUpdate();
	}
	return null;
}
public String consultarHora(Auto auto) throws ClassNotFoundException, SQLException {
    boolean isSelect = Conexion.obtener().prepareCall("select * from registros").execute();
    String a= "";
    if(isSelect)
    {
        //de ser verdadero isSelect debemos obtener los datos del Select con la clase ResultSet
        ResultSet resultados = Conexion.obtener().prepareCall("select hora_ingreso from registros where placa='"+auto.getPlaca()+"'").executeQuery();
        //debemos iterar sobre resultados hasta que la condicion pase a false(ya no habran mas filas por recorrer)
        while(resultados.next())
        {
            //obtenemos el String de las columnas
            a =resultados.getString(1);



       }
        return a;



   }
    else
    {
        //en caso de que isSelect sea false, indica que la consulta SQL no es un Select , por lo tanto sera un INSERT,UPDATE O DELETE por lo que debemos validar que se hayan modificado las filas
        //el metodo executeUpdate() nos retornara un long indicando la cantidad de filas afectadas en caso de ser 0 ninguna fila fue modificada, de caso contrario nos indicara la cantidad de filas afectadas
        int filasModificadas = Conexion.obtener().prepareCall("select * from registros").executeUpdate();
    }
    return a;
}


}
