package application.control;

import java.io.IOException;
import java.util.List;

import application.model.Consultor;
import application.model.FrecuenciaRespiratoria;
import application.model.Medico;
import application.model.Paciente;
import application.model.Saturacion;
import application.model.Temperatura;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repo.MariaBD;

public class ControladorSensoresM {

    @FXML
    private Button botonSalirM;

    @FXML
    private Pane sensorTemperatura;

    @FXML
    private Button botonTemperatura;

    @FXML
    private Button botonFrecuenciaRepi;

    @FXML
    private Button botonSaturacion;

    @FXML
    private AreaChart<String, Float> grafica;

    private Medico med;
    private Paciente pac;
    private Consultor cons;
    private String tipo;
    @FXML
    private TextField textoDNIPaciente;
    
    public ControladorSensoresM(Medico med) {
    	this.med = med;
    	this.tipo = "M";
    }
    public ControladorSensoresM(Paciente pac) {
		this.pac = pac;
		this.tipo = "P";
    }
    public ControladorSensoresM(Consultor cons) {
		this.cons = cons;
		this.tipo = "C";	
    }
    
    @FXML
    void mostarSaturacion(ActionEvent event) {
    	System.out.println("Se llama satu");
   	    MariaBD ob = new MariaBD();
   	    List<Saturacion> listaSaturaciones =ob.recuperarListaSaturacion(textoDNIPaciente.getText());
     	grafica.getData().clear();
   	    XYChart.Series<String,Float > series =  new Series<String, Float>();
   	    series.setName("Saturacion del paciente");
   	    for (int i = 0; i<listaSaturaciones.size(); i++) {
   		   float saturacion = listaSaturaciones.get(i).getsaturacion();

   		    String marcaTiempo = listaSaturaciones.get(i).getmarcaDeTiempo().substring(10);
   		    series.getData().add(new Data<String,Float>( marcaTiempo,saturacion));
      	 }
   	    grafica.getData().add(series);

    }

    @FXML
    void mostrarFrec(ActionEvent event) {
    	System.out.println("Se llama frec");
   	    MariaBD ob = new MariaBD();
   	    List<FrecuenciaRespiratoria> listaFrecuencias =ob.recuperarListaFrecRepiatoria(textoDNIPaciente.getText());
   	    grafica.getData().clear();
    	XYChart.Series<String,Float > series =  new Series<String, Float>();
   	    series.setName("Frecuencia respiratoria del paciente");
   	    for (int i = 0; i<listaFrecuencias.size(); i++) {
   		    float frec_respi = listaFrecuencias.get(i).getfrec_respi();

   		    String marcaTiempo = listaFrecuencias.get(i).getmarcaDeTiempo().substring(10);
   		    series.getData().add(new Data<String,Float>( marcaTiempo,frec_respi));
   	    }
   	    grafica.getData().add(series);    	
    }

    @FXML
    void mostrarTemperatura(ActionEvent event) {
    	System.out.println("Se llama temp");
    	 MariaBD ob = new MariaBD();
    	 List<Temperatura> listaTemperaturas =ob.recuperarListaTemperatura(textoDNIPaciente.getText());
    	 grafica.getData().clear();
    	 XYChart.Series<String,Float > series =  new Series<String, Float>();
    	 series.setName("Temperaturas del paciente");
    	 for (int i = 0; i<listaTemperaturas.size(); i++) {
    		 float temperatura = listaTemperaturas.get(i).gettemperatura();

    		 String marcaTiempo = listaTemperaturas.get(i).getmarcaDeTiempo().substring(10);
    		 series.getData().add(new Data<String,Float>( marcaTiempo,temperatura));
    	 }
    	 grafica.getData().add(series);
    }

    @FXML
    void salirM(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = null; //Estoy haciendo una variable global para la función, local.
    	    if(this.tipo.equals("M")) {
    	    	// Vuelves al menu de MEDICOS
    	    	loader= new FXMLLoader(getClass().getResource("/application/view/MenuM.fxml"));
        		ControladorMenuMedico ControladorMenuMedic = new ControladorMenuMedico(med);
    			loader.setController(ControladorMenuMedic);
    	    	
    	    }else if(this.tipo.equals("P")) {
    	    	// PACIENTE
    	    	loader= new FXMLLoader(getClass().getResource("/application/view/MenuP2.fxml"));
    	    	ControladorMenuPaciente ControladorMenuPaciente = new ControladorMenuPaciente(pac);
    			loader.setController(ControladorMenuPaciente);
    			
    	    }else {
    	    	// CONSUTOR
    	    	loader= new FXMLLoader(getClass().getResource("/application/view/MenuC.fxml"));
    	    	ControladorMenuConsultor ControladorMenuConsult = new ControladorMenuConsultor(cons);
    			loader.setController(ControladorMenuConsult);
    	    }
    	    Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage.show();
			Stage myStage = (Stage) this.botonSalirM.getScene().getWindow();
			myStage.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }


}