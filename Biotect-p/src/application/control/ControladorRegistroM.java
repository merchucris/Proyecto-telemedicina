package application.control;

import java.io.IOException;

import application.model.Consultor;
import application.model.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.JsonBD;

public class ControladorRegistroM {

    @FXML
    private Button botonSalir;

    @FXML
    private TextField miNombreM;

    @FXML
    private TextField miApellidoM;

    @FXML
    private TextField miDNIM;

    @FXML
    private TextField miCorreoM;
    @FXML
    private TextField miPasswordM;
    
    @FXML
    private Button botonCrearM;
    


    @FXML
    void crearMedico(ActionEvent event) {

        JsonBD ob = new JsonBD();
        
    	String nombre = miNombreM.getText();
    	String apellido = miApellidoM.getText();
    	String correo = miCorreoM.getText();
    	String dni = miDNIM.getText();
    	String password = miPasswordM.getText();
    	
    	
    	Medico  m = new Medico(dni, nombre, apellido, correo, password);
    	ob.altaMedico(m);
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
			Stage myStage = (Stage) this.botonSalir.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
