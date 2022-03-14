package encuestas.modelo;

import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Encuesta {

	// TODO: identificador
	@BsonId
	private ObjectId id;
	
	private String titulo;
	private String instruccion;
	private LocalDateTime apertura;
	private LocalDateTime cierre;
	private LinkedList<Opcion> opciones = new LinkedList<>();
	
	public String getIdentificador() {
		return this.id.toString();
	}
	
	public void setIdentificador(String identificador) {
		this.id = new ObjectId(identificador);
	}
	
	@XmlTransient
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
	
	
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
	
	@XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
	public LocalDateTime getApertura() {
		return apertura;
	}
	public void setApertura(LocalDateTime apertura) {
		this.apertura = apertura;
	}
	
	@XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
	public LocalDateTime getCierre() {
		return cierre;
	}
	public void setCierre(LocalDateTime cierre) {
		this.cierre = cierre;
	}
	public LinkedList<Opcion> getOpciones() {
		return opciones;
	}
	public void setOpciones(LinkedList<Opcion> opciones) {
		this.opciones = opciones;
	}
	
	// Propiedad calculada
	
	public int getNumeroOpciones() {
		
		return this.opciones.size();
	}

	@Override
	public String toString() {
		return "Encuesta [id=" + id + ", titulo=" + titulo + ", instruccion=" + instruccion + ", apertura=" + apertura
				+ ", cierre=" + cierre + ", opciones=" + opciones + "]";
	}
	
	
	
	
	
	
	
}
