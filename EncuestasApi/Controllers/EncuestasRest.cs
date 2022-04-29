
using Encuestas.Modelo;
using Encuestas.Servicio;
using Microsoft.AspNetCore.Mvc;

namespace EncuestasApi.Controllers {

    [Route("api/encuestas")]
    [ApiController]
    public class EncuestasController : ControllerBase
    {

        private readonly IServicioEncuestas _servicio;

        public EncuestasController(IServicioEncuestas servicio) {

            this._servicio = servicio;
        }

        [HttpGet("{id}", Name = "GetEncuesta")]
        public ActionResult<Encuesta> Get(string id)
        {
            var entidad = _servicio.Get(id);

            if (entidad == null)
            {
                return NotFound();
            }

            return entidad;
        }

        [HttpPost]
        public ActionResult<Encuesta> Create(Encuesta encuesta)
        {
            _servicio.Create(encuesta);

            return CreatedAtRoute("GetEncuesta", new { id = encuesta.Id }, encuesta);
        }


    }

}