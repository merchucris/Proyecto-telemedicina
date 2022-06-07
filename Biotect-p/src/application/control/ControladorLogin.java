package application.control;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import application.model.Administrador;
import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;
import application.model.Usuario;
import javafx.animation.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import repo.MariaBD;

public class ControladorLogin {
	

    @FXML
    private TextField etiquetauser;

    @FXML
    private PasswordField etiquetaPassword;
    
    @FXML
    private Button botonRegistro1;

    @FXML
    private Button botonEntrar;

    @FXML
    private Button botonRegistro;

    @FXML
    void entrarLogin(ActionEvent event) {
    	
		
    	String user = etiquetauser.getText();
    	String password = etiquetaPassword.getText();

    	//ControladorLogin myJson = new ControladorLogin();
    	MariaBD bbdd = new MariaBD();
    	// CAMBIO A BBDD
    	try {
    		String tipo = bbdd.comprobarUsuario(user,password);
    		System.out.print("Tipo "+ tipo);
    				
    		if (tipo.equals("CONSULTOR")){
   	    		// LEER DE LA TABLA CONSULTOR
   	    		Consultor cons = bbdd.recuperarConsultor(user);
   	    		accederVistaConsultor(cons);
   	    	}else if (tipo.equals("PACIENTE")) {
   	    		// LEER TABLA PACIENTE
   	    		Paciente pacs = bbdd.recuperarPaciente(user);
   	    		System.out.println("Pacientes holaa"+pacs.getDni());
   	    		accederVistaPaciente(pacs);
   	    	}else if (tipo.equals("MEDICO")){
   	    		// LEER TABLA MEDICO
   	    		Medico meds = bbdd.recuperarMedico(user);
   	    		accederVistaMedico(meds);
   	    	} else if(tipo.equals("ADMINISTRADOR")) {
   	    		accederVistaAdministrador();
   	    	}
   	    	else {
    			usuarioNoValido();
    		}
    	    

    	}catch (Exception e) {
    		System.out.println("*** Fallo en: "+e);
		}
    }
    
    @FXML
    void entrarRegistro(ActionEvent event) {
    //Para pacientes únicamente	
    	try {

        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/RegistroPacient.fxml"));

    		ControladorRegistroP ControladorRegistroP = new ControladorRegistroP();

    		loader.setController(ControladorRegistroP);

    		Parent root = loader.load();

    	//	ControlMenu.verTexto(user);;

    		Stage stage = new Stage();

    		stage.setScene(new Scene(root));

    		//stage.initModality(Modality.WINDOW_MODAL);
    		//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());

    		stage.show();
    		
    		Stage myStage = (Stage) this. botonRegistro1.getScene().getWindow();
    		myStage.close();

	  } catch(Exception e) {
		e.printStackTrace();
	  }

    }
    public void accederVistaConsultor(Consultor cons) {
    
		try {

			ControladorMenuConsultor controladorMenuConsultor = new ControladorMenuConsultor(cons);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuC.fxml"));
			loader.setController(controladorMenuConsultor);
			controladorMenuConsultor.setConsultor(cons);
			Parent root;
			root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonEntrar.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    }
    
    public void accederVistaMedico(Medico meds) {

		try {
			ControladorMenuMedico controladorMenuMedico = new ControladorMenuMedico(meds);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuM.fxml"));
			loader.setController(controladorMenuMedico);
			controladorMenuMedico.setMedico(meds);
			Parent root;
			root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonEntrar.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
    }
    
    public void accederVistaPaciente(Paciente pacs) {
    	
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuP2.fxml"));
			ControladorMenuPaciente ControladorMenuPaciente = new ControladorMenuPaciente(pacs);
			loader.setController(ControladorMenuPaciente);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
    		Stage myStage = (Stage) this.botonEntrar.getScene().getWindow();
    		myStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    public void accederVistaAdministrador() {
        
		try {

			ControladorRegistro controladorRegistro = new ControladorRegistro();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/AdminEligeRegistro.fxml"));
			loader.setController(controladorRegistro);
			//controladorRegistro.setUsuario(usus);
			Parent root;
			root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonEntrar.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    }

	void usuarioNoValido() {
		etiquetauser.setText("Usuario/contraseña no válida.");
		PauseTransition pause = new PauseTransition();
		pause.setDuration(Duration.seconds(2));
		pause.setOnFinished(e -> etiquetauser.setText(null));
		pause.play();
		//Alert alert = new Alert(Alert.AlertType.ERROR);
		//alert.setContentText("Usuario/contraseña no válida.");
		//alert.show();
		System.out.println("Usuario/contraseña no válida.");
	}
	


}