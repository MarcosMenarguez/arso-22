package retrofit.encuestas;

import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Opcion {

	private String texto;
	private LinkedList<String> votos = new LinkedList<>();
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LinkedList<String> getVotos() {
		return votos;
	}
	public void setVotos(LinkedList<String> votos) {
		this.votos = votos;
	}
	
	
}
