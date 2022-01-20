package application;

import application.control.ControladorLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		//BorderPane root = new BorderPane();
		//Scene scene = new Scene(root,400,400);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/view/LOGIN.fxml"));

//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/LOGIN.fxml"));
//			loader.setLocation(Main.class.getResource("LOGIN.fxml"));
        //Scene scene = new Scene(root);
            ControladorLogin control = new ControladorLogin();

		    loader.setController(control);

			Parent root = loader.load();

			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
