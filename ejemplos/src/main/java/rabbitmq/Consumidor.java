package rabbitmq;

import java.io.IOException;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumidor {

	public static void main(String[] args) throws Exception {
		
		// TODO uri
		
		ConnectionFactory factory = new ConnectionFactory();
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
		        
		        // Confirma el procesamiento
		        channel.basicAck(deliveryTag, false);
		    }
		});
		
		 
		System.out.println("consumidor esperando ...");
		
	}
}
