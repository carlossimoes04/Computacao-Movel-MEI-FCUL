package cmov_68017.rickandmortyexplorer.data.network

import cmov_68017.rickandmortyexplorer.data.model.Character
import cmov_68017.rickandmortyexplorer.data.model.CharacterResponse
import cmov_68017.rickandmortyexplorer.data.model.Episode
import cmov_68017.rickandmortyexplorer.data.model.EpisodeResponse
import cmov_68017.rickandmortyexplorer.data.model.Location
import cmov_68017.rickandmortyexplorer.data.model.LocationResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// link for the api: https://rickandmortyapi.com/documentation/
private const val BASE_URL = "https://rickandmortyapi.com/api/"

private val json = Json {
    ignoreUnknownKeys = true
}

/* Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 * This is used for deserializing the JSON data from the API
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/*
@Query is used because it appends the filter parameter dynamically as a query string
in the request URL (e.g., "?name=morty"), allowing the user to filter results by name
directly through the API.

When the parameter is null, Retrofit omits it automatically, returning all results
without filtering.
 */
interface RickAndMortyApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("name") name: String? = null
    ): CharacterResponse

    @GET("episode")
    suspend fun getEpisodes(
        @Query("name") name: String? = null
    ): EpisodeResponse

    @GET("location")
    suspend fun getLocations(
        @Query("name") name: String? = null
    ): LocationResponse

}

object RickAndMortyApi {
    val retrofitService: RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }
}