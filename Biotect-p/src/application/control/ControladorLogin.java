package application.control;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;

import application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorLogin {

    @FXML
    private TextField etiquetauser;

    @FXML
    private PasswordField etiquetaPassword;

    @FXML
    private Button botonEntrar;

    @FXML
    private Button botonRegistro;


    @FXML
    void entrarLogin(ActionEvent event) {

    	String user = etiquetauser.getText();
    	String password = etiquetaPassword.getText();
    	
    	if(user.equals("mcr") && password.equals("corde3")){
    		try {
    			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/application/view/AdminRegistra.fxml"));

    			ControlMenu ControlMenu = new ControlMenu();

    			loader2.setController(ControlMenu);

    			Parent root2 = loader2.load();

    		//	ControlMenu.verTexto(user);

    			Stage stage = new Stage();

    			stage.setScene(new Scene(root2));

    			stage.initModality(Modality.WINDOW_MODAL);
    			stage.initOwner(((Node) (event.getSource())).getScene().getWindow());

    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
   // 	} else if() {
    		
    		
    	}
    	//Aquí se comprueba el tipo de usuario que es. En principio, sólo admin, lo creo a pelo
    	   	


    }

    //No va a existir.Borrar.
    @FXML
    void botonRegistrarse(ActionEvent event) {
    	try {

        	//FXMLLoader loader3 = new FXMLLoader(getClass().getResource("../view/registroVista.fxml"));

    		ControlMenu ControlMenu = new ControlMenu();

    		//loader3.setController(ControlMenu);

    		//Parent root = loader3.load();

    	//	ControlMenu.verTexto(user);;

    		//Stage stage = new Stage();

    		//stage.setScene(new Scene(root));

    		//stage.initModality(Modality.WINDOW_MODAL);
    		//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());

    		//stage.show();

	} catch(Exception e) {
		e.printStackTrace();
	}


    }

	public static Usuario comprobarUsuario(String nombreUsuario, String password, String tipo) {

		Usuario persona = new Usuario(nombreUsuario, password, tipo);
		ObjectMapper mapper = new ObjectMapper();

		try {
		Usuario[] usuario = mapper.readValue(new File("usuarios.json"), Usuario[].class);

		       for (int i = 0; i<=usuario.length-1; i++) {
		          if (nombreUsuario.equals((usuario[i].getUsuario()))){
		            if(password.equals(usuario[i].getPassword())) {
		            persona = usuario[i];
		        return persona;
		       }
		   }

		 }
	        	} catch (JsonParseException e) {
	          	e.printStackTrace();
		        } catch (JsonMappingException e) {
	         	e.printStackTrace();
	        	} catch (IOException e) {
		        e.printStackTrace();
   	}
		return null;
		}

}
