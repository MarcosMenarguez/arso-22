package bookle.servicio;

import java.io.File;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXParseException;

import es.um.bookle.Actividad;
import utils.Validador;

public class ValidadorBookle {

	public static final String ESQUEMA = "xml/bookle.xsd";

	public static List<SAXParseException> validar(Actividad actividad) {

		try {
			SchemaFactory factoriaSchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			// Construye el esquema
			Schema esquema = factoriaSchema.newSchema(new File(ESQUEMA));

			// Solicita al esquema la construcción de un validador
			Validator validador = esquema.newValidator();

			// Registra el manejador de eventos de error
			Validador miValidador = new Validador();
			validador.setErrorHandler(miValidador);

			// Solicita la validación de los objetos JAXB
			JAXBContext contexto = JAXBContext.newInstance("es.um.bookle");
			validador.validate(new JAXBSource(contexto, actividad));
			
			return miValidador.getErrores();
		} catch (Exception e) {

			return null; // No se ha realizado la validación 
		}

	}

	}
