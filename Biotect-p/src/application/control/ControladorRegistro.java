package application.control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorRegistro {

    @FXML
    private Button esBotonMedico;

    @FXML
    private Button esBotonConsultor;

    @FXML
    private Button botonSalir;
    
    @FXML
    private Button BotonVolverALogin;


    @FXML
    void abrirRegistroC(ActionEvent event) {

    	try {

        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/RegistroConsult.fxml"));

        	ControladorRegistroC controladorRegistroC = new ControladorRegistroC();

    		loader.setController(controladorRegistroC);

    		Parent root = loader.load();

    		Stage stage = new Stage();

    		stage.setScene(new Scene(root));

    		//stage.initModality(Modality.WINDOW_MODAL);
    		//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
    		stage.show();
    		
    		Stage myStage = (Stage) this.esBotonConsultor.getScene().getWindow();
    		myStage.close();
    		
    	} catch(Exception e) {
		e.printStackTrace();
	  }

    }

    @FXML
    void abrirRegistroM(ActionEvent event) {
    	try {

        	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RegistroMedic.fxml"));
        	
    		ControladorRegistroM ControladorRegistroM = new ControladorRegistroM();
    		
    		loader.setController(ControladorRegistroM);
    		
    		Parent root = loader.load();
    		
    		Stage stage = new Stage();
    		stage.setScene(new Scene(root));
    		stage.show();
    		// Se cierra la ventana anterior al entrar
    		Stage myStage = (Stage) this.esBotonMedico.getScene().getWindow();
    		myStage.close();

	  } catch(Exception e) {
		e.printStackTrace();
	  }

    }

    @FXML
    void volverL(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaLogin.fxml"));
    		ControladorLogin ControladorLogin = new ControladorLogin();
			loader.setController(ControladorLogin);
			Parent root = loader.load();
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
    void salir(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaSalir2.fxml"));
    		ControladorSalir ControladorSali = new ControladorSalir();
			loader.setController(ControladorSali);
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
    	//System.exit(0);

    }
    


}
