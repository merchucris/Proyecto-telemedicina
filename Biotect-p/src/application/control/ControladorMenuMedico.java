package application.control;

import java.io.IOException;

import com.jfoenix.controls.JFXTextArea;

import application.model.Consultor;
import application.model.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorMenuMedico {

	private Medico medico;
	
    public Medico getMedico() {
		return medico;
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

    }

    @FXML
    void visualizarDatos(ActionEvent event) {

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