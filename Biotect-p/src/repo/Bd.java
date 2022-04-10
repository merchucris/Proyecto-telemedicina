package repo;

import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;

import java.util.List;

// Base de datos en JSON
public interface Bd {

    // Añadir un nuevo consultor
    boolean altaConsultor(Consultor consultor);
    
    //Añadir un nuevo medico
    boolean altaMedico(Medico medico);

    //Añadir un nuevo paciente
    boolean altaPaciente(Paciente paciente);

    // Recuperar un consultor por su dni
    Consultor recuperarConsultor(String dni);
    
    //Recuperar un medico por su dni
    Medico recuperarMedico(String dni);
    
    //Recuperar un paciente por su dni
    Paciente recuperarPaciente(String dni);

    // Recuperar todos los consultores
    List<Consultor> recuperarConsultores();

    // Recuperar todos los medicos
    List<Medico> recuperarMedicos();
    
    // Recuperar todos los pacientes
    List<Paciente> recuperarPacientes();

    // Modificar un consultor por su dni (El registro se sabe que existe)
    boolean modificarConsultor(String dni, Consultor consultor);
    
    // Modificar un medico por su dni (El registro se sabe que existe)
    boolean modificarMedico(String dni, Medico medico);
    
    // Modificar un paciente por su dni (El registro se sabe que existe)
    boolean modificarPaciente(String dni, Paciente paciente);
    
    // Eliminar un consultor por su dni
    boolean eliminarConsultor(String dni);
    
    // Eliminar un medico por su dni
    boolean eliminarMedico(String dni);
    
    // Eliminar un paciente por su dni
    boolean eliminarPaciente(String dni);
}
