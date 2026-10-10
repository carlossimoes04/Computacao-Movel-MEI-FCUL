package cmov_68017.rickandmortyexplorer.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val url: String,
    val id: Int? = null,
    val type: String? = null,
    val dimension: String? = null,
    val residents: List<String> = emptyList(),
    val created: String? = null
)