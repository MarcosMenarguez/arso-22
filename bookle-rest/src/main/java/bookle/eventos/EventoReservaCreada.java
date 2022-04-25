package bookle.eventos;

import java.util.LinkedList;

public class EventoReservaCreada {

	private String titulo;
	private String descripcion;
	private LinkedList<String> usuarios = new LinkedList<>();
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LinkedList<String> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(LinkedList<String> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
}
