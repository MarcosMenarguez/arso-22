package encuestas.repositorio;

import java.util.LinkedList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import encuestas.modelo.Encuesta;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class RepositorioEncuestasMongoDB implements RepositorioEncuestas {

	
	private MongoCollection<Encuesta> encuestas;

	
	public RepositorioEncuestasMongoDB() {
		
		// Se obtiene la conexión de una variable de entorno
		
		String uriString = System.getenv("MONGODB_URI");
				
		ConnectionString connectionString = new ConnectionString(uriString);

		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		MongoClient mongoClient = MongoClients.create(clientSettings);
		
		MongoDatabase db = mongoClient.getDatabase("arso");
		
		this.encuestas = db.getCollection("encuestas", Encuesta.class);
		
	}

	/** Métodos de apoyo **/

	protected boolean checkDocument(ObjectId id) {

		Encuesta encuesta = encuestas.find(Filters.eq("_id", id)).first();
		
		return encuesta != null;
	}
	

	/** fin métodos de apoyo **/

	@Override
	public String add(Encuesta encuesta) throws RepositorioException {

		try {
			
			ObjectId id = new ObjectId();
			encuesta.setId(id);
			
			encuestas.insertOne(encuesta);
			
			return id.toString();
			
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido insertar la entidad", e);
		}
	}

	@Override
	public void update(Encuesta encuesta) throws RepositorioException, EntidadNoEncontrada {
			
		if (! checkDocument(encuesta.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + encuesta.getId());
		

		try {
			
			encuestas.replaceOne(Filters.eq("_id", encuesta.getId()), encuesta);
			
			
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido actualizar la entidad, id:" + encuesta.getId(), e);
		}

	}

	@Override
	public void delete(Encuesta encuesta) throws RepositorioException, EntidadNoEncontrada {
		
		if (! checkDocument(encuesta.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + encuesta.getId());
		
		try {
			encuestas.deleteOne(Filters.eq("_id", encuesta.getId()));
			
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido borrar la entidad, id:" + encuesta.getId(), e);
		}

	}

	@Override
	public Encuesta getById(String id) throws RepositorioException, EntidadNoEncontrada {

		Encuesta encuesta = encuestas.find(Filters.eq("_id", new ObjectId(id))).first();
		
		if (encuesta == null)
			throw new EntidadNoEncontrada("No existe la entidad con id:" + id);
		
		return encuesta;
	}

	@Override
	public List<Encuesta> getAll() throws RepositorioException {
		
		LinkedList<Encuesta> allEncuestas = new LinkedList<>();

		encuestas.find().into(allEncuestas);
		
		return allEncuestas;
	}

	@Override
	public List<String> getIds() {
		
		LinkedList<String> allIds = new LinkedList<String>();
		
		encuestas.find().map(e -> e.getId().toString()).into(allIds);
		
		
		return allIds;
	}
}