package repo;

import application.model.Consultor;
import application.model.Medico;
import application.model.MensajeMedico;
import application.model.MensajePaciente;
import application.model.Paciente;
import application.model.Sensor;

import java.util.List;

// Base de datos en JSON
public interface Bd {

    // Añadir un nuevo consultor
    boolean altaConsultor(Consultor consultor, String password);
    
    //Añadir un nuevo medico
    boolean altaMedico(Medico medico, String password);

    //Añadir un nuevo paciente
    boolean altaPaciente(Paciente paciente, String password);
    
    // Añadir un nuevo sensor
    boolean altaSensor(Sensor sensor);
    
    // Recuperar un consultor por su dni
    Consultor recuperarConsultor(String dni);
    
    //Recuperar un medico por su dni
    Medico recuperarMedico(String dni);
    
    //Recuperar un paciente por su dni
    Paciente recuperarPaciente(String dni);
    
    //Recuperar un sensor por su dni
    Sensor recuperarSensor(String dni);

    // Recuperar todos los consultores
    List<Consultor> recuperarConsultores();

    // Recuperar todos los medicos
    List<Medico> recuperarMedicos();
    
    // Recuperar todos los pacientes
    List<Paciente> recuperarPacientes();
    
    // Recuperar todos los sensores
    List<Sensor> recuperarSensores();
    
    

    // Modificar un consultor por su dni (El registro se sabe que existe)
    boolean modificarConsultor(String dni, Consultor consultor);
    
    // Modificar un medico por su dni (El registro se sabe que existe)
    boolean modificarMedico(String dni, Medico medico);
    
    // Modificar un paciente por su dni (El registro se sabe que existe)
    boolean modificarPaciente(String dni, Paciente paciente);
    
    // Modificar un sensor por su dni (El registro se sabe que existe)
    boolean modificarSensor(String dni, Sensor sensor);
    
    // Eliminar un consultor por su dni
    boolean eliminarConsultor(String dni);
    
    // Eliminar un medico por su dni
    boolean eliminarMedico(String dni);
    
    // Eliminar un paciente por su dni
    boolean eliminarPaciente(String dni);
    
    // Eliminar un sensor por su dni
    boolean eliminarSensor(String dni);

    //Recuperar Mensaje Paciente por su dni
	MensajePaciente recuperarMensaje(int id_mensaje);

    //Recuperar Mensaje Medico por su dni
	MensajeMedico recuperarMensajeCon(int id_mensaje);



//	boolean altaMensaje(Mensaje id_mensaje);
}
