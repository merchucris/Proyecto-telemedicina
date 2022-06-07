package application.model;

public class Temperatura {
	private int id_temperatura;
	private String dni;
	private String marcaDeTiempo;
	private float temperatura;


	public Temperatura(int id_temperatura, String dni, String marcaDeTiempo, float temperatura) {
		this.id_temperatura = id_temperatura;
		this.dni = dni;
		this.marcaDeTiempo = marcaDeTiempo;
		this.temperatura = temperatura;
	}
	
	public int getId_temperatura() {
		return id_temperatura;
	}

	public void setId_temperatura(int id_temperatura) {
		this.id_temperatura = id_temperatura;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDni() {
		return dni;
	}
	
	public void setmarcaDeTiempo(String marcaDeTiempo) {
		this.marcaDeTiempo = marcaDeTiempo;
	}
	public String getmarcaDeTiempo() {
		return marcaDeTiempo;
	}
	
	public void settemperatura(float temperatura) {
		this.temperatura = temperatura;
	}
	public float gettemperatura() {
		return temperatura;
	}

}
