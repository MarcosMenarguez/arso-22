package encuestas.test;

import java.time.LocalDateTime;

import encuestas.modelo.Encuesta;
import encuestas.modelo.Opcion;

public class PruebaEncuestas {

	public static void main(String[] args) {
		
		Encuesta encuesta = new Encuesta();
		encuesta.setTitulo("Examen parcial");
		encuesta.setApertura(LocalDateTime.now());
		encuesta.setCierre(LocalDateTime.now().plusDays(1)); // mañana
		
		Opcion opcion1 = new Opcion();
		opcion1.setTexto("viernes tarde");
		
		Opcion opcion2 = new Opcion();
		opcion2.setTexto("lunes tarde");
		
		encuesta.getOpciones().add(opcion1);
		encuesta.getOpciones().add(opcion2);

		System.out.println(encuesta);
		
		System.out.println("fin.");
		

		
		
	}
}
