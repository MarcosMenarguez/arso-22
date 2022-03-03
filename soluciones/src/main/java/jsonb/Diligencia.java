package jsonb;

import java.time.LocalDate;

public class Diligencia extends Calificacion {

	private LocalDate fecha;
	private boolean extraordinaria;
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public boolean isExtraordinaria() {
		return extraordinaria;
	}
	public void setExtraordinaria(boolean extraordinaria) {
		this.extraordinaria = extraordinaria;
	}
	
	
}
