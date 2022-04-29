
using Encuestas.Modelo;
using Encuestas.Repositorio;

namespace Encuestas.Servicio {

    public interface IServicioEncuestas {

        public string Create(Encuesta encuesta);
        public Encuesta Get(string id);

        public void Votar(string id, int indice, string usuario);

    }


    public class ServicioEncuestas : IServicioEncuestas
    {

        private readonly RepositorioEncuestas repositorio;

        public ServicioEncuestas(RepositorioEncuestas repositorio) {

            this.repositorio = repositorio;            
        }

        public string Create(Encuesta encuesta)
        {
           return repositorio.Add(encuesta);
        }

        public Encuesta Get(string id)
        {
            return repositorio.GetById(id);
        }

        public void Votar(string id, int indice, string usuario)
        {
            throw new System.NotImplementedException();
        }
    }


}