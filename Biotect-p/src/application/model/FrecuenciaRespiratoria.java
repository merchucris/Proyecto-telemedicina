package application.model;

public class FrecuenciaRespiratoria {
	
	private int id_frecRespirato;
	private String dni;
	private String marcaDeTiempo;
	private float frec_respi;


	public FrecuenciaRespiratoria(int id_frecRespirato, String dni, String marcaDeTiempo, float frec_respi) {
		this.id_frecRespirato = id_frecRespirato;
		this.dni = dni;
		this.marcaDeTiempo = marcaDeTiempo;
		this.frec_respi = frec_respi;
	}
	
	public int getId_frecRespirato() {
		return id_frecRespirato;
	}

	public void setId_frecRespirato(int frec_respi) {
		this.frec_respi = frec_respi;
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
	
	public void setfrec_respi(float frec_respi) {
		this.frec_respi = frec_respi;
	}
	public float getfrec_respi() {
		return frec_respi;
	}



}
