package bookle.repositorio;

import repositorio.Repositorio;
import utils.PropertiesReader;

/*
 * Factoría que encapsula la implementación del repositorio.
 * 
 * Utiliza un fichero de propiedades para cargar la implementación.
 */

public class FactoriaRepositorioActividades {

	private static final String PROPIEDAD_IMPLEMENTACION = "bookle";
	
	private static RepositorioActividades repository = null;
	
	
	public static RepositorioActividades getRepositorio() {
		
		if (repository == null) {
			// Comprueba se existe una configuración específica para el repositorio
			try {
				PropertiesReader properties = new PropertiesReader(Repositorio.PROPERTIES);			
				String clase = properties.getProperty(PROPIEDAD_IMPLEMENTACION);
				repository = (RepositorioActividades) Class.forName(clase).getConstructor().newInstance();
			}
			catch (Exception e) {
				// Implementación por defecto
				repository = new RepositorioActividadesXML();
			}
			
		}
		return repository;
	}
	
}
