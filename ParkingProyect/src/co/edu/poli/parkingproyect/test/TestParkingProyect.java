package co.edu.poli.parkingproyect.test;


import java.sql.SQLException;

import co.edu.poli.parkingproyect.dao.AutoDao;
import co.edu.poli.parkingproyect.dao.Conexion;
import co.edu.poli.parkingproyect.model.Auto;
import junit.framework.TestCase;

public class TestParkingProyect extends TestCase {
	private AutoDao autodao;
	private Auto auto;
	
	public void escenario() {
		autodao=new AutoDao();
		auto=new Auto("HRT987", "Maria", "Paez", 1200);
	}
	
	public void testRegistrar() {
		escenario();
		assertNotNull(auto);
	}
	
	public void testConsultarHora() throws ClassNotFoundException, SQLException {
		escenario();
	}
	

}
