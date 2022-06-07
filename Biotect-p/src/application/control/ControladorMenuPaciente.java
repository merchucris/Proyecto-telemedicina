package application.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jfoenix.controls.JFXTextArea;

import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;
import application.model.Sensor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorMenuPaciente {
    @FXML
    //Private JFXTextArea panelVisualizarP;
    
	private Paciente paciente;
    
	public ControladorMenuPaciente(Paciente pac) {
		this.paciente = pac;
		

	}
    public Paciente getPaciente() {
    	System.out.print("Paciente en controlador Paciente" + this.paciente.getDni());
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

    @FXML
    private Button botonPerfilP;
    
    @FXML
    private Button botonverMedicosP;
    
    @FXML
    private Button botonvisualizarMisDatos;

    @FXML
    private Button botonConsultarMedico;
    
    @FXML
    private Button botonMensajeG;

    @FXML
    private Button botonRecomendaciones;

//    @FXML
//    private Button botonVerDatos;

    @FXML
    private Button BotonSalirP;
    
    @FXML
    void verPerfilP(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/PerfilPacient.fxml"));
			ControladorPerfilP ControladorPerfilPaciente = new ControladorPerfilP(getPaciente());
			loader.setController(ControladorPerfilPaciente);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonPerfilP.getScene().getWindow();
			myStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void consultarMedico(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ListaMedicos.fxml"));
			ControladorListaMedicos ControladorListaMedicos = new ControladorListaMedicos(getPaciente());
			loader.setController(ControladorListaMedicos);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonConsultarMedico.getScene().getWindow();
			myStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void verRecomendaciones(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/vistaInfoViajes.fxml"));
    		ControladorInfoViaje ControladorInfoViaj = new ControladorInfoViaje(getPaciente());
			loader.setController(ControladorInfoViaj);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonRecomendaciones.getScene().getWindow();
			myStage.close();
    	    
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    

    @FXML
    void verMensajesG(ActionEvent event) {
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/vistaMensajeRecibidoPac.fxml"));
			ControladorMensajeRecibidoPac ControladorMensajeRecib = new ControladorMensajeRecibidoPac(getPaciente());
			loader.setController(ControladorMensajeRecib);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonMensajeG.getScene().getWindow();
			myStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void verMedicosP(ActionEvent event) {
    	
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ListaMedicos.fxml"));  // RegistroConsultor.fxml"));
			ControladorListaMedicosTodos controladorListaMedT = new ControladorListaMedicosTodos(getPaciente());
			loader.setController(controladorListaMedT);
			Parent root;
			root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonverMedicosP.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
    }

//    @FXML
//    void visualizarDatosP(ActionEvent event) {
//       	 MariaBD ob = new MariaBD();
//       	 List<Sensor> senss = ob.recuperarSensores();
//       	 String cadenaSensores = "";
//       	 //List<String> medCheck = Arrays.asList(paciente.getMedicos());
//       	 for (Sensor sen:senss){
//       		 if (this.paciente.getDni().equals(sen.getDni())) {
//       			System.out.print("SENSORES AL PACIENTE ASOCIADOS: " + sen);
//       			//cadenaSensores+= "Fecha: "+ sen.getmarcaDeTiempo();
//       			cadenaSensores+="Glucemia: " + sen.getglucemia();
//       			cadenaSensores += "Saturación: "+ sen.getsaturacion();
//       			cadenaSensores += "Temperatura: "+ sen.gettemperatura();
//       			cadenaSensores += "\n";
//       		 }
//       		
//       	}
//   		//panelVisualizarP.setText(cadenaSensores);
//    }
    
    @FXML
    void verMisDatosP(ActionEvent event) {
    	
    	try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VistaSensores3.fxml"));
			ControladorSensoresM ControladorSensorM = new ControladorSensoresM(getPaciente());
			//ControladorListaPacientes ControladorListaPacient = new ControladorListaPacientes(getMedico());
			loader.setController(ControladorSensorM);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonvisualizarMisDatos.getScene().getWindow();
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
			Stage myStage = (Stage) this.BotonSalirP.getScene().getWindow();
			myStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
