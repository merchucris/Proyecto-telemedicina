package application.model;
public class Administrador extends Persona {

	private String usuario;
    private String password;

    public Administrador(String dni, String nombre, String apellidos, String correo, String password) {
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
		salida += "\n\t-Contraseña: " + password;
		return salida;
	}

}