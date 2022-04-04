package encuestas.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import encuestas.modelo.Encuesta;
import encuestas.servicio.ServicioEncuestas;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class Query implements GraphQLRootResolver {
    
    public Encuesta getEncuesta(String id) 
    		throws RepositorioException, EntidadNoEncontrada {
        
    	return ServicioEncuestas.getInstancia().getById(id);
    }
}