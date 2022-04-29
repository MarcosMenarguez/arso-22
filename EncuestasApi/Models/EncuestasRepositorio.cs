
using Repositorio;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;
using MongoDB.Bson;
using Encuestas.Modelo;

namespace Encuestas.Repositorio
{

    public interface RepositorioEncuestas : Repositorio<Encuesta, string>
    {

        // Se pueden incluir métodos adicionales
    }

    public class RepositorioEncuestasMongoDB : RepositorioEncuestas
    {

        
        private readonly IMongoCollection<Encuesta> encuestas;

        public RepositorioEncuestasMongoDB()
        {
            var client = new MongoClient("uri");
            var database = client.GetDatabase("arso");

            encuestas = database.GetCollection<Encuesta>("encuestas.net");
        }

        public string Add(Encuesta entity)
        {
            encuestas.InsertOne(entity);

            return entity.Id;
        }

        public void Update(Encuesta entity)
        {
            encuestas.ReplaceOne(encuesta => encuesta.Id == entity.Id, entity);
        }

        public void Delete(Encuesta entity)
        {
            encuestas.DeleteOne(encuesta => encuesta.Id == entity.Id);
        }

        public List<Encuesta> GetAll()
        {
            return encuestas.Find(_ => true).ToList();
        }

        public Encuesta GetById(string id)
        {
            return encuestas
                .Find(encuesta => encuesta.Id == id)
                .FirstOrDefault();
        }

        public List<string> GetIds()
        {
            var todas =  encuestas.Find(_ => true).ToList();

            return todas.Select(p => p.Id).ToList();

        }
    }
}