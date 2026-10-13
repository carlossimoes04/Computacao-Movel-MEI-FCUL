package cmov_68017.rickandmortyexplorer.data.model

import kotlinx.serialization.Serializable

/*
Data Transfer Objects of the response

They strictly map the JSON format returned by the server:
{ "info": { "count": ..., "pages": ... }, "results": [ ... ] }

@Serializable allows kotlinx.serialization to automatically populate these fields
from the response body received from the network
 */

@Serializable
data class Info(
    val count: Int, // total number of existing records
    val pages: Int // total number of available pages
)

@Serializable
data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)

@Serializable
data class EpisodeResponse(
    val info: Info,
    val results: List<Episode>
)

@Serializable
data class LocationResponse(
    val info: Info,
    val results: List<Location>
)