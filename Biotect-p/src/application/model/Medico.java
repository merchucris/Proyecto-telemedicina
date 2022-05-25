package application.model;

public class Medico extends Persona {
	
	private String usuario;
	private int id_medico;

	public int getId_medico() {
		return id_medico;
	}

	public void setId_medico(int id_medico) {
		this.id_medico = id_medico;
	}

	public Medico(int id_medico, String dni, String nombre, String apellidos, String correo) {
		super(dni, nombre, apellidos, correo);
		this.id_medico = id_medico;
	}

	public String getUsuario() {
		return getDni();
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		String salida = "\n\t-usuario: " + usuario;
		return salida;
	}



}
