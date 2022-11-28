module ParkingProyect {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	requires junit;
	
	opens co.edu.poli.parkingproyect.controller to javafx.graphics, javafx.fxml;
	opens co.edu.poli.parkingproyect.model to javafx.graphics, javafx.fxml;
}
