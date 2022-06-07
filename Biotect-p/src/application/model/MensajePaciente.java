package application.model;

import java.sql.Timestamp;

public class MensajePaciente {
	
	public MensajePaciente(int id_mensaje, int id_paciente_receptor, Timestamp marcaDeTiempo, int id_medico_remitente,String asunto, 
			       String mensaje) {
		super();
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.marcaDeTiempo = marcaDeTiempo;
		this.id_mensaje = id_mensaje;
		this.id_paciente_receptor = id_paciente_receptor;
		this.id_medico_remitente = id_medico_remitente;
	}
	public String asunto,mensaje;
	public Timestamp marcaDeTiempo;
	public int id_mensaje,id_paciente_receptor,id_medico_remitente;
	
	public void setId_Mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}
	
	public int getId_Mensaje() {
		return id_mensaje;
	}
	
	public int getid_paciente_receptor() {
		return id_paciente_receptor;
	}
	public void setid_paciente_receptor(int id_paciente_receptor) {
		this.id_paciente_receptor = id_paciente_receptor;
	}
	public int getid_medico_remitente() {
		return id_medico_remitente;
	}
	public void setid_medico_remitente(int id_medico_remitente) {
		this.id_medico_remitente = id_medico_remitente;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
