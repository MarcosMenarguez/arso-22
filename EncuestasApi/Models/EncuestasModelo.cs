
using System;
using System.Collections.Generic;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace Encuestas.Modelo {

    public class Encuesta {

        // Declaramos las propiedades

        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set;}
        public string Titulo { get; set; }
        public string Instrucciones { get; set;}

        public DateTime Apertura { get; set;} // Control + .
        public DateTime Cierre { get; set; }

        public List<Opcion> Opciones { get; set; } = new List<Opcion>();

        public int NumeroOpciones => Opciones.Count;

    }

    public class Opcion
    {
        public string Texto { get; set;}
        public List<string> Votos { get; set; } = new List<string>();
        public int NumeroVotos => Votos.Count;
    }
}