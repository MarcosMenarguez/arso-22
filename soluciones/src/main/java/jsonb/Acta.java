package jsonb;

import java.util.LinkedList;
import java.util.List;

public class Acta {

	private String convocatoria;
	private int curso;
	private String asignatura;
	private List<Calificacion> calificaciones = new LinkedList<>();
	private List<Diligencia> diligencias = new LinkedList<>();
	
	public String getConvocatoria() {
		return convocatoria;
	}
	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int curso) {
		this.curso = curso;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	public List<Diligencia> getDiligencias() {
		return diligencias;
	}
	public void setDiligencias(List<Diligencia> diligencias) {
		this.diligencias = diligencias;
	}
	
}
