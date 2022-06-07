package application.control;

import java.io.IOException;
import java.sql.Timestamp;

import application.model.Medico;
import application.model.MensajePaciente;
import application.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorEnviarMensajeMed {

    @FXML
    private Button botonSalir;

    @FXML
    private Button BotonVolverALogin;

    @FXML
    private Button botonEnviarMensaje;

    @FXML
    private TextField dniDdestinatatio;

    @FXML
    private TextField asuntoDestinatario;

    @FXML
    private TextArea mensajeEnviar;
    
    private Medico meds;

    
	public ControladorEnviarMensajeMed(Medico medico) {
    	this.setMeds(meds);
	}
	
    public Medico getMeds() {
		return meds;
	}

	public void setMeds(Medico medic) {
		this.meds = medic;
	}
	
    @FXML
    void enviarMensaje(ActionEvent event) {
    	
        MariaBD ob = new MariaBD();
        
    	//String id_mensaje = "0";
        Paciente pac = ob.recuperarPaciente(dniDdestinatatio.getText());
    	int id_paciente_receptor = pac.getId_paciente();
    	int id_medico_remitente = meds.getId_medico();
    	String asunto = asuntoDestinatario.getText();
    	String mensaje = mensajeEnviar.getText();
    	//Timestamp marcaDeTiempo = new Timestamp(System.currentTimeMillis());
    	MensajePaciente  m = new MensajePaciente(0,id_paciente_receptor, null,id_medico_remitente, asunto, mensaje);	
    	
    	ob.altaMensaje(m);
    }

    @FXML
    void volverL(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuM.fxml"));
    		ControladorMenuMedico ControladorMenuMedico = new ControladorMenuMedico(this.meds);
			loader.setController(ControladorMenuMedico);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.BotonVolverALogin.getScene().getWindow();
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
			ControladorSalir ControladorSalirMenu = new ControladorSalir();
			loader.setController(ControladorSalirMenu);
			Parent root = loader.load();
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
