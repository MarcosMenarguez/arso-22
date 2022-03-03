package jsonb;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

public class Programa {

	public static void main(String[] args) throws Exception {

		// Construcción del contexto
		JsonbConfig config = new JsonbConfig()
				.withNullValues(false)
				.withFormatting(true)
				.withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);

		Jsonb contexto = JsonbProvider.provider().create().withConfig(config).build();

		Reader entrada = new FileReader("json/acta.json");

		Acta acta = contexto.fromJson(entrada, Acta.class);

		for (Calificacion calificacion : acta.getCalificaciones()) {

			double nueva = Math.min(10, calificacion.getNota() + 0.5);

			calificacion.setNota(nueva);
		}
		
		contexto.toJson(acta, new PrintWriter("json/acta-json-b.json"));
		
		System.out.println("fin.");

	}
}
