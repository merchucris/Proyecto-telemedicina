package application.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControlMenu {

	@FXML
   private Label miLabel;

	@FXML
   public void verTexto() {
   	miLabel.setText("hola");
   }

}
