package co.edu.poli.parkingproyect.model;

public class Auto {
	
	public String placa;
	private String nombrePro;
	private String apellidoPro;
	private int hora;
	
	public Auto(String placa, String nombrePro, String apellidoPro, int hora) {
		super();
		this.placa = placa;
		this.nombrePro = nombrePro;
		this.apellidoPro = apellidoPro;
		this.hora = hora;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNombrePro() {
		return nombrePro;
	}

	public void setNombrePro(String nombrePro) {
		this.nombrePro = nombrePro;
	}

	public String getApellidoPro() {
		return apellidoPro;
	}

	public void setApellidoPro(String apellidoPro) {
		this.apellidoPro = apellidoPro;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Auto [placa=" + placa + ", nombrePro=" + nombrePro + ", apellidoPro=" + apellidoPro + ", hora=" + hora
				+ "]";
	}
	
	
	
}
