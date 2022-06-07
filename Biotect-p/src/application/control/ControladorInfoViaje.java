package application.control;

import java.io.IOException;

import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorInfoViaje {
	
    @FXML
    private Button botonSalir;

    @FXML
    private Button BotonVolverAMenu;
	
    private Medico med;
    private Paciente pac;
    private Consultor cons;
    private String tipo;
    
    public ControladorInfoViaje(Medico med) {
    	this.med = med;
    	this.tipo = "M";
    }
    public ControladorInfoViaje(Paciente pac) {
		this.pac = pac;
		this.tipo = "P";
    }
    public ControladorInfoViaje(Consultor cons) {
		this.cons = cons;
		this.tipo = "C";	
    }

	@FXML
    void volverM(ActionEvent event) {
		
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
    	    	ControladorMenuConsultor ControladorMenuConsult = new ControladorMenuConsultor(cons);
    			loader.setController(ControladorMenuConsult);
    	    }
    	    Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.BotonVolverAMenu.getScene().getWindow();
			myStage.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	
    }
    
    @FXML
    void salir(ActionEvent event) {	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaSalir2.fxml"));
			ControladorSalir ControladorSalirMenu = new ControladorSalir();
			loader.setController(ControladorSalirMenu);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonSalir.getScene().getWindow();
			myStage.close();
    	    
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
            }

    }


}
