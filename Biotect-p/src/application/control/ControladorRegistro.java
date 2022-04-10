package application.control;

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
    private Button esBotonPaciente;

    @FXML
    private Button esBotonMedico;

    @FXML
    private Button esBotonConsultor;

    @FXML
    private Button botonSalir;

    @FXML
    void abrirRegistroC(ActionEvent event) {

    	try {

        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/RegistroConsultor.fxml"));

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

        	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RegistroMedico.fxml"));

    		ControladorRegistroM ControladorRegistroM = new ControladorRegistroM();

    		loader.setController(ControladorRegistroM);

    		Parent root = loader.load();

    	//	ControlMenu.verTexto(user);;

    		Stage stage = new Stage();

    		stage.setScene(new Scene(root));

    		//stage.initModality(Modality.WINDOW_MODAL);
    		//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
    		stage.show();
    		// Se cierra la ventana anterior al entrar
    		Stage myStage = (Stage) this.esBotonMedico.getScene().getWindow();
    		myStage.close();

	  } catch(Exception e) {
		e.printStackTrace();
	  }

    }

    @FXML
    void abrirRegistroP(ActionEvent event) {
    	try {

        	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RegistroPaciente.fxml"));

    		ControladorRegistroP ControladorRegistroP = new ControladorRegistroP();

    		loader.setController(ControladorRegistroP);

    		Parent root = loader.load();

    	//	ControlMenu.verTexto(user);;

    		Stage stage = new Stage();

    		stage.setScene(new Scene(root));

    		//stage.initModality(Modality.WINDOW_MODAL);
    		//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());

    		stage.show();
    		
    		Stage myStage = (Stage) this.esBotonPaciente.getScene().getWindow();
    		myStage.close();

	  } catch(Exception e) {
		e.printStackTrace();
	  }
    }

    @FXML
    void salir(ActionEvent event) {
    	System.exit(0);

    }


}
