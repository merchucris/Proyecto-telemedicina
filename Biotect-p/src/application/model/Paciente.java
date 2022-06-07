package application.model;

import java.util.ArrayList;

public class Paciente extends Persona {
	
	private String usuario;
	private String fechaNac;
	private String sexo;
	private int id_paciente;
	
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
    
	public Paciente(int id_paciente, String dni, String nombre, String apellidos, String correo, String fechaNac, String sexo, String[] medicos) {
		super(dni, nombre, apellidos, correo);
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		System.out.print("Actualiza médicos" + this.medicos.size());
		this.id_paciente = id_paciente;
	}
	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}
	public String getUsuario() {
		return getDni();
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFechNac() {
		return fechaNac;
	}
	public void setEdad(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
	
		this.sexo = sexo;
	}
	public ArrayList<Medico> getMedicos() {
		return this.medicos;
	}
	public void setMedicos (ArrayList<Medico> medicos) {
		System.out.print("Actualiza médicos" + medicos.size());
		this.medicos = medicos;
	}

	@Override
	public String toString() {
		String salida = "\n\t-usuario: " + usuario;
		return salida;
	}
}
