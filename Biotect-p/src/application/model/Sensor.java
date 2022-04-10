package application.model;

public class Sensor {
	private String dni;
	private String marcaDeTiempo;
	private float temperatura;
	private int glucemia;
	private int saturacion;

	public Sensor(String dni, String marcaDeTiempo, float temperatura, int glucemia, int saturacion) {
		this.dni = dni;
		this.marcaDeTiempo = marcaDeTiempo;
		this.temperatura = temperatura;
		this.glucemia = glucemia;
		this.saturacion = saturacion;
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
	
	public void setglucemia(int glucemia) {
		this.glucemia = glucemia;
	}
	public float getglucemia() {
		return glucemia;
	}
	
	public void setsaturacion(int saturacion) {
		this.saturacion = saturacion;
	}
	public int getsaturacion() {
		return saturacion;
	}
}
