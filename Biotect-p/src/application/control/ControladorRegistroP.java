package application.control;

import java.io.IOException;

import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.JsonBD;

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
    private TextField botonEdadP;

    @FXML
    private TextField botonCorreoP;

    @FXML
    private TextField botonDNImedicoP;

    @FXML
    private TextField botonSexoP;

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
    void buscarDNI(ActionEvent event) {
    	
          JsonBD ob = new JsonBD();
          Paciente paciee = ob.recuperarPaciente(textoBuscarDNIP.getText());
      
//          miNombreP.setText(paciee.getNombre());
//          miApellidoP.setText(paciee.getApellidos());
//          miDNIP.setText(paciee.getDni());
//          miCorreoP.setText(paciee.getCorreo());
           botonNombreP.setText(paciee.getNombre());
           botonApellidoP.setText(paciee.getApellidos());
           botonDNIP.setText(paciee.getDni());
           String med []= paciee.getMedicos();
           if (med.length >0) {

               botonDNImedicoP.setText(paciee.getMedicos()[0]);
           }
           if (med.length >1) {

        	   botonDNImedicoP1.setText(paciee.getMedicos()[1]);
           }
           if (med.length >2) {

        	   botonDNImedicoP2.setText(paciee.getMedicos()[2]);
           }
           if (med.length >3) {

        	   botonDNImedicoP3.setText(paciee.getMedicos()[3]);
           }

          


           
           botonCorreoP.setText(paciee.getCorreo());
           botonPasswordP.setText(paciee.getPassword());
           botonEdadP.setText(Integer.toString(paciee.getEdad()));
           botonSexoP.setText(paciee.getSexo());
           botonCrearP.setText("Modificar");
           this.esModificar = true;
    }

    @FXML
    void crearPaciente(ActionEvent event) {
    	
    	  JsonBD ob = new JsonBD();
    	  try {
	    	  String dni = this.botonDNIP.getText();
		 	  String nombre = this.botonNombreP.getText();
		 	  String apellidos = this.botonApellidoP.getText();
		 	  String correo = this.botonCorreoP.getText();
		 	  String password = this.botonPasswordP.getText();
		 	  int edad = Integer.parseInt(botonEdadP.getText());
		 	  String sexo = this.botonSexoP.getText();
		 	  String[] medicos = new String[4];
		 	  medicos[0] = botonDNImedicoP.getText();
		 	  medicos[1] = botonDNImedicoP1.getText();
		 	  medicos[2] = botonDNImedicoP2.getText();
		 	  medicos[3] = botonDNImedicoP3.getText();
		 	 Paciente p = new Paciente(dni, nombre, apellidos, correo, password, edad, sexo,medicos);
		 	    // Paciente paci = ob.recuperarPaciente("");
			  if ( this.esModificar == true) {
				  // Moificar un paciente
				  ob.modificarPaciente(textoBuscarDNIP.getText(), p);
			  }else {
				  // Crear un Paciente
				 	 
				 	  ob.altaPaciente(p);	 	  
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
    void salirP(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaSalir.fxml"));
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

