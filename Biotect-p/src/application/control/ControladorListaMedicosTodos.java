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
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorListaMedicosTodos {
	
	Consultor consultor;
	Paciente paciente;
	private Medico medico;
    private String tipo;
	
    public ControladorListaMedicosTodos(Medico medico) {
    	this.medico = medico;
    	this.tipo = "M";
    }
    public ControladorListaMedicosTodos(Paciente paciente) {
		this.paciente = paciente;
		this.tipo = "P";
    }
    public ControladorListaMedicosTodos(Consultor consultor) {
		this.consultor = consultor;
		this.tipo = "C";	
    }

	@FXML
    private Button botonVolverListaM;

    @FXML
    private TextArea listaMedicos;

    @FXML
    private Button botonMostrarMedicos;
    
	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	

    @FXML
    void mostrarListaMedicos(ActionEvent event) {
    	
 	     MariaBD ob = new MariaBD();
 	     List<Medico> meds = ob.recuperarMedicos();
 	     String cadenaMedicos = "";
 	     //List<String> pacCheck = Arrays.asList(medico.getPacientes());
 	    
 	     for (Medico med:meds){
  		    	System.out.print("LISTA MEDICOS PACIENTES ASOCIADOS: " + med );
  			    	cadenaMedicos+="Nombre: " + med.getNombre();
  			    	cadenaMedicos+= "|| Apellido: "+ med.getApellidos();
  			    	cadenaMedicos += "|| DNI: "+ med.getDni() ;
  			    	cadenaMedicos += "\n";
  		  }
      	listaMedicos.setText(cadenaMedicos);

    }

    @FXML
    void volverVentanaListaM(ActionEvent event) {

    	try {
    		FXMLLoader loader = null; //Estoy haciendo una variable global para la función, local.
    	    if(this.tipo.equals("M")) {
    	    	// Vuelves al menu de MEDICOS
    	    	loader= new FXMLLoader(getClass().getResource("/application/view/MenuM.fxml"));
        		ControladorMenuMedico ControladorMenuMedic = new ControladorMenuMedico(medico);
    			loader.setController(ControladorMenuMedic);
    	    	
    	    }else if(this.tipo.equals("P")) {
    	    	// PACIENTE
    	    	loader= new FXMLLoader(getClass().getResource("/application/view/MenuP2.fxml"));
    	    	ControladorMenuPaciente ControladorMenuPaciente = new ControladorMenuPaciente(paciente);
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
			Stage myStage = (Stage) this.botonVolverListaM.getScene().getWindow();
			myStage.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }

}
