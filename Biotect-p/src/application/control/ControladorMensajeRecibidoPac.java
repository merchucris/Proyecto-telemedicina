package application.control;

import java.io.IOException;
import java.util.List;

import application.model.Consultor;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorMensajeRecibidoPac {

    @FXML
    private Button botonSalir;

    @FXML
    private Button BotonVolverAMenu;

    @FXML
    private Button botonMostrarMensaje;

    @FXML
    private TextArea mensajesRecibidos;

	private Paciente pacnts;
	private MensajePaciente mensjs;

	
    public ControladorMensajeRecibidoPac(Paciente pacnts) {
		// TODO Auto-generated constructor stub
    	this.pacnts = pacnts;
	}
    

	@FXML
    void mostrarMensaje(ActionEvent event) {
		
     	 MariaBD ob = new MariaBD();
      	 List<MensajePaciente> listaMensajes = ob.recuperarListaMensajes();
      	 String datosMensaje="";
		 // 1 recorrer mensj - la lista
      	 for (MensajePaciente mensj:listaMensajes) {
      		 if(mensj.id_paciente_receptor == mensj.id_paciente_receptor) {
      			datosMensaje+="Asunto: " + mensj.getAsunto();
      			datosMensaje += "\n";
      			datosMensaje+="Mensaje: " + mensj.getMensaje();
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
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuP2.fxml"));
    		ControladorMenuPaciente ControladorMenuPaciente = new ControladorMenuPaciente(this.pacnts);
			loader.setController(ControladorMenuPaciente);
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