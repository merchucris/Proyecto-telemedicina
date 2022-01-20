package application.model;

import java.sql.Timestamp;
import java.util.Date;

public class Persona {

	private String nombre;
	private String apellidos;
	private int ID;
	private int edad;

	public Timestamp timestamp;
	public Date dateTime;

	public Persona(String nombre, String apellidos, int iD, int edad) {
		super();
		this.nombre = nombre;
		this.setApellidos(apellidos);
		ID = iD;
		this.setEdad(edad);
		timestamp = new Timestamp(new Date().getTime());
		dateTime =  new java.util.Date();
	}

	@Override
	public String toString() {
	String salida = "ID [" + ID + "]";
	salida += "\n\t-Nombre: " + nombre;
	salida += "\n\t-Apellido: " + apellidos;
	salida += "\n\t-Edad: " + edad;
		return salida;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

}
