package encuestas.rest;

import java.net.URI;
import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import encuestas.modelo.Encuesta;
import encuestas.rest.Listado.ResumenExtendido;
import encuestas.servicio.IServicioEncuestas;
import encuestas.servicio.ListadoEncuestas;
import encuestas.servicio.ListadoEncuestas.EncuestaResumen;
import encuestas.servicio.ServicioEncuestas;

@Path("encuestas")
public class EncuestasControladorRest {

	private IServicioEncuestas servicio = ServicioEncuestas.getInstancia();
	
	@Context
	private UriInfo uriInfo;
	
//	String create(Encuesta encuesta) throws RepositorioException;
//	
	
	// curl -i -X POST -H "Accept: application/json" -d @testfiles/1.json http://localhost:8080/api/encuestas/
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Encuesta encuesta) throws Exception {
		
		String id = servicio.create(encuesta);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		
		return Response.created(uri).build();
	}
	
	
//	boolean haVotado(String id, String usuario) throws RepositorioException, EntidadNoEncontrada;
//	
	
	// No la modelamos como operación REST
	
//	void votar(String id, int opcion, String usuario) throws RepositorioException, EntidadNoEncontrada;
//	
	
	// curl -i -X POST --data "usuario=juan@um.es" http://localhost:8080/api/encuestas/624187ec8cad6a2ce55ecdb7/opciones/1/votos
	
	@POST
	@Path("{id}/opciones/{opcion}/votos")
	public Response votar(@PathParam("id") String id, 
			@PathParam("opcion") int opcion, 
			@FormParam("usuario") String usuario) throws Exception {
		
		
		servicio.votar(id, opcion, usuario);
		
		return Response.noContent().build();
	}
	
	
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

	@DELETE
	@Path("{id}")
	public Response remove(String id) throws Exception {
		
		servicio.remove(id);
		
		return Response.noContent().build();
	}
	
	
//	ListadoEncuestas getListadoResumen() throws RepositorioException, EntidadNoEncontrada;

	// http://localhost:8080/api/encuestas
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListadoResumen() throws Exception {
		
		ListadoEncuestas listado = servicio.getListadoResumen();
		
		LinkedList<ResumenExtendido> resumenesExtendidos = new LinkedList<>();
		
		for (EncuestaResumen resumen : listado.getEncuestas()) {
			
			ResumenExtendido resumenExtendido = new ResumenExtendido();
			resumenExtendido.setResumen(resumen);
			
			String url = uriInfo.getAbsolutePathBuilder().path(resumen.getId()).build().toString();
			
			resumenExtendido.setUrl(url);
			
			resumenesExtendidos.add(resumenExtendido);
		}
		
		Listado resultado = new Listado();
		resultado.setEncuestas(resumenesExtendidos);
		
		return Response.ok(resultado).build();
	}
	
	
	
}
