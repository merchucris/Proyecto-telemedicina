package application.control;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Vector;

import application.model.Consultor;
import application.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import repo.MariaBD.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class ControladorRegistroC {
	
	Gson gson = new Gson();

    @FXML
    private Button botonSalir;

    @FXML
    private TextField miNombreC;

    @FXML
    private TextField miApellidoC;

    @FXML
    private TextField miDNIC;

    @FXML
    private TextField miPasswordC;

    @FXML
    private TextField miCorreoC;

    @FXML
    private Button botonVolverC;
    
    @FXML
    void crearConsultor(ActionEvent event) {
//Deserializar 
    	
//   ObservableList<Consultor> consultores = FXCollections.observableArrayList();
//   String dni, nombre, apellidos, correo, password;
//    
//    dni = miDNIC.getText();
//    nombre = miNombreC.getText();
//    apellidos = miApellidoC.getText();
//    correo = miCorreoC.getText();
//    password = miPasswordC.getText();

        MariaBD ob = new MariaBD();
      
    	String nombre = miNombreC.getText();
    	String apellido = miApellidoC.getText();
    	String correo = miCorreoC.getText();
    	String dni = miDNIC.getText();
    	String password = miPasswordC.getText();
    	
    	
    	Consultor  c = new Consultor(0, dni, nombre, apellido, correo);
    	ob.altaConsultor(c, password);


    }
    
	public Vector<Consultor> desserializarJsonAArray() {
		Vector<Consultor> consultor = new Vector<Consultor>();
		
		try (Reader reader = new FileReader("consultor.json")) {
			Gson gson = new Gson();
			Type tipoListaConsultor = new TypeToken<Vector<Consultor>>(){}.getType();
			consultor = gson.fromJson(reader, tipoListaConsultor);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return consultor;
	}
    public Button getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(Button botonSalir) {
		this.botonSalir = botonSalir;
	}

	public TextField getMiNombreC() {
		return miNombreC;
	}

	public void setMiNombreC(TextField miNombreC) {
		this.miNombreC = miNombreC;
	}

	public TextField getMiApellidoC() {
		return miApellidoC;
	}

	public void setMiApellidoC(TextField miApellidoC) {
		this.miApellidoC = miApellidoC;
	}

	public TextField getMiDNIC() {
		return miDNIC;
	}

	public void setMiDNIC(TextField miDNIC) {
		this.miDNIC = miDNIC;
	}

	public TextField getMiPasswordC() {
		return miPasswordC;
	}

	public void setMiPasswordC(TextField miPasswordC) {
		this.miPasswordC = miPasswordC;
	}

	public TextField getMiCorreoC() {
		return miCorreoC;
	}

	public void setMiCorreoC(TextField miCorreoC) {
		this.miCorreoC = miCorreoC;
	}

	public Button getBotonVolverC() {
		return botonVolverC;
	}

	public void setBotonVolverC(Button botonVolverC) {
		this.botonVolverC = botonVolverC;
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
    			Stage myStage = (Stage) this.botonVolverC.getScene().getWindow();
    			myStage.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
	@FXML
    void botonSalir(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaSalir2.fxml"));
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
