package repo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import application.model.Consultor;
import application.model.FrecuenciaRespiratoria;
import application.model.Medico;
import application.model.MensajeMedico;
import application.model.MensajePaciente;
import application.model.Paciente;
import application.model.Saturacion;
import application.model.Sensor;
import application.model.Temperatura;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//Implementación del repositorio para almacenamiento en JSON
public class MariaBD implements Bd {

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
    
    // Mensajes al paciente
    private List<MensajePaciente> mensajes;
    
    //Mensaje al médico
    private List<MensajeMedico> mensajescon;
    
    private String BBDDName = "prbbiotect";
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    static ResultSet rs;
    static String sql;
    static final String USER = "prb_biotect";
    static final String PASS = "biotect123";
    // Conversor de archivos JSON en objetos Java y viceversa
    Gson gson = new Gson();
     
    
    public Connection getConexion() {
    	
    	String BBDDName = "prbbiotect";
	    Connection conn = null;

	    	//ResultSet rs;
		 System.out.println("Creando conexion a la BBDD.");
	        try {
	        	//PASO 1: Registro Mariadb
	        	Class.forName("org.mariadb.jdbc.Driver");
				//PASO 2: Abro la conexión
	        	conn = DriverManager.getConnection(
	                    "jdbc:mariadb://195.235.211.197/prbbiotect", USER, PASS);
	            System.out.println("Connectado a la Base de Datos..."); 
				
			} catch (ClassNotFoundException e1) {
				// Maneja los errores para JDBC
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// meManeja los errores para Class.forName
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return conn;
    }
    //Constructor
    public MariaBD() {
        // Depurar
    	//Hago la conexión con la base de datos
   
        // Creando clase GSON de Google para persistencia en JSON
        this.gson = new Gson();

        // Depurar
        System.out.println("Creada clase GSON de Google para persistencia en JSON -> OK");
        System.out.println("*****1");
        this.consultores = recuperarListaConsultores();
        this.medicos = recuperarListaMedicos();
        this.pacientes = recuperarListaPacientes();
        this.mensajes = recuperarListaMensajes();
        this.mensajescon = recuperarListaMensajesConsultor();
        //this.mensajes = recuperarListaMensajes();
//        System.out.println("####### PACIENTES PRUEBAS ##########");
//        for (Paciente pac : this.pacientes) {
//        	System.out.println("##################");
//        	System.out.print("PAciente" + pac.getNombre() + "TIENE MEDICOS");
//        	String medicos[] = pac.getMedicos();
//        	for (String med : medicos) {
//        		System.out.print("Medico " + med);
//        	}
//        	System.out.println("########");
//        }
//        this.sensores = recuperarListaSensores();
//        System.out.println("*****2");
    }

	// Modificar un consultor por su dni (recuperar consultores + borrar consultor + añadir consultor modificado)
	
    public String comprobarUsuario(String dni, String password) {
    	String tipo = "";
        Connection conn = this.getConexion();
        sql = "SELECT * FROM usuarios WHERE DNI_usuarios = '"+dni+"';";
        try {
			stmt  = conn.createStatement();
		     rs    = stmt.executeQuery(sql);	
		      
		    if (rs.next()) {
		    	System.out.println("Usuario encontrado");
		    	if (password.equalsIgnoreCase(rs.getString("PASSWORD"))) {
		    		
		    		System.out.print("LA CONTRASEÑA ES CORRECTA");
		   	     try {
				    //String sql = "SELECT * FROM usuarios WHERE "PACIENTE" ;"
		    		// Sabiendo el tipo de usuario
		    		// Leer de la tabla correspondiente
		    		// PACIENTE
		    		 tipo = rs.getString("TIPO");
		    		
		    	}catch(Error e1) {
		        }
		   	     
		       }
		    	
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	
    	return tipo;
    }
    
   
    
    @Override
	public boolean modificarConsultor(String dni, Consultor consultor) {
	   
	   Connection conn = this.getConexion();
		
       try {
        sql = "SELECT ID_consultor FROM consultor WHERE ID_consultor = '"+dni+"';";
		stmt  = conn.createStatement();
		rs    = stmt.executeQuery(sql);	
		sql = "UPDATE consultor WHERE dni = "+ dni;
			
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

//		//Para depurar
//        System.out.println("Recuperar consultores...");
//        List<Consultor> consultores = recuperarConsultores();
//        System.out.println("Eliminar consultor antes de añadir el consultor actualizado");
//        consultores.remove(consultor);
//        eliminarConsultor(dni);
//        System.out.println("Añadir consultor...");
//        consultores.add(consultor);
//        //Para depurar
//        System.out.println("Listo para guardar el consultor modificado");
//        // Guardar los consultores
//        guardarConsultores();
//        //Para depurar
//        System.out.println("Consultor modificado (guardado) [" + consultor.getDni() + "] OK");

    	
		return false;
	}
	
	// Modificar un medico por su dni (recuperar medicos + borrar medico + añadir medico modificado)
	@Override
	public boolean modificarMedico(String dni, Medico medico) {
		   Connection conn = this.getConexion();
			
	       try {
	        sql = "SELECT ID_medico FROM medico WHERE ID_medico = '"+dni+"';";
			stmt  = conn.createStatement();
			rs    = stmt.executeQuery(sql);	
			sql = "UPDATE consultor WHERE dni = "+ dni;
				
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Para depurar
//        System.out.println("Recuperar consultores...");
//        List<Medico> medicos = recuperarMedicos();
//        System.out.println("Eliminar consultor antes de añadir el consultor actualizado");
//        medicos.remove(medico);
//        eliminarConsultor(dni);
//        System.out.println("Añadir consultor...");
//        medicos.add(medico);
//        //Para depurar
//        System.out.println("Listo para guardar el consultor modificado");
//        // Guardar los consultores
//        //guardarMedicos();
//        //Para depurar
//        System.out.println("Consultor modificado (guardado) [" + medico.getDni() + "] OK");

		return false;
	}
	
	// Modificar un paciente por su dni (recuperar pacientes + borrar paciente + añadir paciente modificado)
	@Override
	public boolean modificarPaciente(String dni, Paciente paciente) {
		   Connection conn = this.getConexion();
			
	       try {
	    	   
//	    	 // Guardar en la tabla Usuarios
//	     	 String SQL_INSERT= "INSERT INTO usuario (DNI, PASSWORD)"
//	                   +  " values('" + dni.getDni()+"','"+ password +"');";
//	     	 stmt = conn.createStatement();
//	   	     stmt.executeUpdate(SQL_INSERT);
//	   	     // Guardar en la tabla consultores
//	     	  SQL_INSERT= "INSERT INTO consultores (DNI, NOMBRE, APELLIDO, CORREO)"
//	  	                +  " values('"+ .getDni()+"','"+cons.getNombre()+"','"+cons.getApellidos()+"','"+cons.getCorreo()+"');";
//	     	 stmt.executeUpdate(SQL_INSERT);
	     	  
	        sql = "SELECT ID_paciente FROM paciente WHERE ID_paciente = '"+dni+"';";
			stmt  = conn.createStatement();
			rs    = stmt.executeQuery(sql);	
			sql = "UPDATE paciente WHERE dni = "+ dni;
				
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
       // guardarPacientes(paciente,password);
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
    	 Connection conn = this.getConexion();
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	     List<Consultor> listaConsultores = new ArrayList<>();
	     try {
		     String sql = "SELECT * FROM consultor;" ;
	         stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			while ( rs.next() ) {
				int id_consultor = rs.getInt("ID_consultor");
				String dni = rs.getString("DNI_consultor"); //getInt(0);
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDOS");
				String correo = rs.getString("CORREO");
				// String password = rs.getString("PASSWORD");
				Consultor cons = new Consultor(id_consultor, dni,nombre, apellidos,correo);
				
				listaConsultores.add(cons);
			}
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return listaConsultores;
    }
    
    // Recuperar el listado de usuarios del JSON como objetos Java
    private List<Medico> recuperarListaMedicos() {
    	 Connection conn = this.getConexion();
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	     List<Medico> listaMedicos =new ArrayList<>();
	     try {
		     String sql = "SELECT * FROM medico;" ;
	         stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			while ( rs.next() ) {
				int id_medico = rs.getInt("ID_medico");
				String dni = rs.getString("DNI_medico"); //getInt(0);
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDO");
				String correo = rs.getString("CORREO");
			
				Medico meds = new Medico(id_medico,dni, nombre, apellidos, correo );
				
				listaMedicos.add(meds);
			}
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
	    	return listaMedicos;
    }
    
    // Recuperar el listado de usuarios de la base de datos como objetos Java
    private List<Paciente> recuperarListaPacientes() {
   	     Connection conn = this.getConexion();
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	     List<Paciente> listaPacientes =new ArrayList<>();
	     try {
		     String sql = "SELECT * FROM paciente;" ;
	         stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql );
			// SELECT * from pacientemedico where ID_paciente = 1 INNER JOIN (medicos) medicos.ID_medico = pacientesmedico.id_medico ;
			
			/**
			 * while ( rs.next() ) {
			 * 	 print (rs.getString("ID_Medico"))
			 * 	       // 1
			 *         // 2
			 * }
			 */
			while ( rs.next() ) {
				int id_paciente = rs.getInt("ID_paciente");
				String dni = rs.getString("DNI_paciente"); //getInt(0);
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDO");
				String correo = rs.getString("CORREO");
				String fechNac = rs.getString("FECHA_NACIMIENTO");
				//int edad = rs.getInt("FECHA_NACIMIENTO");
				String sexo = rs.getString("SEXO");
				System.out.println("SEXO SE almacena "  + sexo);
				//String[] medicos = rs.getArray("MEDICOA1 + MEDICOA2 + MEDICOA3 + MEDICOA4");
				String [] medicos = new String [5];
				Paciente pac = new Paciente(id_paciente,dni, nombre, apellidos, correo, fechNac, sexo, medicos);
				
				listaPacientes.add(pac);
			}
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     return listaPacientes;
    }
    
    // Recuperar el listado de mensajes del paciente de la base de datos como objetos Java
    public List<MensajePaciente> recuperarListaMensajes() {
  	     Connection conn = this.getConexion();
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	     List<MensajePaciente> listaMensajes =new ArrayList<>();
	     try {
		     String sql = "SELECT * FROM mensajepaciente;";
	         stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql );
			// SELECT * from pacientemedico where ID_paciente = 1 INNER JOIN (medicos) medicos.ID_medico = pacientesmedico.id_medico ;

			while ( rs.next() ) {
				int id_mensaje = rs.getInt("ID_mensajeP");
				int id_paciente_receptor = rs.getInt("ID_paciente");
				int id_medico_remitente = rs.getInt("ID_medico");
				String asunto = rs.getString("Asunto");
				String mensaje = rs.getString("Mensaje");				
				Timestamp marcaDeTiempo = rs.getTimestamp("Time_stamp");
				MensajePaciente men = new  MensajePaciente(id_mensaje , id_paciente_receptor, marcaDeTiempo, id_medico_remitente, asunto, 
					       mensaje);
				
				listaMensajes.add(men);
			}
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     return listaMensajes;
    }
    
    // Recuperar el listado de mensajes del paciente de la base de datos como objetos Java
    public List<MensajeMedico> recuperarListaMensajesConsultor() {
  	     Connection conn = this.getConexion();
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	     List<MensajeMedico> listaMensajesC =new ArrayList<>();
	     try {
		     String sql = "SELECT * FROM mensajemedico" ;
	         stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql );
			// SELECT * from pacientemedico where ID_paciente = 1 INNER JOIN (medicos) medicos.ID_medico = pacientesmedico.id_medico ;
			
			/**
			 * while ( rs.next() ) {
			 * 	 print (rs.getString("ID_Medico"))
			 * 	       // 1
			 *         // 2
			 * }
			 */
			while ( rs.next() ) {
				int id_mensaje = rs.getInt("ID_mensajeM");
				int id_medico_receptor = rs.getInt("ID_medico");
				int id_consultor_remitente = rs.getInt("ID_consultor");
				String asunto = rs.getString("Asunto");
				String mensaje = rs.getString("Mensaje");				
				Timestamp marcaDeTiempo = rs.getTimestamp("Time_stamp");
				MensajeMedico menc = new  MensajeMedico (id_mensaje, id_medico_receptor, marcaDeTiempo, id_consultor_remitente, asunto, 
					       mensaje);
				
				listaMensajesC.add(menc);
			}
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     return listaMensajesC;
    }
    // Recuperar el listado de médicos de la base de datos como objetos Java
    public List<Medico> getMedicosPaciente(Paciente pac) {
  	     Connection conn = this.getConexion();
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	     List<Medico> listaMedicos =new ArrayList<>();
	     try {
		     String sql = "SELECT * FROM pacientemedico INNER JOIN medico ON pacientemedico.ID_medico = medico.ID_medico"
		     		+ " where pacientemedico.ID_paciente = '" +pac.getId_paciente() + "';" ;
	         stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql );
			while ( rs.next() ) {
				System.out.println("MEDICO DEL PACIENTE " + rs.getInt("ID_medico"));
				System.out.println("DNI DEL Medico " + rs.getString("DNI_medico"));
				int ID_medico = rs.getInt("ID_medico");
				String dni = rs.getString("DNI_medico");
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDO");
				String correo = rs.getString("CORREO");
				//int id_medico, String dni, String nombre, String apellidos, String correo
				Medico med = new Medico(ID_medico, dni, nombre, apellidos, correo);
				listaMedicos.add(med);
			//listamedicos.add
			// Hacer push a la lista con el nuevo medico
			}
	     
	     
	     }
	     catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     //Return lista de medicos
	     return listaMedicos;
    }
    
    public List<Paciente> getPacienteMedico(Medico med) {
 	     Connection conn = this.getConexion();
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	     List<Paciente> listaPacientes =new ArrayList<>();
	     try {
	    	 System.out.println("ID MEDICO " + med.getId_medico());
		     String sql = "SELECT * FROM pacientemedico INNER JOIN paciente ON pacientemedico.ID_paciente = paciente.ID_paciente"
		     		+ " where pacientemedico.ID_medico = '" +med.getId_medico() + "';" ;
	         stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql );
			while ( rs.next() ) {
				
				int id_paciente = rs.getInt("ID_paciente");
				System.out.println("PACIENTES " +id_paciente);
				String dni = rs.getString("DNI_paciente"); //getInt(0);
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDO");
				String correo = rs.getString("CORREO");
				String fechNac = rs.getString("FECHA_NACIMIENTO");
				//int edad = rs.getInt("FECHA_NACIMIENTO");
				String sexo = rs.getString("SEXO");
				//String[] medicos = rs.getArray("MEDICOA1 + MEDICOA2 + MEDICOA3 + MEDICOA4");
				String [] medicos = new String [5];
				Paciente pac = new Paciente(id_paciente,dni, nombre, apellidos, correo, fechNac, sexo, medicos);
				listaPacientes.add(pac);
			//listamedicos.add
			// Hacer push a la lista con el nuevo medico
			}
	     
	     
	     }
	     catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     //Return lista de medicos
	     return listaPacientes;
   }
    
//    public List<MensajePaciente> getMensajesPaciente(Paciente pac) {
// 	     Connection conn = this.getConexion();
//	     Statement stmt = null;
//	     PreparedStatement pstmt = null;
//	     List<MensajePaciente> listaMensajesPac =new ArrayList<>();
//	     try {
//		     String sql = "SELECT * FROM mensajepaciente INNER JOIN paciente ON mensajepaciente.ID_paciente = paciente.ID_paciente"
//		     		+ " where mensajepaciente.ID_paciente = '" +pac.getId_paciente() + "';" ;
//	         stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql );
//			while ( rs.next() ) {
//				System.out.println("MEDICO DEL PACIENTE " + rs.getInt("ID_medico"));
//				System.out.println("DNI DEL Medico " + rs.getString("DNI_medico"));
//				int id_mensaje = rs.getInt("ID_mensajeP");
//				int id_paciente_receptor = rs.getInt("ID_paciente");
//				int id_medico_remitente = rs.getInt("ID_medico");
//				String asunto = rs.getString("Asunto");
//				String mensaje = rs.getString("Mensaje");				
//				Timestamp marcaDeTiempo = rs.getTimestamp("Time_stamp");
//				MensajePaciente menP = new  MensajePaciente(id_mensaje , id_paciente_receptor, marcaDeTiempo, id_medico_remitente, asunto, 
//					       mensaje);
//				listaMensajesPac.add(menP);
//
//			}
//	     }
//	     catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	     
//	     return listaMensajesPac;
//   }
    
	// Recuperar el listado de usuarios del JSON como objetos Java
    public List<Sensor> recuperarListaSensores() {
  	     Connection conn = this.getConexion();
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	     List<Sensor> listaSensores =new ArrayList<>();
	     try {
		     String sql = "SELECT * FROM sensor;" ;
	         stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql );
		
			while ( rs.next() ) {
				String dni = rs.getString(""); //getInt(0);
				String marcaDeTiempo = rs.getString("");
				float temperatura = rs.getFloat("");
				int glucemia = rs.getInt("");
				int saturación = rs.getInt("");
				Sensor sens = new Sensor(dni, marcaDeTiempo, temperatura, glucemia, saturación);
				
				listaSensores.add(sens);
			}
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     return listaSensores;
    }
    
    //SENSOR DE TEMPERATURA
    public List<Temperatura> recuperarListaTemperatura(String dniPaciente) {
        Connection conn = this.getConexion();
          Statement stmt = null;
          PreparedStatement pstmt = null;
          List<Temperatura> listaTemperaturas =new ArrayList<>();
          try {
              String sql = "SELECT * FROM temperatura WHERE Dni_paciente = '"+dniPaciente+"';" ;
              stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
         
             while ( rs.next() ) {
                 int id_temperatura = rs.getInt("ID_temperatura");
                 String dni = rs.getString("DNI_paciente"); //getInt(0);
                 float temperatura = rs.getFloat("Temperatura");
                 String marcaDeTiempo = rs.getString("Time_Stamp");

                 Temperatura temps = new Temperatura(id_temperatura,dni, marcaDeTiempo, temperatura);
                 
                 listaTemperaturas.add(temps);
             }
          }catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
          
             return listaTemperaturas;
     }
    
    //SENSOR DE FRECUENCIA RESPIRATORIA
    public List<FrecuenciaRespiratoria> recuperarListaFrecRepiatoria(String dniPaciente) {
          Connection conn = this.getConexion();
          Statement stmt = null;
          PreparedStatement pstmt = null;
          List<FrecuenciaRespiratoria> listaFrecuenciaRespiratoria =new ArrayList<>();
          try {
              String sql = "SELECT * FROM frecuenciarespiratoria WHERE Dni_paciente = '"+dniPaciente+"';" ;
              stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
         
             while ( rs.next() ) {
                 int id_frecRespirato = rs.getInt("ID_frecuencia");
                 String dni = rs.getString("DNI_paciente"); //getInt(0);
                 float frec_respi = rs.getFloat("Frec_respi");
                 String marcaDeTiempo = rs.getString("Time_Stamp");

                 FrecuenciaRespiratoria frec = new FrecuenciaRespiratoria(id_frecRespirato,dni, marcaDeTiempo, frec_respi);
                 
                 listaFrecuenciaRespiratoria.add(frec);
             }
          }catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
          
             return listaFrecuenciaRespiratoria;
     }
    //SENSOR DE SATURACIÓN
    public List<Saturacion> recuperarListaSaturacion(String dniPaciente) {
          Connection conn = this.getConexion();
          Statement stmt = null;
          PreparedStatement pstmt = null;
          List<Saturacion> listaSaturacion =new ArrayList<>();
          try {
              String sql = "SELECT * FROM oxigeno WHERE Dni_paciente = '"+dniPaciente+"';" ;
              stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
         
             while ( rs.next() ) {
                 int id_saturacion = rs.getInt("ID_oxigeno");
                 String dni = rs.getString("DNI_paciente"); //getInt(0);
                 float saturacion = rs.getFloat("Saturacion");
                 String marcaDeTiempo = rs.getString("Time_Stamp");

                 Saturacion satu = new Saturacion(id_saturacion,dni, marcaDeTiempo, saturacion);
                 
                 listaSaturacion.add(satu);
             }
          }catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
          
             return listaSaturacion;
     }


    // BORRAR, CONFIRMAR
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
    private void guardarConsultores(Consultor cons, String password) {
    	
   	     Connection conn = this.getConexion();
   	     try {
   	    	// 1 -tabla usuarios 
   	    	// DNI PK
   	    	// 2 - tabla consultores con DNI de usuarios
   	    	// DNI FK
   	    	 
   	      // Guardar en la tabla Usuarios
   	     String SQL_INSERT= "INSERT INTO usuarios (DNI_usuarios, TIPO, PASSWORD)"
                 +  " values('" + cons.getDni()+"','"+ "CONSULTOR"+ "','"+ password +"');";
   	     stmt = conn.createStatement();
 	     stmt.executeUpdate(SQL_INSERT);
 	     // Guardar en la tabla consultores
   	     SQL_INSERT= "INSERT INTO consultor (DNI_consultor, NOMBRE, APELLIDO, CORREO)"
	                       +  " values('"+ cons.getDni()+"','"+cons.getNombre()+"','"+cons.getApellidos()+"','"+cons.getCorreo()+"','"+password+"');";
   	     stmt.executeUpdate(SQL_INSERT);
   	  
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
//        String json = gson.toJson(this.consultores);
//        
//        File file = new File(FICHERO_BD_CONSULTORES);
//        guardarJSONFichero(json,file);
    }
//    
//    //Guardar el Json de medicos
    private void guardarMedico(Medico med, String password) {
    	Connection conn = this.getConexion();
  	     try {
  	    	// 1 -tabla usuarios 
  	    	// DNI PK
  	    	// 2 - tabla consultores con DNI de usuarios
  	    	// DNI FK
  	    	 
  	      // Guardar en la tabla Usuarios
  	     String SQL_INSERT= "INSERT INTO usuarios (DNI_usuarios, TIPO, PASSWORD)"
                +  " values('" + med.getDni()+"','"+ "MEDICO"+ "','"+ password  +"');";
  	     stmt = conn.createStatement();
	     stmt.executeUpdate(SQL_INSERT);
	     // Guardar en la tabla consultores
  	     SQL_INSERT= "INSERT INTO medico (DNI_medico, NOMBRE, APELLIDO, CORREO)"
	                       +  " values('"+ med.getDni()+"','"+med.getNombre()+"','"+med.getApellidos()+"','"+med.getCorreo()+"');";
  	     stmt.executeUpdate(SQL_INSERT);
  	  
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }
    
    //Guardar la base de datos de pacientes
    private void guardarPacientes(Paciente pac, String password) {
    	Connection conn = this.getConexion();
 	     try {
 	    	// 1 -tabla usuarios 
 	    	// DNI PK
 	    	// 2 - tabla consultores con DNI de usuarios
 	    	// DNI FK
 	    	 
 	      // Guardar en la tabla Usuarios
 	     String SQL_INSERT= "INSERT INTO usuarios (DNI_usuarios, TIPO, PASSWORD)"
               +  " values('" + pac.getDni()+"','"+ "PACIENTE"+ "','"+ password  +"');";
 	     stmt = conn.createStatement();
	     stmt.executeUpdate(SQL_INSERT);
	     // Guardar en la tabla consultores
 	     SQL_INSERT= "INSERT INTO paciente (DNI_paciente, NOMBRE, APELLIDO, CORREO, FECHA_NACIMIENTO, SEXO)"
	                       +  " values('"+ pac.getDni()+"','"+pac.getNombre()+"','"+pac.getApellidos()+"','"+pac.getCorreo()+"','"+pac.getFechNac()+"','"+pac.getSexo()+"');";
 	     stmt.executeUpdate(SQL_INSERT);
 	  
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }

    // Guardar la base de datos de consultores
    private void guardarSensores() {
        String json = gson.toJson(this.sensores);
        
        File file = new File(FICHERO_BD_SENSORES);
        guardarJSONFichero(json,file);
    }
    
//    //Guardar la base de datos de pacientes
//    private void guardarMensajes() {
//    	Connection conn = this.getConexion();
// 	     try {
// 	    	// 1 -tabla usuarios 
// 	    	// DNI PK
// 	    	// 2 - tabla consultores con DNI de usuarios
// 	    	// DNI FK
// 	    	 
// 	      // Guardar en la tabla Usuarios
// 	     String SQL_INSERT= "INSERT INTO usuarios (DNI_usuarios, TIPO, PASSWORD)"
//               +  " values('" + pac.getDni()+"','"+ "PACIENTE"+ "','"+ password  +"');";
// 	     stmt = conn.createStatement();
//	     stmt.executeUpdate(SQL_INSERT);
//	     // Guardar en la tabla consultores
// 	     SQL_INSERT= "INSERT INTO paciente (DNI_paciente, NOMBRE, APELLIDO, CORREO, FECHA_NACIMIENTO, SEXO)"
//	                       +  " values('"+ pac.getDni()+"','"+pac.getNombre()+"','"+pac.getApellidos()+"','"+pac.getCorreo()+"','"+pac.getFechNac()+"','"+pac.getSexo()+"');";
// 	     stmt.executeUpdate(SQL_INSERT);
// 	  
//	     }catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//		}
//    }

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
  
    
    // Recuperar un medico por su dni
//    private Medico recuperarMedicoPorDni(String dni) {
//        //Para depurar
//        System.out.println("Buscando medico [" + dni + "]...");
//
//        if  (medicos != null) {
//            for (Medico medicoBusqueda : medicos) {
//                if (medicoBusqueda.getDni().equals(dni)) {
//                    System.out.println("Medico encontrado [" + dni + "] -> OK");
//                    return medicoBusqueda;
//                }
//            }
//        } 
//       	//Si no se ha encontrado el usuario
//       	System.out.println("No se ha encontrado el medico [" + dni + "]");
//       	return null;
//
//    }
    
    // Recuperar un medico por su dni
//    private Paciente recuperarPacientePorDni(String dni) {
//        //Para depurar
//        System.out.println("Buscando paciente [" + dni + "]...");
//
//        if  (pacientes != null) {
//            for (Paciente pacienteBusqueda : pacientes) {
//                if (pacienteBusqueda.getDni().equals(dni)) {
//                    System.out.println("Paciente encontrado [" + dni + "] -> OK");
//                    return pacienteBusqueda;
//                }
//            }
//        } 
//       	//Si no se ha encontrado el usuario
//       	System.out.println("No se ha encontrado el paciente [" + dni + "]");
//       	return null;
//
//    }
    
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
    public boolean altaConsultor(Consultor consultor, String password) {
    	
    	
        // No debe ya haber un consultor
        if (recuperarConsultor(consultor.getDni()) != null) {
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
        guardarConsultores(consultor, password);

        //Para depurar
        System.out.println("Consultor dado de alta (guardado) [" + consultor.getDni() + "] OK");

        return true;
    }

    // Añadir un nuevo medico
    @Override
    public boolean altaMedico(Medico medico, String password) {
        // No debe ya haber un consultor
        if (recuperarMedico(medico.getDni()) != null) {
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
        this.guardarMedico(medico, password);

        //Para depurar
        System.out.println("Medico dado de alta (guardado) [" + medico.getDni() + "] OK");

        return true;
    }

    // Añadir un nuevo paciente
    @Override
    public boolean altaPaciente(Paciente paciente, String password) {
        // No debe ya haber un consultor
        if (recuperarPaciente(paciente.getDni()) != null) {
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
        guardarPacientes(paciente, password);

        //Para depurar
        System.out.println("Paciente dado de alta (guardado) [" + paciente.getDni() + "] OK");

        return true;
    }

    // Añadir un nuevo sensor
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
    
    
    //Añadir un nuevo mensaje al paciente
    public boolean altaMensaje(MensajePaciente mensaje) {
        if (recuperarMensaje(mensaje.getId_Mensaje()) != null) {
            return false;
        }

        //Para depurar
        System.out.println("Dando de alte mensaje [" + mensaje.getId_Mensaje() + "]...");
        //Agregamos el usuario a la lista de objetos de usuarios Java
        this.mensajes.add(mensaje);
        //Para depurar
      
        // Guardar los mensajes
    	Connection conn = this.getConexion();
	     try {
	     String SQL_INSERT= "INSERT INTO mensaje (ID_mensajeP, ID_paciente, ID_medico, Asunto, Mensaje)"
	                       +  " values('"+ mensaje.getId_Mensaje()+"','"+mensaje.id_paciente_receptor+"','"
	    		           +  mensaje.id_medico_remitente+"','"+mensaje.getAsunto()+"','"+mensaje.getMensaje()+"');";
	     stmt = conn.createStatement();
	     stmt.executeUpdate(SQL_INSERT);
	     System.out.println("Mensaje dado de alta correctamente");
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
        
        return true;
    }
    
    //Añadir un nuevo mensaje al médico
    public boolean altaMensajeAlMedico(MensajeMedico mensajescon) {
        if (recuperarMensaje(mensajescon.getId_Mensaje()) != null) {
            return false;
        }

        //Para depurar
        System.out.println("Dando de alte mensaje [" + mensajescon.getId_Mensaje() + "]...");
        //Agregamos el usuario a la lista de objetos de usuarios Java
        this.mensajescon.add(mensajescon);
        //Para depurar
      
        // Guardar los mensajes
    	Connection conn = this.getConexion();
	     try {
	     String SQL_INSERT= "INSERT INTO mensajemedico (ID_mensajeM, ID_medico, ID_consultor, Asunto, Mensaje)"
	                       +  " values('"+ mensajescon.getId_Mensaje()+"','"+ mensajescon.id_medico_receptor+"','"
	    		           +  mensajescon.id_consultor_remitente+"','"+ mensajescon.getAsunto()+"','"+ mensajescon.getMensajeCon()+"');";
	     stmt = conn.createStatement();
	     stmt.executeUpdate(SQL_INSERT);
	     System.out.println("Mensaje dado de alta correctamente");
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
        
        return true;
    }
    // Recuperar un consultor por su dni
    @Override
    public Consultor recuperarConsultor(String dni) {
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
    @Override
    public Medico recuperarMedico(String dni) {
  	  System.out.println("Buscando medicos [" + dni + "]...");

      if  (dni != null) {
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
    
    // Recuperar un paciente por su dni
    @Override
    public Paciente recuperarPaciente(String dni) {
    	 System.out.println("Buscando paciente [" + dni + "]...");

         if  (dni != null) {
             for (Paciente pacienteBusqueda : pacientes) {
            	 System.out.println("Paciente [" + pacienteBusqueda.getDni() + "] -> OK");
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
    
    // Recuperar un paciente por su dni
//    @Override
//    public Mensaje recuperarMensaje(int id_mensaje) {
//    	 System.out.println("Buscando mensaje [" + id_mensaje + "]...");
//
//         if  (id_mensaje != null) {
//             for (Mensaje mensajeBusqueda : mensajes) {
//            	 System.out.println("Mensaje [" + mensajeBusqueda.getId_Mensaje() + "] -> OK");
//                 if (mensajeBusqueda.getId_Mensaje()).equals(id_mensaje)) {
//                     System.out.println("Mensaje encontrado [" + id_mensaje + "] -> OK");
//                     
//                     return mensajeBusqueda;
//                 }
//             }
//         } 
//        	//Si no se ha encontrado el usuario
//        	System.out.println("No se ha encontrado el paciente [" + id_mensaje + "]");
//        	return null;
//    }
    
    // Recuperar un sensor por su dni
    @Override
    public Sensor recuperarSensor(String dni) {
        return recuperarSensorPorDni(dni);
    }
       
    // Recuperar un mensaje por su dni
	@Override
	public MensajePaciente recuperarMensaje(int id_mensaje) {
		// TODO Auto-generated method stub
		return null;
	}
	
    // Recuperar un mensaje por su dni
	@Override
	public MensajeMedico recuperarMensajeCon(int id_mensaje) {
		// TODO Auto-generated method stub
		return null;
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

        Consultor consultor = recuperarConsultor(dni);
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

        Medico medico = recuperarMedico(dni);
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

        Paciente paciente = recuperarPaciente(dni);
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