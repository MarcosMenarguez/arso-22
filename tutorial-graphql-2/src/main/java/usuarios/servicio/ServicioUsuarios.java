package usuarios.servicio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import usuarios.modelo.Usuario;

public class ServicioUsuarios implements IServicioUsuarios {

	private HashMap<String, Usuario> usuarios;
	
	/** Patrón Singleton **/
	
	private static ServicioUsuarios instancia;

	private ServicioUsuarios() {
		
		this.usuarios = new HashMap<>();
		
		Usuario usuario1 = new Usuario();
		usuario1.setNombre("Juan");
		usuario1.setEmail("juan@um.es");
		
		Usuario usuario2 = new Usuario();
		usuario2.setNombre("Pedro");
		usuario2.setEmail("pedro@um.es");
		
		this.usuarios.put(usuario1.getEmail(), usuario1);
		this.usuarios.put(usuario2.getEmail(), usuario2);

	}
	
	public static ServicioUsuarios getInstancia() {

		if (instancia == null)
			instancia = new ServicioUsuarios();

		return instancia;
	}

	
	@Override
	public Usuario findByEmail(String email) {
		
		return this.usuarios.get(email);
	}
	
	@Override
	public List<Usuario> findAll() {
		
		return new LinkedList<>(this.usuarios.values());
	}
	
}
