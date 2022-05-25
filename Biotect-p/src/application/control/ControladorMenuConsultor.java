package application.control;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXTextArea;

import application.model.Consultor;
import application.model.Paciente;
import application.model.Sensor;
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

public class ControladorMenuConsultor {

	private Consultor consultor;
	
    public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

	@FXML
    private JFXTextArea panelVisualizarOpcionC;

    @FXML
    private Button botonPerfilC;

    @FXML
    private Button botonListaPacientes;

    @FXML
    private Button botonVerDatosC;

    @FXML
    private Button botonSalirC;

    @FXML
    void verPerfilC(ActionEvent event) {
    	MariaBD bbdd = new MariaBD();
    	try {
    		System.out.println("----*****--0");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/PerfilConsult.fxml"));  // RegistroConsultor.fxml"));
			System.out.println("----*****--00");
			ControladorPerfilC controladorPerfilC = new ControladorPerfilC(getConsultor());
			System.out.println("----*****--000");
			loader.setController(controladorPerfilC);
			System.out.println("----*****--01-dni: "+consultor.getDni());
			controladorPerfilC.setCons(consultor);
//			controladorPerfilC.setMiDNIC(consultor.getDni());
			System.out.println("----*****--02");
//			controladorPerfilC.setMiNombreC(consultor.getNombre());
//			controladorPerfilC.setMiApellidoC(consultor.getApellidos());
//			controladorPerfilC.setMiCorreoC(consultor.getCorreo());
			System.out.println("----*****--03");
			Parent root;
			root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonPerfilC.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void abrirLista(ActionEvent event) {
    	MariaBD ob = new MariaBD();
    	List<Consultor> listaConsultores = ob.recuperarListaConsultores();
    	//reiterar la lista for each
    	for (Consultor consultor :  listaConsultores) {
    		System.out.println("Datos consultor" + consultor.getNombre());
    		panelVisualizarOpcionC.appendText("Nombre: " + consultor.getNombre() + " Apellidos: " + consultor.getApellidos());
    		panelVisualizarOpcionC.appendText("###########################################");
    		
    	}
    	// Mostrar lista
    }

    @FXML
    void verDatosSensoresC(ActionEvent event) {
   	 MariaBD ob = new MariaBD();
  	 List<Sensor> senss = ob.recuperarSensores();
  	 String cadenaSensores = "";
  	 //List<String> medCheck = Arrays.asList(paciente.getMedicos());
  	 for (Sensor sen:senss){
  		System.out.print("SENSORES AL PACIENTE ASOCIADOS: " + sen);
			//cadenaSensores+= "Fecha: "+ sen.getmarcaDeTiempo();
			cadenaSensores+="Glucemia: " + sen.getglucemia();
			cadenaSensores += "Saturación: "+ sen.getsaturacion();
			cadenaSensores += "Temperatura: "+ sen.gettemperatura();
			cadenaSensores += "\n";
  	}
  	panelVisualizarOpcionC.setText(cadenaSensores);

    }

    @FXML
    void verListaPacientes(ActionEvent event) {
      	 MariaBD ob = new MariaBD();
      	 List<Paciente> pacs = ob.recuperarPacientes();
      	 String cadenaPacientes = "";
      	 //List<String> medCheck = Arrays.asList(paciente.getMedicos());
      	 for (Paciente pac:pacs){
      		System.out.print("SENSORES AL PACIENTE ASOCIADOS: " + pac);
      		    cadenaPacientes+= "Nombre: "+ pac.getNombre();
      		    cadenaPacientes+="Apellido: " + pac.getApellidos();
      		    cadenaPacientes += "DNI: "+ pac.getDni();
      		    cadenaPacientes += "\n";
      	}
      	panelVisualizarOpcionC.setText(cadenaPacientes);

    }
    @FXML
    void salirC(ActionEvent event) {
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
			Stage myStage = (Stage) this.botonSalirC.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    	
//    miNombreC.setText(this.consultor.getNombre());
//    miApellidoC.setText(cons.getApellidos());
//    miDNIC.setText(cons.getDni());
//    miCorreoC.setText(cons.getCorreo());

}