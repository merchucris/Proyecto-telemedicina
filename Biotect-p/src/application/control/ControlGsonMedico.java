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

import application.model.Medico;

public class ControlGsonMedico {
	public static void main(String[] args) {
		ControlGsonMedico miManejadorJsonMedicos = new ControlGsonMedico();

		miManejadorJsonMedicos.serializarArrayAJsonMedicos();

		Vector<Medico> m = new Vector<>();
		m = miManejadorJsonMedicos.desserializarJsonAArray();

		Medico medico1 = m.get(0);
		Medico medico2 = m.get(1);
		Medico medico3 = m.get(2);


		System.out.println(medico1);
		System.out.println(medico2);
		System.out.println(medico3);

	}

	public void serializarArrayAJsonMedicos() {
		Medico medico1 = new Medico("Doctor","House", 1234, 21);
		Medico medico2 = new Medico("The Good","Doctor", 1373, 28);
		Medico medico3 = new Medico("Begoña","Flores", 4321, 42);

		Vector<Medico> medicos = new Vector<>();
		medicos.add(medico1);
		medicos.add(medico2);
		medicos.add(medico3);


		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionBonita = prettyGson.toJson(medicos);
		System.out.println(representacionBonita);

		try(FileWriter writer = new FileWriter("medicos.json")){
			prettyGson.toJson(medicos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	public Vector<Medico> desserializarJsonAArray() {
		Vector<Medico> medicos = new Vector<>();

		try (Reader reader = new FileReader("medicos.json")) {
			Gson gson = new Gson();
			Type tipoListaMedicos = new TypeToken<Vector<Medico>>(){}.getType();
			medicos = gson.fromJson(reader, tipoListaMedicos);
        } catch (IOException e) {
            e.printStackTrace();
        }

		return medicos;
	}

}
