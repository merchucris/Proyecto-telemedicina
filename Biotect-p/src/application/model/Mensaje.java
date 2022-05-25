package application.model;

public class Mensaje {
	public String cuerpo,dniremit,dnidest,asunto,nomremit;
	public int codigo,id_usuario_destinatario,id_usuario_remitente;
	
	
	

	public int getId_usuario_destinatario() {
		return id_usuario_destinatario;
	}
	public void setId_usuario_destinatario(int id_usuario_destinatario) {
		this.id_usuario_destinatario = id_usuario_destinatario;
	}
	public int getId_usuario_remitente() {
		return id_usuario_remitente;
	}
	public void setId_usuario_remitente(int id_usuario_remitente) {
		this.id_usuario_remitente = id_usuario_remitente;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getDniremit() {
		return dniremit;
	}
	public void setDniremit(String dniremit) {
		this.dniremit = dniremit;
	}
	public String getDnidest() {
		return dnidest;
	}
	public void setDnidest(String dnidest) {
		this.dnidest = dnidest;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}	

}
