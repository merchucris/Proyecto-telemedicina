package application.control;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.jfoenix.controls.JFXTextArea;

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
import repo.JsonBD;

public class ControladorMenuPaciente {
    @FXML
    private JFXTextArea panelVisualizarP;
	private Paciente paciente;
	public ControladorMenuPaciente(Paciente pac) {
		this.paciente = pac;
		

	}
    public Paciente getPaciente() {
    	System.out.print("Paciente en controlador Paciente" + this.paciente.getDni());
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}



    @FXML
    private Button botonPerfilP;

    @FXML
    private Button botonConsultarMedico;

    @FXML
    private Button botonVerDatos;

    @FXML
    private Button BotonSalirP;

    @FXML
    void consultarMedico(ActionEvent event) {
    	 JsonBD ob = new JsonBD();
    	 List<Medico> medcs = ob.recuperarMedicos();
    	 String cadenaMedicos = "";
    	 //List<String> medCheck = Arrays.asList(paciente.getMedicos());
    	 String [] medCheck = paciente.getMedicos();
    	 for (Medico med:medcs){
    		 System.out.print("MEDICO DNI: " + med.getDni());
    		for (String medCh : medCheck) {
    			System.out.print("PACIENTE MEDICOS ASOCIADOS: " + medCh );
    			if (medCh.equalsIgnoreCase(med.getDni())) {
    				 cadenaMedicos+="Nombre: " + med.getNombre();
    	    		 cadenaMedicos+= "Apellido: "+ med.getApellidos();
    	    		 cadenaMedicos += "DNI: "+ med.getDni() ;
    	    		 cadenaMedicos += "\n";
    			}
    		}
    		
    	 }
      
		panelVisualizarP.setText(cadenaMedicos);
    }

    @FXML
    void verPerfilP(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/PerfilPaciente.fxml"));
			ControladorPerfilP ControladorPerfilPaciente = new ControladorPerfilP(getPaciente());
			loader.setController(ControladorPerfilPaciente);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonPerfilP.getScene().getWindow();
			myStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void visualizarDatosP(ActionEvent event) {

    }
    
    @FXML
    void salirP(ActionEvent event) {
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
			Stage myStage = (Stage) this.BotonSalirP.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
