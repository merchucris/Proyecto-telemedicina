package application.control;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import application.model.Usuario;

public class ControlGsonUsuario {

	public static void main(String[] args) {
		ControlGsonUsuario miManejadorJsonUsuarios = new ControlGsonUsuario();


		miManejadorJsonUsuarios.serializarArrayAJsonUsuarios();

	//----------------------------------------------------------------------------------------------------
		Vector<Usuario> u = new Vector<>();
		u = miManejadorJsonUsuarios.desserializarJsonAArrayUsuario();

		Usuario usuarioCuidador = u.get(0);
		Usuario usuarioMedico = u.get(1);
		Usuario usuarioPaciente = u.get(2);

		System.out.println(usuarioCuidador);
		System.out.println(usuarioMedico);
	    System.out.println(usuarioPaciente);
	}

		public Vector<Usuario> desserializarJsonAArrayUsuario() {
			Vector<Usuario> users = new Vector<>();

			try (Reader reader = new FileReader("usuario.json")) {
				Gson gson = new Gson();
				Type tipoListaUsuarios = new TypeToken<Vector<Usuario>>(){}.getType();
				users = gson.fromJson(reader, tipoListaUsuarios);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

			return users;
		}

		public void serializarArrayAJsonUsuarios() {
			Usuario Cuidador = new Usuario("Fran","1234","Cuidador");
			Usuario Medico = new Usuario("Rosa","1234","Medico");
			Usuario Paciente = new Usuario("Lola","1234","Paciente" );

			Vector<Usuario> c = new Vector<>();
			Vector<Usuario> m= new Vector<>();
			Vector<Usuario> p = new Vector<>();

			c.add(Cuidador);
			m.add(Medico);
			p.add(Paciente);

			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			String representacionBonitaCuidador = prettyGson.toJson(c);
			String representacionBonitaMedico = prettyGson.toJson(m);
			String representacionBonitaPaciente = prettyGson.toJson(p);
			System.out.println(representacionBonitaCuidador);
			System.out.println(representacionBonitaMedico);
			System.out.println(representacionBonitaPaciente);

			try(FileWriter writer = new FileWriter("usuario.json")){
				prettyGson.toJson(c, writer);
				prettyGson.toJson(m, writer);
				prettyGson.toJson(p, writer);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		}



		}

