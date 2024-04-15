import com.sergi.rmbb.Personaje
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApiService {
    @GET("character")
    fun getCharacters(): Call<RespuestaPersonajes>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<Personaje>
}
