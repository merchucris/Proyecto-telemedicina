package application.control;

import java.io.IOException;

import application.model.Consultor;
import application.model.Medico;
import application.model.MensajeMedico;
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

public class ControladorEnviarMensajeCon {
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

	private Consultor cons;
	private Medico meds;
    
    public ControladorEnviarMensajeCon(Consultor cons) {
    	this.setCons(cons);
    }


	public Consultor getCons() {
		return cons;
	}
	public void setCons(Consultor cons) {
		this.cons = cons;
	}

	
    @FXML
    void enviarMensaje(ActionEvent event) {
    	
        MariaBD ob = new MariaBD();
        
    	//String id_mensaje = "0";
        Medico meds = ob.recuperarMedico(dniDdestinatatio.getText());
    	int id_medico_receptor = meds.getId_medico();
    	int id_consultor_remitente = cons.getId_consultor();
    	String asunto = asuntoDestinatario.getText();
    	String mensaje = mensajeEnviar.getText();
    	//Timestamp marcaDeTiempo = new Timestamp(System.currentTimeMillis());
    	MensajeMedico  m = new MensajeMedico(0,id_medico_receptor, null,id_consultor_remitente, asunto, mensaje);	
    	
    	ob.altaMensajeAlMedico(m);
    }

    @FXML
    void volverL(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuC.fxml"));
    		ControladorMenuConsultor ControladorMenuConsult = new ControladorMenuConsultor(this.cons);
			loader.setController(ControladorMenuConsult);
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
