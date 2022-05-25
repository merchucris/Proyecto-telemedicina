package application.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class ControladorListaMedicos {
	
    @FXML
    private TextArea listaMedicos;
	private Paciente paciente;
    private Button botonVolverListaM;

    //Constructor de paciente
    private Paciente pacnts;
	
    //Getters y setters
    public Paciente getPacs() {
		return this.pacnts;
	}
    
	public void setPacs(Paciente pacie) {
		this.pacnts = pacie;
	}
	
	public ControladorListaMedicos(Paciente pac) {
		this.paciente = pac;
	}
	
    @FXML
    private Button botonMostrarMedicos;

    @FXML
    void mostrarListaMedicos(ActionEvent event) {
    	System.out.println("###############Mostrarlere##################");
   	 MariaBD ob = new MariaBD();
   	 List<Medico> medcs = ob.recuperarMedicos();
   	 String cadenaMedicos = "";
   	 //List<String> medCheck = Arrays.asList(paciente.getMedicos());
   	 List <Medico> medCheck = ob.getMedicosPaciente(paciente);
   	 System.out.println("*******************");
   	 for (Medico med:medcs){
   		 System.out.println("MEDICO DNI: " + med.getDni());
   		for (Medico medCh : medCheck) {
   			System.out.print("PACIENTE MEDICOS ASOCIADOS: " + medCh );
   			if (medCh.getDni().equalsIgnoreCase(med.getDni())) {
   				 cadenaMedicos+="Nombre: " + med.getNombre();
   	    		 cadenaMedicos+= "Apellido: "+ med.getApellidos();
   	    		 cadenaMedicos += "DNI: "+ med.getDni() ;
   	    		 cadenaMedicos += "\n";
   			}
   		}
   	 }
   	 System.out.println("*******************");
   	 listaMedicos.setText(cadenaMedicos);
		
	}
	public Paciente getPaciente() {
    	System.out.print("Paciente en controlador Paciente" + this.paciente.getDni());
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
    @FXML
    void volverVentanaListaM(ActionEvent event) {
       	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuP2.fxml"));
    		ControladorMenuPaciente ControladorMenuPaciente = new ControladorMenuPaciente(this.pacnts);
			loader.setController(ControladorMenuPaciente);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonMostrarMedicos.getScene().getWindow();
			myStage.close();
    	    
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	

    }
    

}
