package application.control;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXComboBox;

import application.model.Medico;
import application.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorRegistroP {
	private boolean esModificar = false;
    @FXML
    private Button botonSalir;

    @FXML
    private TextField botonNombreP;

    @FXML
    private TextField botonApellidoP;

    @FXML
    private TextField botonDNIP;

    @FXML
    private DatePicker botonFechaNacP;

    @FXML
    private TextField botonCorreoP;

    @FXML
    private TextField botonDNImedicoP;

    @FXML
    private JFXComboBox<String> botonSexoP;

    @FXML
    private Button botonCrearP;
    
    @FXML
    private TextField botonPasswordP;

    @FXML
    private TextField textoBuscarDNIP;

    @FXML
    private Button botonBuscarDNIP;
    
    @FXML
    private TextField botonDNImedicoP1;

    @FXML
    private TextField botonDNImedicoP2;

    @FXML
    private TextField botonDNImedicoP3;

    @FXML
    private Button BotonVolverALogin;
    ObservableList<String> listSex = FXCollections.observableArrayList(
            "Masculino", "Femenino");
	@FXML
    public void initialize() {
		botonSexoP.setItems(listSex);
  
    	
    }

    @FXML
    void buscarDNI(ActionEvent event) {
    	
          MariaBD ob = new MariaBD();
          Paciente paciee = ob.recuperarPaciente(textoBuscarDNIP.getText());
      
//          miNombreP.setText(paciee.getNombre());
//          miApellidoP.setText(paciee.getApellidos());
//          miDNIP.setText(paciee.getDni());
//          miCorreoP.setText(paciee.getCorreo());
           botonNombreP.setText(paciee.getNombre());
           botonApellidoP.setText(paciee.getApellidos());
           botonDNIP.setText(paciee.getDni());
           ArrayList<Medico> med = paciee.getMedicos();
           if (med.size() >0) {

               botonDNImedicoP.setText(med.get(0).getNombre());
           }
           if (med.size() >1) {

        	   botonDNImedicoP1.setText(med.get(1).getNombre());
           }
           if (med.size() >2) {

        	   botonDNImedicoP2.setText(med.get(2).getNombre());
           }
           if (med.size() >3) {

        	   botonDNImedicoP3.setText(med.get(3).getNombre());
           }
           
           botonCorreoP.setText(paciee.getCorreo());
           botonFechaNacP.setPromptText(paciee.getFechNac());
           //botonEdadP.setText(Integer.toString(paciee.getEdad()));
           botonSexoP.setPromptText(paciee.getSexo());
           botonCrearP.setText("Modificar");
           this.esModificar = true;
    }

    @FXML
    void crearPaciente(ActionEvent event) {
    	
    	  MariaBD ob = new MariaBD();
    	  try {
	    	  String dni = this.botonDNIP.getText();
		 	  String nombre = this.botonNombreP.getText();
		 	  String apellidos = this.botonApellidoP.getText();
		 	  String correo = this.botonCorreoP.getText();
		 	  String fechNac = this.botonFechaNacP.getPromptText();
		 	  //int edad = Integer.parseInt(botonEdadP.getText());
		 	  String sexo = this.botonSexoP.getPromptText();
		 	  String[] medicos = new String[4];
		 	  medicos[0] = botonDNImedicoP.getText();
		 	  medicos[1] = botonDNImedicoP1.getText();
		 	  medicos[2] = botonDNImedicoP2.getText();
		 	  medicos[3] = botonDNImedicoP3.getText();
		 	  String password = botonPasswordP.getText();
		 	 Paciente p = new Paciente(0,dni, nombre, apellidos, correo, fechNac, sexo, medicos);
		 	    // Paciente paci = ob.recuperarPaciente("");
			  if ( this.esModificar == true) {
				  // Moificar un paciente
				  ob.modificarPaciente(textoBuscarDNIP.getText(), p);
			  }else {
				  // Crear un Paciente
				 	 
				 	  ob.altaPaciente(p,password);	 	  
			  }
    	  }catch(NumberFormatException e1) {
    		  System.out.println("No se pudo modificar paciente. La edad es obligatoria y debe ser un numero");
    	  }
	 	  
// 	     botonNombreP.setText(paci.getNombre());
// 	     botonApellidoP.setText(paci.getApellidos());
//     	 botonDNIP.setText(paci.getDni());
//      	 botonCorreoP.setText(paci.getCorreo());
//      	 botonSexoP.setText(paci.getSexo());
//      	 botonEdadP.setText(paci.getEdad());
//      	 botonDNImedicoP.setText(paci.getMedicos());


//    		botonNombreP.setText("");
//        	botonApellidoP.setText("");
//        	botonEdadP.setText("");
//        	botonCorreoP.setText("");
//        	botonSexoP.setText("");

    	
    }

    @FXML
    void volverL(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaLogin.fxml"));
			ControladorLogin ControladorLog = new ControladorLogin();
			loader.setController(ControladorLog);
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
    
    @FXML
    void salirP(ActionEvent event) {
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

