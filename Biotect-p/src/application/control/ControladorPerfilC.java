package application.control;

import java.io.IOException;

import application.model.Consultor;
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


public class ControladorPerfilC {

	@FXML
    private Button botonSalir;

    @FXML
    private TextField miNombreC;

    @FXML
    private TextField miApellidoC;

    @FXML
    private TextField miDNIC;

    @FXML
    private TextField miCorreoC;

    @FXML
    private Button botonVolverC;

    @FXML
    private Button botonMostrarC;
 
    //Constructor de Consultor
    private Consultor cons;
	
    //Getters y setters al estar en privado
    public Consultor getCons() {
		return cons;
	}

	public void setCons(Consultor consue) {
		this.cons = consue;
	}
   
    public void ponerDni(String dni) {
    	miDNIC.setText(cons.getDni());
    }

	public TextField getMiNombreC() {
		return miNombreC;
	}

	public void setMiNombreC(TextField miNombreC) {
		this.miNombreC = miNombreC;
	}

	public ControladorPerfilC(Consultor cons) {
		super();
		this.cons = cons;
	}
	
	public void setMiNombreC(String nombre) {
		System.out.println("**00003**");
		this.miNombreC.setText(nombre);
	}
	public void setMiApellidoC(String apellidos) {
		this.miApellidoC.setText(apellidos);
	}
	public void setMiCorreoC(String correo) {
		this.miCorreoC.setText(correo);
	}
//    JsonBD ob = new JsonBD();
//    Consultor consuu = ob.recuperarConsultor(miDNIC.getText());
//
//    miNombreC.setText(consuu.getNombre())
//    miApellidoC.setText(consuu.getApellidos());
//    miDNIC.setText(consuu.getDni());
//    miCorreoC.setText(consuu.getCorreo());
    
//    public void ponerDni(String dni) {
//    	String s = dni+"3333L";
//    	miDNIC.setText(s);
//    }

    @FXML
    void mostrarPerfilC(ActionEvent event) {
      ponerDni(getCons().getDni());
      MariaBD ob = new MariaBD();
      Consultor consuu = ob.recuperarConsultor(cons.getDni());
  
      miNombreC.setText(consuu.getNombre());
      miApellidoC.setText(consuu.getApellidos());
      miDNIC.setText(consuu.getDni());
      miCorreoC.setText(consuu.getCorreo());
    }

//	public void setMiDNIC(String dni) {
//		JsonBD ob = new JsonBD();
//
//		Consultor cons = ob.recuperarConsultor("3333L");
//
//		miNombreC.setText(cons.getNombre());
//		miApellidoC.setText(cons.getApellidos());
//		miDNIC.setText(cons.getDni());
//		miCorreoC.setText(cons.getCorreo());
//
//		System.out.println("**00001**");
//		System.out.println("00001: "+ getCons());
//		System.out.println("00001: "+ getCons().getNombre());
//		System.out.println("00001: "+ getCons().getCorreo());
//		System.out.println("00001: "+ getCons().getPassword());
//		System.out.println("00001: "+ getCons().getDni());
//		ponerDni(getCons().getDni());
//		System.out.println("**00002**");
//	}
	
	@FXML
    void volverMenuC(ActionEvent event) {

    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuC.fxml"));
    		ControladorMenuConsultor controladorMenuConsultor = new ControladorMenuConsultor(cons);
			loader.setController(controladorMenuConsultor);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonVolverC.getScene().getWindow();
			myStage.close();
    	    
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }

    @FXML
    void SalirC(ActionEvent event) {
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
    			e.printStackTrace();
            }
    }

}