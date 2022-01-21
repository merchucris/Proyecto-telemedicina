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

import application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    	
    	try {
    	myJson.leerJsonPartes("admin.json");
    	}catch (Exception e) {
    		System.out.println("*** Fallo en: "+e);
		}
    	
    	if(user.equals("mcr") && password.equals("corde3")){
    		try {
    			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/application/view/AdminRegistra.fxml"));

    			ControlMenu ControlMenu = new ControlMenu();

    			loader2.setController(ControlMenu);

    			Parent root2 = loader2.load();

    		//	ControlMenu.verTexto(user);

    			Stage stage = new Stage();

    			stage.setScene(new Scene(root2));

    			stage.initModality(Modality.WINDOW_MODAL);
    			stage.initOwner(((Node) (event.getSource())).getScene().getWindow());

    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
   // 	} else if() {
    		
    		
    	}
    	//Aquí se comprueba el tipo de usuario que es. En principio, sólo admin. Lo creo "a pelo"
    	   	


    }
    void leerJsonPartes(String sFile) throws java.io.IOException {
		JsonParser parser = new JsonParser();
        FileReader fr = new FileReader(sFile);
        JsonElement admin = parser.parse(fr);
        dumpJSONElement(admin);
    }

//    void escribirJsonClass() throws java.io.IOException {
//    	Gson gson = new Gson();
//        MiObjeto obj = new MiObjeto("Juan", "Madrid", null);
//        String jsonString = gson.toJson(obj);
//        System.out.println("JSON: " + jsonString);       
//    }
    
    void dumpJSONElement(JsonElement elemento) {
        if (elemento.isJsonObject()) {
            System.out.println("Es objeto");
            JsonObject obj = elemento.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String,JsonElement> entrada = iter.next();
                System.out.println("Clave: " + entrada.getKey());
                System.out.println("Valor: " + entrada.getValue());
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
    
    //No va a existir.Borrar.
    @FXML
    void botonRegistrarse(ActionEvent event) {
    	try {

        	//FXMLLoader loader3 = new FXMLLoader(getClass().getResource("../view/registroVista.fxml"));

    		ControlMenu ControlMenu = new ControlMenu();

    		//loader3.setController(ControlMenu);

    		//Parent root = loader3.load();

    	//	ControlMenu.verTexto(user);;

    		//Stage stage = new Stage();

    		//stage.setScene(new Scene(root));

    		//stage.initModality(Modality.WINDOW_MODAL);
    		//stage.initOwner(((Node) (event.getSource())).getScene().getWindow());

    		//stage.show();

	} catch(Exception e) {
		e.printStackTrace();
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
