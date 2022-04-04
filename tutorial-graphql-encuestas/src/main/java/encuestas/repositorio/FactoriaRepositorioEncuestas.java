package encuestas.repositorio;

import repositorio.Repositorio;
import utils.PropertiesReader;

public class FactoriaRepositorioEncuestas {

	private static final String PROPIEDAD_IMPLEMENTACION = "encuestas";
	
	private static RepositorioEncuestas repository = null;
	
	public static RepositorioEncuestas getRepositorio() {
		if (repository == null) {
			// Comprueba se existe una configuración específica para el repositorio
			try {
				PropertiesReader properties = new PropertiesReader(Repositorio.PROPERTIES);			
				String clase = properties.getProperty(PROPIEDAD_IMPLEMENTACION);
				repository = (RepositorioEncuestas) Class.forName(clase).getConstructor().newInstance();
			}
			catch (Exception e) {
				// Implementación por defecto
				repository = new RepositorioEncuestasMongoDB();
			}
			
		}
		return repository;
	}
	
	
}
