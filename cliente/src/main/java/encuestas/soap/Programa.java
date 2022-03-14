package encuestas.soap;

import java.time.LocalDateTime;
import java.util.Collections;

import encuestas.servicio.Encuesta;
import encuestas.servicio.EncuestaResumen;
import encuestas.servicio.IServicioEncuestas;
import encuestas.servicio.Opcion;
import encuestas.servicio.ServicioEncuestasService;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		ServicioEncuestasService servicio = new ServicioEncuestasService();
		
		IServicioEncuestas puerto = servicio.getServicioEncuestasPort();
		
		Encuesta encuesta = new Encuesta();
		
		encuesta.setTitulo("Fecha del parcial");
		// Importante: las fechas se introducen como cadenas.
		encuesta.setApertura(LocalDateTime.now().toString());
		encuesta.setCierre(LocalDateTime.now().plusDays(1).toString());
				
		Opcion opcion1 = new Opcion();
		opcion1.setTexto("Jueves");
		Opcion opcion2 = new Opcion();
		opcion2.setTexto("Viernes");
		
		Collections.addAll(encuesta.getOpciones(), opcion1, opcion2);
		
		// Alta de la encuesta
		
		String id = puerto.create(encuesta);
		
		// Voto
		
		puerto.votar(id, 1, "juan@um.es");
		
		System.out.println("¿Ha votado juan? " + puerto.haVotado(id, "juan@um.es"));
		System.out.println("¿Ha votado jose? " + puerto.haVotado(id, "jose@um.es"));
						
		for (EncuestaResumen resumen : puerto.getListadoResumen().getEncuestas()) {
			System.out.println(resumen);
			
			if (! resumen.getId().equals(id))
				puerto.remove(resumen.getId());
		}
		
		System.out.println("fin.");

		
		
	}
}
