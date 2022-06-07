package application.control;

import java.io.IOException;
import java.util.List;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorMensajeRecibidoMed {

    @FXML
    private Button botonSalir;

    @FXML
    private Button BotonVolverAMenu;

    @FXML
    private Button botonMostrarMensaje;

    @FXML
    private TextArea mensajesRecibidos;

	private Medico med;
	private MensajePaciente mensjs;

	public Medico getMed() {
		return med;
	}

	public void setMed(Medico med) {
		this.med = med;
	}

	public ControladorMensajeRecibidoMed(Medico med) {
		// TODO Auto-generated constructor stub
	}


	@FXML
    void mostrarMensaje(ActionEvent event) {
		 MariaBD ob = new MariaBD();
      	 List<MensajeMedico> listaMensajesM = ob.recuperarListaMensajesConsultor();
      	 String datosMensaje="";
		 // 1 recorrer mensj - la lista
      	 for (MensajeMedico mensjM:listaMensajesM) {
      		 if(mensjM.id_medico_receptor == mensjM.id_medico_receptor) {
      			datosMensaje+="Asunto: " + mensjM.getAsunto();
      			datosMensaje += "\n";
      			datosMensaje+="Mensaje: " + mensjM.getMensajeCon();
      			datosMensaje += "\n";
      			datosMensaje += "***************************************************";
      			datosMensaje += "\n";
      			 
      		 }
      		
      	 }
      	 mensajesRecibidos.setText(datosMensaje);

    }


	@FXML
    void volverM(ActionEvent event) {

    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuM.fxml"));
    		ControladorMenuMedico ControladorMenuMed = new ControladorMenuMedico(this.med);
			loader.setController(ControladorMenuMed);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.BotonVolverAMenu.getScene().getWindow();
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