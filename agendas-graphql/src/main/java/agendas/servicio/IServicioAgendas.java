package agendas.servicio;

import java.util.List;

import agendas.modelo.Cita;

public interface IServicioAgendas {

	List<Cita> getCitas(String usuario);
}
