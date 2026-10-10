package cmov_68017.rickandmortyexplorer.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val count: Int,
    val pages: Int
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