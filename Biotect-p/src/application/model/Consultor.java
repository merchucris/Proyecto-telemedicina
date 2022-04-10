package application.model;

public class Consultor extends Persona {

	private String usuario; //dni
	private String password;


	public Consultor(String dni, String nombre, String apellidos, String correo, String password) {
		super(dni, nombre, apellidos, correo);
		this.password = password;
	}

	public String getUsuario() {
		return getDni();
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		String salida = "\n\t-usuario: " + usuario;
		salida += "\n\t-Contrase�a: " + password;
		return salida;
	}

}
