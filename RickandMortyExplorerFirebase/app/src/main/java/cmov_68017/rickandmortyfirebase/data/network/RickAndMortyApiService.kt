package cmov_68017.rickandmortyfirebase.data.network

import cmov_68017.rickandmortyfirebase.data.model.CharacterResponse
import cmov_68017.rickandmortyfirebase.data.model.EpisodeResponse
import cmov_68017.rickandmortyfirebase.data.model.LocationResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// link for the api: https://rickandmortyapi.com/documentation/
/*
Base URL for the Rick and Morty API
- Defines the root URL for all the HTTP requests. The Retrofit concatenates this URL with the
paths defined on the @GET annotations
 */
private const val BASE_URL = "https://rickandmortyapi.com/api/"

/*
This was necessary due to the fact that the API returns non-declared fields (such as "next" and "prev"
in Info). Without this, Retrofit would throw an exception.
 */
private val json = Json {
    ignoreUnknownKeys = true
}

/* Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 * This is used for deserializing the JSON data from the API
 *
 * Associates the base URL with the conversor to transform the JSON data into Kotlin objects
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/*
@Query is used because it appends the filter parameter dynamically as a query string
in the request URL (e.g., "?name=morty"), allowing the user to filter results by name
directly through the API

When the parameter is null, Retrofit omits it automatically, returning all results
without filtering

- @GET: defines the specific resource path in the API (e.g., /character)
- suspend: indicates that the function runs asynchronously within a Coroutine without blocking the UI
- @Query("name"): dynamically adds '?name=value' to the URL for filtering
  If null, Retrofit omits the parameter and returns the full list
- Return: wrapper classes (e.g., CharacterResponse) that mirror the root JSON object
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

/*
Singleton

Provides a single point of access to the retrofit service with "lazy" instantiation
It is only created in memory when called for the first time
 */
object RickAndMortyApi {
    val retrofitService: RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }
}