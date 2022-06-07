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

public class ControladorListaPacientesMed {

    @FXML
    private Button botonVolverMenuM;

    @FXML
    private TextArea listaPacientes;

    @FXML
    private Button botonMostrarMisMedicos;
    
    
	private Medico medico;
	
	public ControladorListaPacientesMed(Medico medico) {
		this.medico  =medico;
	}

	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

    @FXML
    void mostrarListaMisPacientes(ActionEvent event) {
  	     MariaBD ob = new MariaBD();
  	     List<Paciente> pacs = ob.getPacienteMedico(medico);
  	     String cadenaPacientes = "";
  	     //List<String> pacCheck = Arrays.asList(medico.getPacientes());
  	    
  	     for (Paciente pac:pacs){
  	    	 
  		    System.out.print("PACIENTE DNI: " + pac.getDni());
  		    cadenaPacientes += "DNI Paciente: " + pac.getDni();
	  		  cadenaPacientes += "|| Nombre: " + pac.getNombre();
	  		
  	     }
  	     listaPacientes.setText(cadenaPacientes);

    }

    @FXML
    void volverVentanaMenuM(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuM.fxml"));
    		ControladorMenuMedico ControladorMenuMeds = new ControladorMenuMedico(medico);
			loader.setController(ControladorMenuMeds);
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
