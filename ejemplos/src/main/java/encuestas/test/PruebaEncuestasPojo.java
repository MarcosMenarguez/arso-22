package encuestas.test;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.time.LocalDateTime;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import encuestas.modelo.Encuesta;
import encuestas.modelo.Opcion;

public class PruebaEncuestasPojo {

    public static void main(String[] args) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://arso:arso22@cluster0.mp1o0.azure.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("arso").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Encuesta> collection = database.getCollection("encuestas", Encuesta.class);

            // Crear una encuesta
            
            Encuesta encuesta = new Encuesta();
    		encuesta.setTitulo("Examen parcial");
    		encuesta.setApertura(LocalDateTime.now());
    		encuesta.setCierre(LocalDateTime.now().plusDays(1)); // mañana
    		
    		Opcion opcion1 = new Opcion();
    		opcion1.setTexto("viernes tarde");
    		
    		Opcion opcion2 = new Opcion();
    		opcion2.setTexto("lunes tarde");
    		
    		encuesta.getOpciones().add(opcion1);
    		encuesta.getOpciones().add(opcion2);
            
    		collection.insertOne(encuesta);
    		
    		
            for (Encuesta encuesta1 : collection.find()) {
            	
            	System.out.println(encuesta1);
            }
            
        }
    }
}
