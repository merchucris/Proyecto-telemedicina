package repo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;
import application.model.Sensor;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

//Implementación del repositorio para almacenamiento en JSON
public class JsonBD implements Bd {

    // Ruta a los datos en JSON
    private static final String RUTA_BASE_BD = "/";

    // Fichero JSON de consultores
    private static final String FICHERO_BD_CONSULTORES = "consultores.json";
    
    //Fichero Json de medicos
    private static final String FICHERO_BD_MEDICOS = "medicos.json";
    
    //Fichero Json de pacientes
    private static final String FICHERO_BD_PACIENTES = "pacientes.json";
    
    //Fichero Json de sensores
    private static final String FICHERO_BD_SENSORES = "sensores.json";
    
    // Lista de consultores
    private List<Consultor> consultores;
    
    //Lista de medicos
    private List<Medico> medicos;

    //Lista de pacientes
    private List<Paciente> pacientes;
    
    //Lista de sensores
    private List<Sensor> sensores;

    // Conversor de archivos JSON en objetos Java y viceversa
    Gson gson = new Gson();

    //Constructor
    public JsonBD() {
        // Depurar
        System.out.println("Creando clase GSON de Google para persistencia en JSON...");

        // Creando clase GSON de Google para persistencia en JSON
        this.gson = new Gson();

        // Depurar
        System.out.println("Creada clase GSON de Google para persistencia en JSON -> OK");
        System.out.println("*****1");
        this.consultores = recuperarListaConsultores();
        this.medicos = recuperarListaMedicos();
        this.pacientes = recuperarListaPacientes();
        System.out.println("####### PACIENTES PRUEBAS ##########");
        for (Paciente pac : this.pacientes) {
        	System.out.println("##################");
        	System.out.print("PAciente" + pac.getNombre() + "TIENE MEDICOS");
        	String medicos[] = pac.getMedicos();
        	for (String med : medicos) {
        		System.out.print("Medico " + med);
        	}
        	System.out.println("########");
        }
        this.sensores = recuperarListaSensores();
        System.out.println("*****2");
    }

	// Modificar un consultor por su dni (recuperar consultores + borrar consultor + añadir consultor modificado)
	@Override
	public boolean modificarConsultor(String dni, Consultor consultor) {
        //Para depurar
        System.out.println("Recuperar consultores...");
        List<Consultor> consultores = recuperarConsultores();
        System.out.println("Eliminar consultor antes de añadir el consultor actualizado");
        consultores.remove(consultor);
        eliminarConsultor(dni);
        System.out.println("Añadir consultor...");
        consultores.add(consultor);
        //Para depurar
        System.out.println("Listo para guardar el consultor modificado");
        // Guardar los consultores
        guardarConsultores();
        //Para depurar
        System.out.println("Consultor modificado (guardado) [" + consultor.getDni() + "] OK");

		return false;
	}
	
	// Modificar un medico por su dni (recuperar medicos + borrar medico + añadir medico modificado)
	@Override
	public boolean modificarMedico(String dni, Medico medico) {
        //Para depurar
        System.out.println("Recuperar consultores...");
        List<Medico> medicos = recuperarMedicos();
        System.out.println("Eliminar consultor antes de añadir el consultor actualizado");
        medicos.remove(medico);
        eliminarConsultor(dni);
        System.out.println("Añadir consultor...");
        medicos.add(medico);
        //Para depurar
        System.out.println("Listo para guardar el consultor modificado");
        // Guardar los consultores
        guardarMedicos();
        //Para depurar
        System.out.println("Consultor modificado (guardado) [" + medico.getDni() + "] OK");

		return false;
	}
	
	// Modificar un paciente por su dni (recuperar pacientes + borrar paciente + añadir paciente modificado)
	@Override
	public boolean modificarPaciente(String dni, Paciente paciente) {
        //Para depurar
        System.out.println("Recuperar pacientes...");
        List<Paciente> pacientes = recuperarPacientes();
        System.out.println("Eliminar paciente antes de añadir el paciente actualizado");
        pacientes.remove(paciente);
        eliminarPaciente(dni);
        System.out.println("Añadir paciente...");
        pacientes.add(paciente);
        //Para depurar
        System.out.println("Listo para guardar el consultor modificado");
        // Guardar los pacinetes
        guardarPacientes();
        //Para depurar
        System.out.println("Consultor modificado (guardado) [" + paciente.getDni() + "] OK");

		return false;
	}
	
	// Modificar un sensor por su dni (recuperar sensores + borrar sensor + añadir sensor modificado)
	@Override
	public boolean modificarSensor(String dni, Sensor sensor) {
        //Para depurar
        System.out.println("Recuperar sensores...");
        List<Sensor> sensores = recuperarSensores();
        System.out.println("Eliminar consultor antes de añadir el sensor actualizado");
        sensores.remove(sensor);
        eliminarSensor(dni);
        System.out.println("Añadir sensor...");
        sensores.add(sensor);
        //Para depurar
        System.out.println("Listo para guardar el sensor modificado");
        // Guardar los sensores
        guardarSensores();
        //Para depurar
        System.out.println("Sensor modificado (guardado) [" + sensor.getDni() + "] OK");

		return false;
	}

	// Recuperar el listado de usuarios del JSON como objetos Java
    public List<Consultor> recuperarListaConsultores() {
        //Depurar
        System.out.println("Obteniendo listado de usuarios desde la persistencia JSON... 1");
        System.out.println(FICHERO_BD_CONSULTORES);

        String consultorJSON = recuperarJSONString(FICHERO_BD_CONSULTORES);
        final Type tipoListaConsultor = new TypeToken<List<Consultor>>(){}.getType();
        return gson.fromJson(consultorJSON, tipoListaConsultor);
    }
    
    // Recuperar el listado de usuarios del JSON como objetos Java
    private List<Medico> recuperarListaMedicos() {
        //Depurar
        System.out.println("Obteniendo listado de usuarios desde la persistencia JSON... 1");
        System.out.println(FICHERO_BD_MEDICOS);

        String medicoJSON = recuperarJSONString(FICHERO_BD_MEDICOS);
        final Type tipoListaMedico = new TypeToken<List<Medico>>(){}.getType();
        return gson.fromJson(medicoJSON, tipoListaMedico);
    }
    
    // Recuperar el listado de usuarios del JSON como objetos Java
    private List<Paciente> recuperarListaPacientes() {
        //Depurar
        System.out.println("Obteniendo listado de usuarios desde la persistencia JSON... 1");
        System.out.println(FICHERO_BD_PACIENTES);

        String pacienteJSON = recuperarJSONString(FICHERO_BD_PACIENTES);
        final Type tipoListaPaciente = new TypeToken<List<Paciente>>(){}.getType();
        return gson.fromJson(pacienteJSON, tipoListaPaciente);
    }
    
	// Recuperar el listado de usuarios del JSON como objetos Java
    public List<Sensor> recuperarListaSensores() {
        //Depurar
        System.out.println("Obteniendo listado de usuarios desde la persistencia JSON... 1");
        System.out.println(FICHERO_BD_SENSORES);

        String sensorJSON = recuperarJSONString(FICHERO_BD_SENSORES);
        final Type tipoListaSensor = new TypeToken<List<Sensor>>(){}.getType();
        return gson.fromJson(sensorJSON, tipoListaSensor);
    }

    // Recuperar el contenido de un fichero JSON del almacenamiento como String
    private String recuperarJSONString(String nombreFichero) {
        String dataString = null;
        try {
        	System.out.println("***"+nombreFichero+"***");
        	File archivo = new File (nombreFichero);
        	FileReader fr = new FileReader (archivo);
        	
        	BufferedReader reader = new BufferedReader(fr);
            StringBuilder strBuilder = new StringBuilder();
            String linea;
            System.out.println("***"+nombreFichero+"***2");
            while ((linea = reader.readLine()) != null) {
                strBuilder.append(linea);
            }
            System.out.println("***"+nombreFichero+"***3");
            reader.close();
            dataString = strBuilder.toString();
            System.out.println("***"+nombreFichero+"***4");
        } catch (IOException e) {
            System.out.println("Error al recuperar del fichero "+nombreFichero+" la lista de JSON: " + e.getMessage());
            //e.printStackTrace();
        }
        return dataString;
    }

    // Guardar el JSON de consultores
    private void guardarConsultores() {
        String json = gson.toJson(this.consultores);
        
        File file = new File(FICHERO_BD_CONSULTORES);
        guardarJSONFichero(json,file);
    }
    
    //Guardar el Json de medicos
    private void guardarMedicos() {
        String json = gson.toJson(this.medicos);
        File file = new File(FICHERO_BD_MEDICOS);
        guardarJSONFichero(json,file);
    }
    
    //Guardar el Json de pacientes
    private void guardarPacientes() {
        String json = gson.toJson(this.pacientes);
        File file = new File(FICHERO_BD_PACIENTES);
        guardarJSONFichero(json,file);
    }

    // Guardar el JSON de consultores
    private void guardarSensores() {
        String json = gson.toJson(this.sensores);
        
        File file = new File(FICHERO_BD_SENSORES);
        guardarJSONFichero(json,file);
    }

    //Escribir un JSON en el fichero especificado
    private void guardarJSONFichero(String json, File fichero) {
        try {
            FileWriter writer = new FileWriter(fichero);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar en JSON: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    // Recuperar un consultor por su dni
    private Consultor recuperarConsultorPorDni(String dni) {
        //Para depurar
        System.out.println("Buscando consultor [" + dni + "]...");

        if  (consultores != null) {
            for (Consultor consultorBusqueda : consultores) {
                if (consultorBusqueda.getDni().equals(dni)) {
                    System.out.println("Consultor encontrado [" + dni + "] -> OK");
                    return consultorBusqueda;
                }
            }
        } 
       	//Si no se ha encontrado el usuario
       	System.out.println("No se ha encontrado el consultor [" + dni + "]");
       	return null;

    }
    
    // Recuperar un medico por su dni
    private Medico recuperarMedicoPorDni(String dni) {
        //Para depurar
        System.out.println("Buscando medico [" + dni + "]...");

        if  (medicos != null) {
            for (Medico medicoBusqueda : medicos) {
                if (medicoBusqueda.getDni().equals(dni)) {
                    System.out.println("Medico encontrado [" + dni + "] -> OK");
                    return medicoBusqueda;
                }
            }
        } 
       	//Si no se ha encontrado el usuario
       	System.out.println("No se ha encontrado el medico [" + dni + "]");
       	return null;

    }
    
    // Recuperar un medico por su dni
    private Paciente recuperarPacientePorDni(String dni) {
        //Para depurar
        System.out.println("Buscando paciente [" + dni + "]...");

        if  (pacientes != null) {
            for (Paciente pacienteBusqueda : pacientes) {
                if (pacienteBusqueda.getDni().equals(dni)) {
                    System.out.println("Paciente encontrado [" + dni + "] -> OK");
                    return pacienteBusqueda;
                }
            }
        } 
       	//Si no se ha encontrado el usuario
       	System.out.println("No se ha encontrado el paciente [" + dni + "]");
       	return null;

    }
    
    // Recuperar un sensor por su dni
    private Sensor recuperarSensorPorDni(String dni) {
        //Para depurar
        System.out.println("Buscando sensor [" + dni + "]...");

        if  (sensores != null) {
            for (Sensor sensorBusqueda : sensores) {
                if (sensorBusqueda.getDni().equals(dni)) {
                    System.out.println("Sensor encontrado [" + dni + "] -> OK");
                    return sensorBusqueda;
                }
            }
        } 
       	//Si no se ha encontrado el usuario
       	System.out.println("No se ha encontrado el sensor [" + dni + "]");
       	return null;

    }
    
    // Añadir un nuevo consultor
    @Override
    public boolean altaConsultor(Consultor consultor) {
        // No debe ya haber un consultor
        if (recuperarConsultorPorDni(consultor.getDni()) != null) {
            //Para depurar
            System.out.println("El consultor [" + consultor.getDni() + "] ya existe...");

            return false;
        }

        //Para depurar
        System.out.println("Dando de alta consultor [" + consultor.getDni() + "]...");
        //Agregamos el usuario a la lista de objetos de usuarios Java
        consultores.add(consultor);
        //Para depurar
        System.out.println("Listo para dar de alta al consultor [" + consultor.getDni() + "]");
        // Guardar los consultores
        guardarConsultores();

        //Para depurar
        System.out.println("Consultor dado de alta (guardado) [" + consultor.getDni() + "] OK");

        return true;
    }

    // Añadir un nuevo medico
    @Override
    public boolean altaMedico(Medico medico) {
        // No debe ya haber un consultor
        if (recuperarMedicoPorDni(medico.getDni()) != null) {
            //Para depurar
            System.out.println("El consultor [" + medico.getDni() + "] ya existe...");

            return false;
        }

        //Para depurar
        System.out.println("Dando de alta medico [" + medico.getDni() + "]...");
        //Agregamos el usuario a la lista de objetos de usuarios Java
        medicos.add(medico);
        //Para depurar
        System.out.println("Listo para dar de alta al medico [" + medico.getDni() + "]");
        // Guardar los consultores
        guardarMedicos();

        //Para depurar
        System.out.println("Medico dado de alta (guardado) [" + medico.getDni() + "] OK");

        return true;
    }

    // Añadir un nuevo paciente
    @Override
    public boolean altaPaciente(Paciente paciente) {
        // No debe ya haber un consultor
        if (recuperarPacientePorDni(paciente.getDni()) != null) {
            //Para depurar
            System.out.println("El paciente [" + paciente.getDni() + "] ya existe...");

            return false;
        }

        //Para depurar
        System.out.println("Dando de alta paciente [" + paciente.getDni() + "]...");
        //Agregamos el usuario a la lista de objetos de usuarios Java
        pacientes.add(paciente);
        //Para depurar
        System.out.println("Listo para dar de alta al paciente [" + paciente.getDni() + "]");
        // Guardar los consultores
        guardarPacientes();

        //Para depurar
        System.out.println("Paciente dado de alta (guardado) [" + paciente.getDni() + "] OK");

        return true;
    }

    // Añadir un nuevo consultor
    @Override
    public boolean altaSensor(Sensor sensor) {
        // No debe ya haber un consultor
        if (recuperarSensorPorDni(sensor.getDni()) != null) {
            //Para depurar
            System.out.println("El sensores [" + sensor.getDni() + "] ya existe...");

            return false;
        }

        //Para depurar
        System.out.println("Dando de alta sensor [" + sensor.getDni() + "]...");
        //Agregamos el usuario a la lista de objetos de usuarios Java
        sensores.add(sensor);
        //Para depurar
        System.out.println("Listo para dar de alta al sensor [" + sensor.getDni() + "]");
        // Guardar los consultores
        guardarSensores();

        //Para depurar
        System.out.println("Consultor dado de alta (guardado) [" + sensor.getDni() + "] OK");

        return true;
    }
    
    // Recuperar un consultor por su dni
    @Override
    public Consultor recuperarConsultor(String dni) {
        return recuperarConsultorPorDni(dni);
    }
    
    // Recuperar un medico por su dni
    @Override
    public Medico recuperarMedico(String dni) {
        return recuperarMedicoPorDni(dni);
    }
    
    // Recuperar un paciente por su dni
    @Override
    public Paciente recuperarPaciente(String dni) {
        return recuperarPacientePorDni(dni);
    }
    
    // Recuperar un sensor por su dni
    @Override
    public Sensor recuperarSensor(String dni) {
        return recuperarSensorPorDni(dni);
    }

    // Recuperar todos los consultores
    @Override
    public List<Consultor> recuperarConsultores() {
        return consultores;
    }
    
    // Recuperar todos los consultores
    @Override
    public List<Medico> recuperarMedicos() {
        return medicos;
    }
    
    // Recuperar todos los consultores
    @Override
    public List<Paciente> recuperarPacientes() {
        return pacientes;
    }
    
    // Recuperar todos los sensores
    @Override
    public List<Sensor> recuperarSensores() {
        return sensores;
    }
    
    // Eliminar un consultor por su dni
    @Override
    public boolean eliminarConsultor(String dni) {
        //Para depurar
        System.out.println("Eliminando consultor [" + dni + "]...");

        Consultor consultor = recuperarConsultorPorDni(dni);
        if (consultor == null)
            return false;
        consultores.remove(consultor);

        //Para depurar
        System.out.println("Consultor eliminado [" + dni + "]");

        return true;
    }
    
    // Eliminar un medico por su dni
    @Override
    public boolean eliminarMedico(String dni) {
        //Para depurar
        System.out.println("Eliminando medico [" + dni + "]...");

        Medico medico = recuperarMedicoPorDni(dni);
        if (medico == null)
            return false;
        medicos.remove(medico);

        //Para depurar
        System.out.println("Medico eliminado [" + dni + "]");

        return true;
    }
    
    // Eliminar un paciente por su dni
    @Override
    public boolean eliminarPaciente(String dni) {
        //Para depurar
        System.out.println("Eliminando paciente [" + dni + "]...");

        Paciente paciente = recuperarPacientePorDni(dni);
        if (paciente == null)
            return false;
        pacientes.remove(paciente);

        //Para depurar
        System.out.println("Paciente eliminado [" + dni + "]");

        return true;
    }
    
    // Eliminar un sensor por su dni
    @Override
    public boolean eliminarSensor(String dni) {
        //Para depurar
        System.out.println("Eliminando consultor [" + dni + "]...");

        Sensor sensor = recuperarSensorPorDni(dni);
        if (sensor == null)
            return false;
        sensores.remove(sensor);

        //Para depurar
        System.out.println("Sensor eliminado [" + dni + "]");

        return true;
    }

}