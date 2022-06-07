package application.model;

public class Consultor extends Persona {

	private String usuario; //dni
	private int id_consultor;
	



	public Consultor(int id_consultor, String dni, String nombre, String apellidos, String correo) {
		super(dni, nombre, apellidos, correo);
		this.id_consultor = id_consultor;
	}

	public int getId_consultor() {
		return id_consultor;
	}

	public void setId_consultor(int id_consultor) {
		this.id_consultor = id_consultor;
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
