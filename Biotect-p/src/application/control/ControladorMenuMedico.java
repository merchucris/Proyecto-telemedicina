package application.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jfoenix.controls.JFXTextArea;

import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;
import application.model.Sensor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorMenuMedico {

	private Medico medico;
	public ControladorMenuMedico(Medico medico) {
		this.medico  =medico;
	}
    public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	
    // @FXML
   // private JFXTextArea panelVisualizarM;

    @FXML
    private Button botonPerfilM;

    @FXML
    private Button botonConsultarPaciente;

    @FXML
    private Button botonVerDatosM;
    

    @FXML
    private Button botonvisualizarDatos;

    @FXML
    private Button botonPacientesM;

    @FXML
    private Button botonSalirM;

    @FXML
    private Button botonMensajes;

    @FXML
    private Button botonEnviarMensaje;
    
    @FXML
    private TextField botonDniPaciente;

    @FXML
    private TextField botonCorreoP;

    @FXML
    private TextField botonNombreP;

    @FXML
    private TextField botonSexoP;

    @FXML
    private TextField botonApellidoP;


    @FXML
    void verPerfilM(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/PerfilMedic.fxml"));
			ControladorPerfilM ControladorPerfilMedico = new ControladorPerfilM(getMedico());
			loader.setController(ControladorPerfilMedico);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonPerfilM.getScene().getWindow();
			myStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
//    @FXML
//    void verListaPacientes(ActionEvent event) {
//   	     MariaBD ob = new MariaBD();
//   	     List<Paciente> pacs = ob.recuperarPacientes();
//   	     String cadenaPacientes = "";
//   	     //List<String> pacCheck = Arrays.asList(medico.getPacientes());
//   	    
//   	     for (Paciente pac:pacs){
//   	    	 
//   		    System.out.print("PACIENTE DNI: " + pac.getDni());
//   		    ArrayList<Medico> pacCheck = pac.getMedicos();
//   		    if (pacCheck.size()>0) {
//   		    System.out.print(pacCheck.size());
//   		     for (Medico pacCh : pacCheck) {
//    		    	System.out.print("MEDICOS PACIENTES ASOCIADOS: " + pacCh );
//    			    if (pacCh.getDni().equalsIgnoreCase(medico.getDni())) {
//    				    cadenaPacientes+="Nombre: " + pac.getNombre();
//    				    cadenaPacientes+= "Apellido: "+ pac.getApellidos();
//    				    cadenaPacientes += "DNI: "+ pac.getDni() ;
//    				    cadenaPacientes += "\n";
//    			    }
//    		    }
//   		    }
//   	     }
		//panelVisualizarM.setText(cadenaPacientes);
//    }
    
    @FXML
    void buscarPaciente(ActionEvent event) {
    	
    	//COMO EN EL REGISTRO DEL PACIENTE
    	
    	
        MariaBD ob = new MariaBD();
        Paciente paciee = ob.recuperarPaciente(botonDniPaciente.getText());
    
         botonNombreP.setText(paciee.getNombre());
         botonApellidoP.setText(paciee.getApellidos());
         botonCorreoP.setText(paciee.getCorreo());
         //botonEdadP.setText(Integer.toString(paciee.getEdad()));
         System.out.println("SEXO " + paciee.getSexo());
         botonSexoP.setPromptText(paciee.getSexo());
         //botonSexoP.setText(paciee.getFechNac());


  }
    
    @FXML
    void verDatosPacientes(ActionEvent event) {
    	  try {
  			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VistaSensores3.fxml"));
  			ControladorSensoresM ControladorSensor = new ControladorSensoresM(getMedico());
  			//ControladorListaPacientes ControladorListaPacient = new ControladorListaPacientes(getMedico());
  			loader.setController(ControladorSensor);
  			Parent root = loader.load();
  			Stage stage = new Stage();
  			stage.setScene(new Scene(root));
  			stage.initModality(Modality.WINDOW_MODAL);
  			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
  			stage.show();
  			Stage myStage = (Stage) this.botonvisualizarDatos.getScene().getWindow();
  			myStage.close();
  			
  		  } catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

    }

    
    @FXML
    void consultarPaciente(ActionEvent event) {
  	     try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ListaMisPacientesM.fxml"));
			ControladorListaPacientesMed ControladorListaPacient = new ControladorListaPacientesMed(getMedico());
			//ControladorListaPacientes ControladorListaPacient = new ControladorListaPacientes(getMedico());
			loader.setController(ControladorListaPacient);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonConsultarPaciente.getScene().getWindow();
			myStage.close();
			
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    

    @FXML
    void verPacientesM(ActionEvent event) {
    	
    	MariaBD bbdd = new MariaBD();
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ListaPacientesTodos.fxml"));
			ControladorListaPacientesTodos ControladorListaPacC = new ControladorListaPacientesTodos(getMedico());
			loader.setController(ControladorListaPacC);
			Parent root;
			root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonPacientesM.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void verMensajes(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/vistaMensajeRecibidoMed.fxml"));
    		ControladorMensajeRecibidoMed ControladorMensajRecMed = new ControladorMensajeRecibidoMed(this.medico);
			loader.setController(ControladorMensajRecMed);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonMensajes.getScene().getWindow();
			myStage.close();
    	    
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	

    }
    
    @FXML
    void enviarMensaje(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/vistaEnviarMensaje.fxml"));
    		ControladorEnviarMensajeMed ControladorMensaj = new ControladorEnviarMensajeMed(this.medico);
			loader.setController(ControladorMensaj);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonMensajes.getScene().getWindow();
			myStage.close();
    	    
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    }

    @FXML
    void visualizarDatos(ActionEvent event) {
      	 MariaBD ob = new MariaBD();
      	 List<Sensor> senss = ob.recuperarSensores();
      	 List<Paciente> pacs = ob.recuperarPacientes();
      	 String cadenaSensores = "";
      	 //List<String> medCheck = Arrays.asList(paciente.getMedicos());
      	 for (Sensor sen:senss){
      		
      			System.out.print("SENSORES AL PACIENTE ASOCIADOS: " + sen);
      			for (Paciente pac : pacs) {
      				if (pac.getDni().equals(sen.getDni())) {
      					cadenaSensores+= "Nombre Paciente: " + pac.getNombre() + " " + pac.getApellidos();
      				}
      			}
      			
      			//cadenaSensores+= "Fecha: "+ sen.getmarcaDeTiempo();
      			cadenaSensores+="Glucemia: " + sen.getglucemia();
      			cadenaSensores += "Saturación: "+ sen.getsaturacion();
      			cadenaSensores += "Temperatura: "+ sen.gettemperatura();
      			cadenaSensores += "\n";
      		
      		
      	}
      	//panelVisualizarM.setText(cadenaSensores);
    }
    
    @FXML
    void salirM(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaSalir2.fxml"));
			ControladorSalir ControladorS = new ControladorSalir();
			loader.setController(ControladorS);
			Parent root;
			root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonSalirM.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}