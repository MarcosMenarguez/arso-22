package usuarios.servicio;

import java.util.List;

import usuarios.modelo.Usuario;

public interface IServicioUsuarios {

	Usuario findByEmail(String email);
	
	List<Usuario> findAll();	
}
