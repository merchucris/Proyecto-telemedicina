package application.model;

import java.sql.Timestamp;

public class MensajeMedico {
	
	public MensajeMedico(int id_mensaje, int id_medico_receptor, Timestamp marcaDeTiempo, int id_consultor_remitente,String asunto, 
			       String mensajescon) {
		super();
		this.asunto = asunto;
		this.mensajescon = mensajescon;
		this.marcaDeTiempo = marcaDeTiempo;
		this.id_mensaje = id_mensaje;
		this.id_medico_receptor = id_medico_receptor;
		this.id_consultor_remitente = id_consultor_remitente;
	}
	public String asunto,mensajescon;
	public Timestamp marcaDeTiempo;
	public int id_mensaje,id_medico_receptor,id_consultor_remitente;
	
	public void setId_Mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}
	
	public int getId_Mensaje() {
		return id_mensaje;
	}
	
	public int getid_medico_receptor() {
		return id_medico_receptor;
	}
	public void setid_medico_receptor(int id_medico_receptor) {
		this.id_medico_receptor = id_medico_receptor;
	}
	public int getid_consultor_remitente() {
		return id_consultor_remitente;
	}
	public void setid_consultor_remitente(int id_consultor_remitente) {
		this.id_consultor_remitente = id_consultor_remitente;
	}
	public String getMensajeCon() {
		return mensajescon;
	}
	public void setMensajeCon(String mensaje) {
		this.mensajescon = mensaje;
	}

	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public Timestamp getmarcaDeTiempo() {
		return this.marcaDeTiempo;
	}
	public void setmarcaDeTiempo(Timestamp marcaDeTiempo) {
		this.marcaDeTiempo = marcaDeTiempo;
	}	

}
