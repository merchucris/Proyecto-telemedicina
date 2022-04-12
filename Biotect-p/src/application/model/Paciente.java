package application.model;

public class Paciente extends Persona {
	
	private String usuario;
	private String password;
	private int edad;
	private String sexo;
	private String[] medicos = new String[5];
    
	public Paciente(String dni, String nombre, String apellidos, String correo, String password, int edad, String sexo, String[] medicos) {
		super(dni, nombre, apellidos, correo);
		this.password = password;
		System.out.print("Actualiza médicos" + this.medicos.length);
		
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
	
		this.sexo = sexo;
	}
	public String[] getMedicos() {
		return medicos;
	}
	public void setMedicos (String [] medicos) {
		System.out.print("Actualiza médicos" + medicos.length);
		this.medicos = medicos;
	}

	@Override
	public String toString() {
		String salida = "\n\t-usuario: " + usuario;
		salida += "\n\t-Contrase�a: " + password;
		return salida;
	}
}
