package application.control;

import java.util.ArrayList;
import java.util.List;

import application.model.Medico;
import application.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import repo.MariaBD;

public class ControladorListaPacientes {

    @FXML
    private Button botonVolverMenuM;

    @FXML
    private TextArea listaPacientes;

    @FXML
    private Button botonMostrarMedicos;
    
	private Medico medico;
	
	public ControladorListaPacientes(Medico medico) {
		this.medico  =medico;
	}
    public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

    @FXML
    void mostrarListaPacientes(MouseEvent event) {
  	     MariaBD ob = new MariaBD();
  	     List<Paciente> pacs = ob.recuperarPacientes();
  	     String cadenaPacientes = "";
  	     //List<String> pacCheck = Arrays.asList(medico.getPacientes());
  	    
  	     for (Paciente pac:pacs){
  	    	 
  		    System.out.print("PACIENTE DNI: " + pac.getDni());
  		    ArrayList<Medico> pacCheck = pac.getMedicos();
  		    if (pacCheck.size()>0) {
  		    System.out.print(pacCheck.size());
  		     for (Medico pacCh : pacCheck) {
   		    	System.out.print("MEDICOS PACIENTES ASOCIADOS: " + pacCh );
   			    if (pacCh.getDni().equalsIgnoreCase(medico.getDni())) {
   				    cadenaPacientes+="Nombre: " + pac.getNombre();
   				    cadenaPacientes+= "Apellido: "+ pac.getApellidos();
   				    cadenaPacientes += "DNI: "+ pac.getDni() ;
   				    cadenaPacientes += "\n";
   			    }
   		    }
  		    }
  	     }

    }

    @FXML
    void volverVentanaMenuM(ActionEvent event) {

    }

}
