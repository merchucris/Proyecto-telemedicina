package application.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.model.Consultor;
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
import repo.MariaBD;

public class ControladorPerfilP {

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
    private TextField botonSexoP;

    @FXML
    private Button botonVolverP;

    @FXML
    private Button botonMostrarP;

    //Constructor de paciente
    private Paciente pacnts;
	
    //Getters y setters
    public Paciente getPacs() {
		return this.pacnts;
	}
    
	public void setPacs(Paciente pacie) {
		this.pacnts = pacie;
	}
    
    public void ponerDni(String dni) {
    	botonDNIP.setText(pacnts.getDni());
    }

	public TextField getBotonNombreP() {
		return botonNombreP;
	}
	
	public void setMiNombreP(TextField botonNombreP) {
		this.botonNombreP = botonNombreP;
	}

   	public ControladorPerfilP(Paciente pacnts) {
   		super();
   		this.pacnts = pacnts;
    }
   	public void setMiNombreP(String nombre) {
		System.out.println("**00003**");
		this.botonNombreP.setText(nombre);
	}
	public void setMiApellidoP(String apellidos) {
		this.botonApellidoP.setText(apellidos);
	}
	public void setMiCorreoP(String correo) {
		this.botonCorreoP.setText(correo);
	}
	
    private String BBDDName = "prbbiotect";
    static final String USER = "prb_biotect";
    static final String PASS = "biotect123";
    
    @FXML
    void mostrarPerfilP(ActionEvent event) {
    	 System.out.print("PACIENTE DNI: " + this.pacnts.getDni());
         MariaBD ob = new MariaBD();
         Paciente pacie = ob.recuperarPaciente(this.pacnts.getDni());
     
         botonNombreP.setText(pacie.getNombre());
         botonApellidoP.setText(pacie.getApellidos());
         botonDNIP.setText(pacie.getDni());
         botonCorreoP.setText(pacie.getCorreo());
         botonSexoP.setPromptText(pacie.getSexo());
     }


    @FXML
    void volverMenuP(ActionEvent event) {
    	
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
			Stage myStage = (Stage) this.botonVolverP.getScene().getWindow();
			myStage.close();
    	    
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
   }
    
    @FXML
    void SalirP(ActionEvent event) {
    	
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
