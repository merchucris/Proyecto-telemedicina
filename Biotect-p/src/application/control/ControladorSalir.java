package application.control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorSalir {

    @FXML
    private Button botonSalir;

    @FXML
    private Button botonSalirFinal;

    @FXML
    void salirAplicacion(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void volverAEntrar(ActionEvent event) {
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaLogin.fxml"));
			ControladorLogin ControladorLogin = new ControladorLogin();
			loader.setController(ControladorLogin);
			Parent root;
			root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonSalirFinal.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
