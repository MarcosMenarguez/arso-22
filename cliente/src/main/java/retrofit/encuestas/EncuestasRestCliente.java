package retrofit.encuestas;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EncuestasRestCliente {

	@POST("encuestas")
	Call<Void> createEncuesta(@Body Encuesta encuesta);
	
	@GET("encuestas/{id}")
	Call<Encuesta> getEncuesta(@Path("id") String id);

}
