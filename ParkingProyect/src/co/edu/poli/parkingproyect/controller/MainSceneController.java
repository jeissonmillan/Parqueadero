package co.edu.poli.parkingproyect.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.ArcTo;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.spi.ResourceBundleProvider;

import co.edu.poli.parkingproyect.dao.AutoDao;
import co.edu.poli.parkingproyect.dao.Conexion;
import co.edu.poli.parkingproyect.model.Auto;

public class MainSceneController {
	
	private int D=10;
	@FXML
	private Label Lbienvenida1;
	@FXML
	private Label Lbienvenida2;
	@FXML
	private Label Lbienvenida3;
	@FXML
	private Label Lbienvenida5;
	@FXML
	private Label Lbienvenida6;
	@FXML
	private Label Lbienvenida7;
	@FXML
	private Label Lbienvenida4;
	@FXML
	private Label Lbienvenida8;
	@FXML
	private Label Lingreso2;
	@FXML
	private TextField TFingresoNombre;
	@FXML
	private TextField TFingresoApellido;
	@FXML
	private TextField TFingresoPlaca;
	@FXML
	private TextField TFingresoHora;
	@FXML
	private Label Lingreso3;
	@FXML
	private Button Botonregistrar;
	@FXML
	private Label Lingreso5;
	@FXML
	private Label Lingreso;
	@FXML
	private Label Lsalida1;
	@FXML
	private Label Lsalida2;
	@FXML
	private TextField TFsalidaPlaca;
	@FXML
	private TextField TFDisponibilidad;
	@FXML
	private Label Lsalida3;
	@FXML
	private TextField TFsalidaHora;
	@FXML
	private Button BotonTarifa;
	@FXML
	private Button BotonSalida;
	@FXML
	private Label Lsalida4;
	@FXML
	private TextField TFsalidaFacturaPlaca;
	@FXML
	private TextField TFsalidaHoraingreso;
	@FXML
	private TextField TFsalidaHorasalida;
	@FXML
	private TextField TFsalidaTarifa;
	@FXML
	private Label Lsalida5;
	@FXML
	private TableView <Auto>TableRegistros;
	@FXML
	private TableColumn <Auto,String> RegistroPlaca;
	@FXML
	private TableColumn <Auto,String> RegistroNombre;
	@FXML
	private TableColumn <Auto,String> RegistroHora;
	@FXML
	private TableColumn <Auto,String> RegistroHora1;
	@FXML
	private Label Lregistros1;
	
	public ObservableList<Auto> registro= FXCollections.observableArrayList();

	// Event Listener on Button[#BotonRegistrar].onAction
	// IMPLEMENTACION DE LA ACCION PARA EL BOTON REGISTRAR
	
	
	
	
	
	@FXML
	public void BotonRegistrar(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if(D==0) {
			TFDisponibilidad.setText("No hay disponibilidad");
		}
		else{
			Auto auto1 = new Auto(TFingresoPlaca.getText(), TFingresoNombre.getText(), TFingresoApellido.getText(), Integer.parseInt(TFingresoHora.getText()));
			AutoDao autoDao1 = new AutoDao();
			autoDao1.Registrar(auto1);
			
			Auto auto2= autoDao1.consultar(TFingresoPlaca.getText());
			
	//		System.out.println("Apellido: " + auto2.getPlaca());
			
			registro.add(auto1);
			TableRegistros.setItems(registro);
			
			RegistroPlaca.setCellValueFactory(new PropertyValueFactory<Auto, String>("nombrePro"));
			
			
			


			
		
		D=D-1;
		
		TFDisponibilidad.setText(String.valueOf(D));
		
	
		}
	}
	// Event Listener on Button[#BotonTarifa].onAction
	// IMPLEMENTACION DE LA ACCION PARA EL BOTON CALCULAR TARIFA
	@FXML
	public void BotonTarifa(ActionEvent event) throws ClassNotFoundException, NumberFormatException, SQLException {
		Auto auto1 = new Auto(TFsalidaPlaca.getText(), null, null, Integer.parseInt(TFsalidaHora.getText()));
		AutoDao autoDao1 = new AutoDao();
		autoDao1.RegistrarTarifa(auto1);
		
		int a= Integer.parseInt(TFsalidaHora.getText());
		int b= Integer.parseInt(autoDao1.consultarHora(auto1));
		double total= a-b;
		total = (total/100)*6100;
		TFsalidaTarifa.setText("$" + String.valueOf(total));
	
		
	}
	// Event Listener on Button[#BotonSalida].onAction
	// IMPLEMENTACION DE LA ACCION PARA EL BOTON VALIDAR SALIDA
	@FXML
	public void BotonValidarsalida(ActionEvent event) throws ClassNotFoundException, SQLException {
		Auto auto1 = new Auto(TFsalidaPlaca.getText(), null, null, Integer.parseInt(TFsalidaHora.getText()));
		AutoDao autoDao1 = new AutoDao();
		TFsalidaFacturaPlaca.setText(TFsalidaPlaca.getText());
		
		TFsalidaHorasalida.setText(TFsalidaHora.getText());
		TFsalidaHoraingreso.setText(autoDao1.consultarHora(auto1));

		D=D+1;
		
		
		int a= Integer.parseInt(TFsalidaHora.getText());
		int b= Integer.parseInt(autoDao1.consultarHora(auto1));
		
		
		double total= a-b;
		total = (total/100)*6100;
		
		TFsalidaTarifa.setText("$" + String.valueOf(total));
		
		TFDisponibilidad.setText(String.valueOf(D));
		
		autoDao1.RegistrarSalida(auto1);
		
		
	}
}
