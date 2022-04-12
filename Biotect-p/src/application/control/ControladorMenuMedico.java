package application.control;

import java.io.IOException;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.JsonBD;

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

	
    @FXML
    private JFXTextArea panelVisualizarM;

    @FXML
    private Button botonPerfilM;

    @FXML
    private Button botonConsultarPaciente;

    @FXML
    private Button botonVerDatosP;

    @FXML
    private Button Citas;

    @FXML
    private Button botonSalirM;

    @FXML
    void verPerfilM(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/PerfilMedico.fxml"));
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
    
    @FXML
    void verListaPacientes(ActionEvent event) {
   	     JsonBD ob = new JsonBD();
   	     List<Paciente> pacs = ob.recuperarPacientes();
   	     String cadenaPacientes = "";
   	     //List<String> pacCheck = Arrays.asList(medico.getPacientes());
   	    
   	     for (Paciente pac:pacs){
   	    	 
   		    System.out.print("PACIENTE DNI: " + pac.getDni());
   		    String [] pacCheck = pac.getMedicos();
   		    if (pacCheck.length>0) {
   		    System.out.print(pacCheck.length);
   		     for (String pacCh : pacCheck) {
    		    	System.out.print("MEDICOS PACIENTES ASOCIADOS: " + pacCh );
    			    if (pacCh.equalsIgnoreCase(medico.getDni())) {
    				    cadenaPacientes+="Nombre: " + pac.getNombre();
    				    cadenaPacientes+= "Apellido: "+ pac.getApellidos();
    				    cadenaPacientes += "DNI: "+ pac.getDni() ;
    				    cadenaPacientes += "\n";
    			    }
    		    }
   		    }
   	     }
		panelVisualizarM.setText(cadenaPacientes);
    }

    @FXML
    void visualizarDatos(ActionEvent event) {
      	 JsonBD ob = new JsonBD();
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
      	panelVisualizarM.setText(cadenaSensores);
    }
    
    @FXML
    void salirM(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaSalir.fxml"));
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