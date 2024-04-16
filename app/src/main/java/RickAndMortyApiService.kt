import com.sergi.rmbb.Personaje
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RickAndMortyApiService {
    @GET("character")
    fun getCharacters(): Call<RespuestaPersonajes>




    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Response<RespuestaPersonajes>


    @DELETE("character/{id}")
    fun deleteCharacter(@Path("id") characterId: Int): Call<Unit>

    @POST("character")
    fun insertCharacter(@Body personaje: Personaje): Call<Unit>
}
