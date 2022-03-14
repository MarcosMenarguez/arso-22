package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://um.es/arso")
public interface Saludo {

	String getSaludo(String nombre);
}