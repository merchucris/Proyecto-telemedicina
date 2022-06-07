package application.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorListaPacientesTodos {

    @FXML
    private Button botonVolverMenuM;

    @FXML
    private TextArea listaPacientes;

    @FXML
    private Button botonMostrarTodosPacientes;
    
	private Consultor consultor;
	private Paciente pac;
	private Medico med;
    private String tipo;
	public Paciente getPaciente() {
		return this.pac;
	}

	public void setPaciente(Paciente pac) {
		this.pac = pac;
	}
	
    public ControladorListaPacientesTodos(Medico med) {
    	this.med = med;
    	this.tipo = "M";
    }
    public ControladorListaPacientesTodos(Paciente pac) {
		this.pac = pac;
		this.tipo = "P";
    }
    public ControladorListaPacientesTodos(Consultor consultor) {
    	this.consultor =consultor;
		this.tipo = "C";	
    }
	

    @FXML
    void mostrarListaTodosPacientes(ActionEvent event) {
    	
  	     MariaBD ob = new MariaBD();
  	     List<Paciente> pacs = ob.recuperarPacientes();
  	     String cadenaPacientes = "";
  	     //List<String> medCheck = Arrays.asList(paciente.getMedicos());
     	 for (Paciente pac:pacs){
  	   	   System.out.print("LISTA PACIENTES: " + pac);
  		       cadenaPacientes+= "Nombre: "+ pac.getNombre();
  		       cadenaPacientes+="|| Apellido: " + pac.getApellidos();
  		       cadenaPacientes += "|| DNI: "+ pac.getDni();
  		       cadenaPacientes += "\n";
  	     }
     	listaPacientes.setText(cadenaPacientes);

    }

    @FXML
    void volverVentanaMenuM(ActionEvent event) {
    	try {
    		FXMLLoader loader = null; //Estoy haciendo una variable global para la función, local.
    	    if(this.tipo.equals("M")) {
    	    	// Vuelves al menu de MEDICOS
    	    	loader= new FXMLLoader(getClass().getResource("/application/view/MenuM.fxml"));
        		ControladorMenuMedico ControladorMenuMedic = new ControladorMenuMedico(med);
    			loader.setController(ControladorMenuMedic);
    	    	
    	    }else if(this.tipo.equals("P")) {
    	    	// PACIENTE
    	    	loader= new FXMLLoader(getClass().getResource("/application/view/MenuP2.fxml"));
    	    	ControladorMenuPaciente ControladorMenuPaciente = new ControladorMenuPaciente(pac);
    			loader.setController(ControladorMenuPaciente);
    			
    	    }else {
    	    	// CONSUTOR
    	    	loader= new FXMLLoader(getClass().getResource("/application/view/MenuC.fxml"));
    	    	ControladorMenuConsultor ControladorMenuConsult = new ControladorMenuConsultor(consultor);
    			loader.setController(ControladorMenuConsult);
    	    }
    	    Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonVolverMenuM.getScene().getWindow();
			myStage.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }

}
