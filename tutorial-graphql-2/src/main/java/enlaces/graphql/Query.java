package enlaces.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import enlaces.modelo.Enlace;
import enlaces.servicio.ServicioEnlaces;
import repositorio.RepositorioException;
import usuarios.modelo.Usuario;
import usuarios.servicio.ServicioUsuarios;

public class Query implements GraphQLRootResolver {
    
    public List<Enlace> allEnlaces() throws RepositorioException {
        return ServicioEnlaces.getInstancia().findAll();
    }
    
    public List<Usuario> allUsuarios() {
        return ServicioUsuarios.getInstancia().findAll();
    }
    
    public Usuario usuarioByEmail(String email) {
    	
    	return ServicioUsuarios.getInstancia().findByEmail(email);
    }
}