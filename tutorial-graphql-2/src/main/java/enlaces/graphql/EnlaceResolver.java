package enlaces.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;

import enlaces.modelo.Enlace;
import usuarios.modelo.Usuario;
import usuarios.servicio.ServicioUsuarios;

public class EnlaceResolver implements GraphQLResolver<Enlace> {

	public Usuario publicadoPor(Enlace enlace) {
		
		return ServicioUsuarios.getInstancia().findByEmail(enlace.getEmailUsuario());
	}
	
}
