package encuestas.rest;

import java.util.LinkedList;

import encuestas.servicio.ListadoEncuestas.EncuestaResumen;

public class Listado {

	public static class ResumenExtendido {
		
		private String url;
		private EncuestaResumen resumen;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public EncuestaResumen getResumen() {
			return resumen;
		}
		public void setResumen(EncuestaResumen resumen) {
			this.resumen = resumen;
		}
		
		
	}
	
	private LinkedList<ResumenExtendido> encuestas = new LinkedList<>();
	
	public LinkedList<ResumenExtendido> getEncuestas() {
		return encuestas;
	}
	
	public void setEncuestas(LinkedList<ResumenExtendido> encuestas) {
		this.encuestas = encuestas;
	}
}
