package application.control;

import java.io.IOException;

import application.model.Consultor;
import application.model.Medico;
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

public class ControladorPerfilM {

    @FXML
    private TextField miNombreM;

    @FXML
    private TextField miApellidoM;

    @FXML
    private TextField miDNIM;

    @FXML
    private TextField miCorreoM;

    @FXML
    private Button botonVolverM;

    @FXML
    private Button botonSalir;
    
    private Medico meds;
	
    
    public Medico getMeds() {
		return meds;
	}

	public void setMeds(Medico medic) {
		this.meds = medic;
	}
	 public void ponerDni(String dni) {
	    miDNIM.setText(meds.getDni());
	 }

	public TextField getMiNombreM() {
		return miNombreM;
	}


	public void setMiNombreM(TextField miNombreM) {
		this.miNombreM = miNombreM;
	}


    @FXML
    void volverMenuM(ActionEvent event) {

    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuMedico.fxml"));
    		ControladorMenuMedico ControladorMenuMedico = new ControladorMenuMedico(this.meds);
			loader.setController(ControladorMenuMedico);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonVolverM.getScene().getWindow();
			myStage.close();
    	    
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    }
    
    @FXML
    void salirM(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/VentanaSalir.fxml"));
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
    
    @FXML
    void mostrarPerfilM(ActionEvent event) {
      ponerDni(getMeds().getDni());
      JsonBD ob = new JsonBD();
      Medico medcs = ob.recuperarMedico(meds.getDni());
  
      miNombreM.setText(medcs.getNombre());
      miApellidoM.setText(medcs.getApellidos());
      miDNIM.setText(medcs.getDni());
      miCorreoM.setText(medcs.getCorreo());
    }
    
	public ControladorPerfilM(Medico meds) {
		super();
		this.meds = meds;
	}
	
	public void setMiNombreC(String nombre) {
		System.out.println("**00003**");
		this.miNombreM.setText(nombre);
	}
	public void setMiApellidoC(String apellidos) {
		this.miApellidoM.setText(apellidos);
	}
	public void setMiCorreoC(String correo) {
		this.miCorreoM.setText(correo);
	}
	

}
