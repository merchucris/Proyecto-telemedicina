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
import repo.MariaBD;

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
    private Button BotonVolverALogin;
    


    @FXML
    void crearMedico(ActionEvent event) {

        MariaBD ob = new MariaBD();
        
    	String nombre = miNombreM.getText();
    	String apellido = miApellidoM.getText();
    	String correo = miCorreoM.getText();
    	String dni = miDNIM.getText();
    	String password = miPasswordM.getText();
    	
    	Medico  m = new Medico(0,dni, nombre, apellido, correo);
    	
    	ob.altaMedico(m, password);
    }
    
    @FXML
    void volverL(ActionEvent event) {
       	try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/AdminEligeRegistro.fxml"));
    			ControladorRegistro ControladorReg = new ControladorRegistro();
    			loader.setController(ControladorReg);
    			Parent root;
    			root = loader.load();
    			Stage stage = new Stage();
    			stage.setScene(new Scene(root));
    			stage.initModality(Modality.WINDOW_MODAL);
    			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
    			stage.show();
    			Stage myStage = (Stage) this.BotonVolverALogin.getScene().getWindow();
    			myStage.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }

    @FXML
    void salirM(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaSalir2.fxml"));
    		ControladorSalir ControladorSalir = new ControladorSalir();
			loader.setController(ControladorSalir);
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
