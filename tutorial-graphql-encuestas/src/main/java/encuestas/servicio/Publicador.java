package encuestas.servicio;

import javax.xml.ws.Endpoint;

public class Publicador {

	// Problema JAXWS y JDK17
	// --add-opens java.base/java.lang=ALL-UNNAMED
	
	public static void main(String[] args) {
		
		Endpoint.publish(
				"http://localhost:9995/ws/encuestas", 
				ServicioEncuestas.getInstancia());
		
		System.out.println("Servicio encuestas funcionando");
	}
}
