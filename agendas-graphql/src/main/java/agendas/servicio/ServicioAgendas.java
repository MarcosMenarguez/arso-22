package agendas.servicio;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.json.bind.Jsonb;
import javax.json.bind.spi.JsonbProvider;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import agendas.modelo.Cita;
import bookle.eventos.EventoReservaCreada;
import io.leangen.graphql.annotations.GraphQLQuery;

public class ServicioAgendas implements IServicioAgendas {

	// patrón singleton

	private static ServicioAgendas instancia;

	public static ServicioAgendas getInstancia() {

		if (instancia == null)
			instancia = new ServicioAgendas();

		return instancia;
	}

	// Atributos
	private HashMap<String, List<Cita>> citas;
	
	private ServicioAgendas() {
		
		this.citas = new HashMap<>();
		
		// relleno
		Cita cita = new Cita();
		cita.setTitulo("prueba");
		cita.setDescripcion("descripcion");
		LinkedList<Cita> lista = new LinkedList<>();
		lista.add(cita);
		
		this.citas.put("pepe@um.es", lista);
		
		// registro consumidor eventos
		
		try {
		ConnectionFactory factory = new ConnectionFactory();
		// TODO uri
		factory.setUri("uri");
		
	    Connection connection = factory.newConnection();

	    Channel channel = connection.createChannel();
	    
	    /** Declaración de la cola y enlace con el exchange **/

		final String exchangeName = "arso-exchange";
		final String queueName = "arso-queue";
		final String bindingKey = "arso";

		boolean durable = true;
		boolean exclusive = false;
		boolean autodelete = false;
		Map<String, Object> properties = null; // sin propiedades
		channel.queueDeclare(queueName, durable, exclusive, autodelete, properties);

		channel.queueBind(queueName, exchangeName, bindingKey);
	    
		/** Configuración del consumidor **/
		
		boolean autoAck = false;
		String cola = "arso-queue";
		String etiquetaConsumidor = "arso-consumidor";
		
		// Consumidor push
		
		channel.basicConsume(cola, autoAck, etiquetaConsumidor, 
		  
		  new DefaultConsumer(channel) {
		    @Override
		    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
		            byte[] body) throws IOException {
		        
		        String routingKey = envelope.getRoutingKey();
		        String contentType = properties.getContentType();
		        long deliveryTag = envelope.getDeliveryTag();

		        String contenido = new String(body);

		        System.out.println(contenido);
		        
				Jsonb contexto = JsonbProvider.provider().create().build();

			    EventoReservaCreada evento = contexto.fromJson(contenido, 
			    		EventoReservaCreada.class);

			    // Procesamos el evento
			    Cita cita = new Cita();
			    cita.setTitulo(evento.getTitulo());
			    cita.setDescripcion(evento.getDescripcion());
			    for (String usuario : evento.getUsuarios()) {
			    	citas.putIfAbsent(usuario, new LinkedList<>());
			    	citas.get(usuario).add(cita);
			    	
			    }
		        
		        // Confirma el procesamiento
		        channel.basicAck(deliveryTag, false);
		    }
		});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		
	}
	
	@GraphQLQuery
	@Override
	public List<Cita> getCitas(String usuario) {
		
		return this.citas.get(usuario);
	}

}
