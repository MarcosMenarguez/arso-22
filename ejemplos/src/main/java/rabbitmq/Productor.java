package rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Productor {

	public static void main(String[] args) throws Exception {

		// TODO uri
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri("uri");

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		/** Declaración del Exchange **/

		final String exchangeName = "arso-exchange";
		
		boolean durable = true;
		channel.exchangeDeclare(exchangeName, "direct", durable);

		/** Envío del mensaje **/
		
		String mensaje = "hola";

		String routingKey = "arso";
		channel.basicPublish(exchangeName, routingKey, new AMQP.BasicProperties.Builder()
				// .contentType("application/json")
				.build(), mensaje.getBytes());

		channel.close();
		connection.close();

		System.out.println("fin.");

	}
}
