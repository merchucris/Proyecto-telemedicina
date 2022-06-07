package application.model;

public class Saturacion {
	private int id_saturacion;
	private String dni;
	private String marcaDeTiempo;
	private float saturacion;


	public Saturacion(int id_saturacion, String dni, String marcaDeTiempo, float saturacion) {
		this.id_saturacion = id_saturacion;
		this.dni = dni;
		this.marcaDeTiempo = marcaDeTiempo;
		this.saturacion = saturacion;
	}
	
	public int getId_temperatura() {
		return id_saturacion;
	}

	public void setId_temperatura(int id_saturacion) {
		this.id_saturacion = id_saturacion;
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
	
	public void setsaturacion(float saturacion) {
		this.saturacion = saturacion;
	}
	public float getsaturacion() {
		return saturacion;
	}


}
