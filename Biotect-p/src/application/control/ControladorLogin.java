package application.control;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import application.model.Administrador;
import application.model.Consultor;
import application.model.Medico;
import application.model.Paciente;
import application.model.Usuario;
import javafx.animation.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControladorLogin {
    @FXML
    private TextField etiquetauser;

    @FXML
    private PasswordField etiquetaPassword;

    @FXML
    private Button botonEntrar;

    @FXML
    private Button botonRegistro;

    @FXML
    void entrarLogin(ActionEvent event) {

    	String user = etiquetauser.getText();
    	String password = etiquetaPassword.getText();

    	ControladorLogin myJson = new ControladorLogin();
    	// CAMBIO A BBDD
    	try {
        	Administrador[] adms = myJson.leerJsonAdministradores("admin.json");
    	    if (comprobarAdministrador(event, adms, user, password)) return;

    	    Paciente[] pacs = myJson.leerJsonPacientes("pacientes.json");
        	if (comprobarPaciente(event, pacs, user, password)) return;
    	    
    	    Medico[] meds = myJson.leerJsonMedicos("medicos.json");
    	    if (comprobarMedico(event, meds, user, password)) return;

    	    Consultor[] consts = myJson.leerJsonConsultores("consultores.json");
    	    if (comprobarConsultor(event, consts, user, password)) return;
    	    
    	    usuarioNoValido();

    	}catch (Exception e) {
    		System.out.println("*** Fallo en: "+e);
		}
    }

	boolean comprobarAdministrador(ActionEvent event, Administrador[] adms, String user, String password) {
		System.out.println("---DENTRO-*****--1");		
		try {
			for (Administrador adm : adms ) {
				if (adm.getUsuario().equals(user) && adm.getPassword().equals(password)) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/AdminRegistra.fxml"));
					ControladorRegistro ControlRegistro = new ControladorRegistro();
					loader.setController(ControlRegistro);
					Parent root = loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.initModality(Modality.WINDOW_MODAL);
					//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
					stage.show();
					Stage myStage = (Stage) this.botonEntrar.getScene().getWindow();
					myStage.close();
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	boolean comprobarPaciente(ActionEvent event, Paciente[] pacs, String user, String password) {
		System.out.println("---DENTRO-*****--2");
		try {
			for (Paciente pac : pacs ) {
				if (pac.getUsuario().equals(user) && pac.getPassword().equals(password)) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuPaciente.fxml"));
					ControladorMenuPaciente ControladorMenuPaciente = new ControladorMenuPaciente(pac);
					loader.setController(ControladorMenuPaciente);
					Parent root = loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.initModality(Modality.WINDOW_MODAL);
					//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
					stage.show();
		    		Stage myStage = (Stage) this.botonEntrar.getScene().getWindow();
		    		myStage.close();
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	boolean comprobarMedico(ActionEvent event, Medico[] medics, String user, String password) {
		System.out.println("---DENTRO-*****--3");
		try {
			for (Medico meds : medics ) {
				if (meds.getUsuario().equals(user) && meds.getPassword().equals(password)) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuMedico.fxml"));
					ControladorMenuMedico ControladorMenuMedico = new ControladorMenuMedico();
					loader.setController(ControladorMenuMedico);
					ControladorMenuMedico.setMedico(meds);
					Parent root = loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.initModality(Modality.WINDOW_MODAL);
					//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
					stage.show();
					Stage myStage = (Stage) this.botonEntrar.getScene().getWindow();
					myStage.close();
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	boolean comprobarConsultor(ActionEvent event, Consultor[] consts, String user, String password) {
		System.out.println("---DENTRO-*****--4");
		try {
			for (Consultor cons : consts) {
				System.out.println("---DENTRO-*****--5-"+cons.getUsuario()+"--user:-"+user+cons.getPassword()+"---pw:--"+password);
				if (cons.getUsuario().equals(user) && cons.getPassword().equals(password)) {
					System.out.println("---DENTRO-*****--6");
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MenuConsultor.fxml"));
					System.out.println("---DENTRO-*****--7");
					ControladorMenuConsultor controladorMenuConsultor = new ControladorMenuConsultor();
					System.out.println("---DENTRO-*****--8");
					loader.setController(controladorMenuConsultor);
					System.out.println("---DENTRO-*****--01");
					controladorMenuConsultor.setConsultor(cons);
					System.out.println("---DENTRO-*****--02");
					Parent root = loader.load();
					System.out.println("---DENTRO-*****--03");
					Stage stage = new Stage();
					System.out.println("---DENTRO-*****--04");
					stage.setScene(new Scene(root));
					System.out.println("---DENTRO-*****--05");
					stage.initModality(Modality.WINDOW_MODAL);
					System.out.println("---DENTRO-*****--06");
					//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
					stage.show();
					System.out.println("---DENTRO-*****--07");
					Stage myStage = (Stage) this.botonEntrar.getScene().getWindow();
					myStage.close();
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	void usuarioNoValido() {
		etiquetauser.setText("Usuario/contraseña no válida.");
		PauseTransition pause = new PauseTransition();
		pause.setDuration(Duration.seconds(2));
		pause.setOnFinished(e -> etiquetauser.setText(null));
		pause.play();
		//Alert alert = new Alert(Alert.AlertType.ERROR);
		//alert.setContentText("Usuario/contraseña no válida.");
		//alert.show();
		System.out.println("Usuario/contraseña no válida.");
	}
	
    Administrador[] leerJsonAdministradores(String sFile) throws java.io.IOException {
		JsonParser parser = new JsonParser();
        FileReader fr = new FileReader(sFile);
        JsonArray admins = parser.parse(fr).getAsJsonArray();
        System.out.println(admins);
        dumpJSONElement(admins);
        Gson gson = new Gson();
        Administrador[] administradores = gson.fromJson(admins, Administrador[].class);
        System.out.println("------");
        System.out.println(administradores);
        return administradores;
    }

    Paciente[] leerJsonPacientes(String sFile) throws java.io.IOException {
		JsonParser parser = new JsonParser();
        FileReader fr = new FileReader(sFile);
        System.out.println("-*-----");
        System.out.println(sFile);
        JsonArray pacs = parser.parse(fr).getAsJsonArray();
        System.out.println(pacs);
        dumpJSONElement(pacs);
        Gson gson = new Gson();
        Paciente[] pacientes = gson.fromJson(pacs, Paciente[].class);
        System.out.println("------");
        System.out.println(pacientes);
        return pacientes;
    }
    
    Medico[] leerJsonMedicos(String sFile) throws java.io.IOException {
		JsonParser parser = new JsonParser();
        FileReader fr = new FileReader(sFile);
        System.out.println("-*-----");
        System.out.println(sFile);
        JsonArray meds = parser.parse(fr).getAsJsonArray();
        System.out.println(meds);
        dumpJSONElement(meds);
        Gson gson = new Gson();
        Medico[] medicos = gson.fromJson(meds, Medico[].class);
        System.out.println("------");
        System.out.println(medicos);
        return medicos;
    }
    
    Consultor[] leerJsonConsultores(String sFile) throws java.io.IOException {
		JsonParser parser = new JsonParser();
        FileReader fr = new FileReader(sFile);
        System.out.println("-*-----");
        System.out.println(sFile);
        JsonArray consts = parser.parse(fr).getAsJsonArray();
        System.out.println(consts);
        dumpJSONElement(consts);
        Gson gson = new Gson();
        Consultor[] consultores = gson.fromJson(consts, Consultor[].class);
        System.out.println("------");
        System.out.println(consultores);
        return consultores;
    }

    void dumpJSONElement(JsonElement elemento) {
        if (elemento.isJsonObject()) {
            System.out.println("Es objeto");
            JsonObject obj = elemento.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String,JsonElement> entrada = iter.next();
                System.out.println("Clave: " + entrada.getKey());
                System.out.println("Valor: ");
                dumpJSONElement(entrada.getValue());
            }

        } else if (elemento.isJsonArray()) {
            JsonArray array = elemento.getAsJsonArray();
            System.out.println("Es array. Numero de elementos: " + array.size());
            java.util.Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                JsonElement entrada = iter.next();
                dumpJSONElement(entrada);
            }

        } else if (elemento.isJsonPrimitive()) {
            System.out.println("Es primitiva");
            JsonPrimitive valor = elemento.getAsJsonPrimitive();
            if (valor.isBoolean()) {
                System.out.println("Es booleano: " + valor.getAsBoolean());
            } else if (valor.isNumber()) {
                System.out.println("Es numero: " + valor.getAsNumber());
            } else if (valor.isString()) {
                System.out.println("Es texto: " + valor.getAsString());
            }
        } else if (elemento.isJsonNull()) {
            System.out.println("Es NULL");
        } else {
            System.out.println("Es otra cosa");
        }

    }

	public static Usuario comprobarUsuario(String nombreUsuario, String password, String tipo) {

		Usuario persona = new Usuario(nombreUsuario, password, tipo);
		ObjectMapper mapper = new ObjectMapper();

		try {
		    Usuario[] usuario = mapper.readValue(new File("usuarios.json"), Usuario[].class);

		       for (int i = 0; i<=usuario.length-1; i++) {
		          if (nombreUsuario.equals((usuario[i].getUsuario()))){
		            if(password.equals(usuario[i].getPassword())) {
		              persona = usuario[i];
		              return persona;
		            }
		          }
		       }
	    } catch (JsonParseException e) {
	          	e.printStackTrace();
		} catch (JsonMappingException e) {
	         	e.printStackTrace();
	    } catch (IOException e) {
		        e.printStackTrace();
   	    }
		return null;
	}
}