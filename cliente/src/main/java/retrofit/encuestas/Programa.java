package retrofit.encuestas;

import java.time.LocalDateTime;

import retrofit.github.GitHubService;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8080/api/")
				.addConverterFactory(JacksonConverterFactory.create())
				.build();

		EncuestasRestCliente service = 
				retrofit.create(EncuestasRestCliente.class);
		
		Encuesta encuesta = new Encuesta();
		encuesta.setTitulo("Fecha parcial");
		encuesta.setApertura(LocalDateTime.now().toString());
		encuesta.setCierre(LocalDateTime.now().plusDays(1).toString());
		
		Opcion opcion1 = new Opcion();
		opcion1.setTexto("Jueves");
		encuesta.getOpciones().add(opcion1);
		
		Opcion opcion2 = new Opcion();
		opcion2.setTexto("Viernes");
		encuesta.getOpciones().add(opcion2);
		
		
		Response<Void> respuesta = 
				service.createEncuesta(encuesta).execute();
		
		System.out.println(respuesta);
		
		String url1 = respuesta.headers().get("Location");

		String id1 = url1.substring(url1.lastIndexOf("/") + 1);

		System.out.println("Encuesta creada: " + url1);
		System.out.println("Id: " + id1);
		
		Encuesta encuesta2 = 
				service.getEncuesta(id1).execute().body();
		
		System.out.println(encuesta2.getTitulo());
		
		

		
		
	}
}
