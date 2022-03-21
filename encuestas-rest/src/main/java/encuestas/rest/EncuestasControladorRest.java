package encuestas.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import encuestas.modelo.Encuesta;
import encuestas.servicio.IServicioEncuestas;
import encuestas.servicio.ListadoEncuestas;
import encuestas.servicio.ServicioEncuestas;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@Path("encuestas")
public class EncuestasControladorRest {

	private IServicioEncuestas servicio = ServicioEncuestas.getInstancia();
	
	@Context
	private UriInfo uriInfo;
	
//	String create(Encuesta encuesta) throws RepositorioException;
//	
//	boolean haVotado(String id, String usuario) throws RepositorioException, EntidadNoEncontrada;
//	
//	void votar(String id, int opcion, String usuario) throws RepositorioException, EntidadNoEncontrada;
//	
//	Encuesta getById(String id) throws RepositorioException, EntidadNoEncontrada;
//	
	
	// http://localhost:8080/api/encuestas/6238529d3e70df52228266a9
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") String id) throws Exception {
		
		
		Encuesta encuesta = servicio.getById(id);
		
		return Response.status(Status.OK).entity(encuesta).build();
	}
	
	
//	void remove(String id) throws RepositorioException, EntidadNoEncontrada;
//	
//	ListadoEncuestas getListadoResumen() throws RepositorioException, EntidadNoEncontrada;

	
	
	
}
