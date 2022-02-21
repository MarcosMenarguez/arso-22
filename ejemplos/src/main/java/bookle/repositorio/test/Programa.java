package bookle.repositorio.test;

import java.util.Date;

import bookle.repositorio.FactoriaRepositorioActividades;
import bookle.repositorio.RepositorioActividades;
import es.um.bookle.Actividad;
import es.um.bookle.DiaActividad;
import es.um.bookle.Turno;
import utils.Utils;

public class Programa {

	public static void main(String[] args) throws Exception {

		RepositorioActividades repositorio = FactoriaRepositorioActividades.getRepositorio();

		// 1. Creación de una actividad

		Actividad actividad = new Actividad();
		actividad.setTitulo("Entrevistas de prácticas");
		actividad.setDescripcion("Enlace Zoom: ...");
		actividad.setProfesor("Marcos");
		actividad.setEmail("marcos@um.es");

		// Día 1

		DiaActividad dia1 = new DiaActividad();
		Date fecha1 = Utils.dateFromString("08-03-2022");
		dia1.setFecha(Utils.createFecha(fecha1));

		for (int hora = 10; hora <= 14; hora++) {

			Turno turno = new Turno();
			turno.setHorario(hora + ":00h");
			dia1.getTurno().add(turno);
		}

		actividad.getAgenda().add(dia1);

		// Día 2

		DiaActividad dia2 = new DiaActividad();
		Date fecha2 = Utils.dateFromString("09-03-2022");
		dia2.setFecha(Utils.createFecha(fecha2));

		for (int hora = 17; hora <= 19; hora++) {
			Turno turno = new Turno();
			turno.setHorario(hora + ":00h");
			dia2.getTurno().add(turno);
		}

		actividad.getAgenda().add(dia2);

		String id = repositorio.add(actividad);
		
		System.out.println("Actividad creada con id: " + id);
		
		System.out.println("fin.");

	}
}
