package encuestas.graphql;

import java.time.LocalDateTime;
import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import encuestas.modelo.Encuesta;
import encuestas.modelo.Opcion;
import encuestas.servicio.ServicioEncuestas;
import repositorio.RepositorioException;


public class Mutation implements GraphQLRootResolver {
    
    public Encuesta crearEncuesta(String titulo, 
    		String instruccion, 
    		String apertura,
    		String cierre,
    		List<String> opciones) throws RepositorioException {
        
    	Encuesta encuesta = new Encuesta();
    	encuesta.setTitulo(titulo);
    	encuesta.setInstruccion(instruccion);
    	encuesta.setApertura(LocalDateTime.parse(apertura));
    	encuesta.setCierre(LocalDateTime.parse(cierre));
    	
    	for (String opcionTexto : opciones) {
    		Opcion opcion = new Opcion();
    		opcion.setTexto(opcionTexto);
    		encuesta.getOpciones().add(opcion);    		
    	}
    	
    	String id = ServicioEncuestas.getInstancia().create(encuesta);
    
    	encuesta.setIdentificador(id);
    	
    	return encuesta;
    	
    }
}
