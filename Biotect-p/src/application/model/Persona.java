package application.model;

public class Persona {

	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;

	public Persona(String dni, String nombre, String apellidos, String correo) {
		super();
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setCorreo(correo);
	}

	@Override
	public String toString() {
	String salida = "-DNI: " + dni;
	salida += "\n\t-Nombre: " + nombre;
	salida += "\n\t-Apellidos: " + apellidos;
	salida += "\n\t-E-mail: " + correo;
		return salida;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
