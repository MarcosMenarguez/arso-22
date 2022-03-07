package encuestas.servicio;

import java.time.LocalDateTime;

import encuestas.servicio.ListadoEncuestas.EncuestaResumen;
import encuestas.modelo.Encuesta;
import encuestas.modelo.Opcion;
import encuestas.repositorio.FactoriaRepositorioEncuestas;
import encuestas.repositorio.RepositorioEncuestas;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class ServicioEncuestas implements IServicioEncuestas {

	private RepositorioEncuestas repositorio = FactoriaRepositorioEncuestas.getRepositorio();
	
	/** Patrón Singleton **/
	
	private static ServicioEncuestas instancia;

	private ServicioEncuestas() {
		
	}
	
	public static ServicioEncuestas getInstancia() {

		if (instancia == null)
			instancia = new ServicioEncuestas();

		return instancia;
	}
	
	
	@Override
	public String create(Encuesta encuesta) throws RepositorioException {
		
		// Control de integridad de los datos
		
		// 1. Campos obligatorios
		if (encuesta.getTitulo() == null || encuesta.getTitulo().isEmpty())
			throw new IllegalArgumentException("titulo: no debe ser nulo ni vacio");
		
		if (encuesta.getApertura() == null)
			throw new IllegalArgumentException("fecha apertura: no debe ser nula");
		
		if (encuesta.getCierre() == null)
			throw new IllegalArgumentException("fecha cierre: no debe ser nula");
		
		if (encuesta.getCierre().isBefore(encuesta.getApertura()))
			throw new IllegalArgumentException("fecha cierre: debe ser posterior a la apertura");
		
		if (LocalDateTime.now().isAfter(encuesta.getCierre()))
			throw new IllegalArgumentException("fecha cierre: no debe estar en el pasado");
		
		if (encuesta.getOpciones() == null)
			throw new IllegalArgumentException("opciones: no debe ser una coleccion nula");
		
		for (Opcion opcion : encuesta.getOpciones()) {
			if (opcion.getTexto() == null || opcion.getTexto().isEmpty())
				throw new IllegalArgumentException("opcion, texto: no debe ser nulo ni vacio");
		
			if (opcion.getVotos() == null)
				throw new IllegalArgumentException("opciones: no debe ser una coleccion nula");
		}
		
		// TODO
		String id = repositorio.add(encuesta);
		
		return id;
	}

	@Override
	public boolean haVotado(String id, String usuario) throws RepositorioException, EntidadNoEncontrada {
		
		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
		
		// TODO
		Encuesta encuesta = repositorio.getById(id);
		
		for (Opcion opcion : encuesta.getOpciones())
			if (opcion.getVotos().contains(usuario))
				return true;
				
		return false;
		
	}

	@Override
	public void votar(String id, int opcion, String usuario) throws RepositorioException, EntidadNoEncontrada {
				
		// TODO
		Encuesta encuesta = repositorio.getById(id);
		
		if (opcion < 1 || opcion > encuesta.getOpciones().size() )
			throw new IllegalArgumentException("opcion: indice no valido");
		
		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
	
		if (haVotado(id, usuario))
			throw new IllegalArgumentException("usuario: ya ha votado");
		
		LocalDateTime ahora = LocalDateTime.now();
		
		if (ahora.isBefore(encuesta.getApertura()) || ahora.isAfter(encuesta.getCierre()))
			throw new IllegalArgumentException("la encuesta no esta abierta");
		// FIXME: estrictamente debería ser una excepcion de estado IllegalStateException
		
		int indice = opcion - 1; // desde 0
		
		// TODO
		encuesta.getOpciones().get(indice).getVotos().add(usuario);
		
	}

	@Override
	public Encuesta getById(String id) throws RepositorioException, EntidadNoEncontrada {
		
		// TODO
		return repositorio.getById(id);
	}

	@Override
	public void remove(String id) throws RepositorioException, EntidadNoEncontrada {
		
		// TODO
		Encuesta encuesta = repositorio.getById(id);
		
		repositorio.delete(encuesta);
		
	}

	@Override
	public ListadoEncuestas getListadoResumen() throws RepositorioException, EntidadNoEncontrada {
		
		ListadoEncuestas listado = new ListadoEncuestas();
		
		// TODO
		for (Encuesta encuesta : repositorio.getAll()) {
			EncuestaResumen resumen = new EncuestaResumen();
			resumen.setId(encuesta.getId().toString());
			resumen.setTitulo(encuesta.getTitulo());
			
			listado.getEncuestas().add(resumen);
			
		}
		
		
		return listado;
	}

}
