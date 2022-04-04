package retrofit.encuestas;

import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Encuesta {

	private String titulo;
	private String instruccion;
	private String apertura; // simplificación
	private String cierre;
	private LinkedList<Opcion> opciones = new LinkedList<>();
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getInstruccion() {
		return instruccion;
	}
	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}
	public String getApertura() {
		return apertura;
	}
	public void setApertura(String apertura) {
		this.apertura = apertura;
	}
	public String getCierre() {
		return cierre;
	}
	public void setCierre(String cierre) {
		this.cierre = cierre;
	}
	public LinkedList<Opcion> getOpciones() {
		return opciones;
	}
	public void setOpciones(LinkedList<Opcion> opciones) {
		this.opciones = opciones;
	}
	
	
}
