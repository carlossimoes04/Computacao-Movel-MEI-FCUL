package cmov_68017.rickandmortyexplorer.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmov_68017.rickandmortyexplorer.data.network.RickAndMortyApi
import cmov_68017.rickandmortyexplorer.ui.model.DisplayableItem
import kotlinx.coroutines.launch

class RickAndMortyViewModel : ViewModel() {

    /*
    Holds the observable screen state. When this value changes, Jetpack Compose
    automatically recomposes the associated visual elements
     */
    var rickAndMortyUiState: RickAndMortyUiState by mutableStateOf(RickAndMortyUiState.Loading)
        private set

    init {
        // Upon instantiating the ViewModel, immediately trigger the initial API load
        getCharacter()
    }

    fun getCharacter(query: String? = null) {
        // viewModelScope.launch creates an asynchronous coroutine
        // tied to the ViewModel's lifecycle, allowing 'suspend' functions
        // to be called without blocking the Main Thread
        viewModelScope.launch {
            rickAndMortyUiState = RickAndMortyUiState.Loading
            rickAndMortyUiState = try {
                val listResult = RickAndMortyApi.retrofitService.getCharacters(name = query)
                val displayableList = listResult.results.map { character ->
                    DisplayableItem(
                        name = character.name,
                        imageUrl = character.image,
                        firstLabel = "Status",
                        firstValue = character.status,
                        secondLabel = "Species",
                        secondValue = character.species
                    )
                }
                RickAndMortyUiState.Success(
                    item = displayableList.random(),
                    totalCount = listResult.info.count,
                    currentViewCount = displayableList.size,
                    items = displayableList,
                    currentCategory = Category.CHARACTER
                )
            } catch (e: Exception) {
                e.printStackTrace()
                RickAndMortyUiState.Error
            }
        }
    }

    fun getEpisodes(query: String? = null) {
        viewModelScope.launch {
            rickAndMortyUiState = RickAndMortyUiState.Loading
            rickAndMortyUiState = try {
                val listResult = RickAndMortyApi.retrofitService.getEpisodes(name = query)
                val displayableList = listResult.results.map { episode ->
                    DisplayableItem(
                        name = episode.name,
                        imageUrl = null, // episodes don't bring images from API
                        firstLabel = "Episode",
                        firstValue = episode.episode,
                        secondLabel = "Air Date",
                        secondValue = episode.airDate
                    )
                }
                if (displayableList.isNotEmpty()) {
                    RickAndMortyUiState.Success(
                        item = displayableList.random(),
                        totalCount = listResult.info.count,
                        currentViewCount = displayableList.size,
                        items = displayableList,
                        currentCategory = Category.EPISODE
                    )
                } else {
                    RickAndMortyUiState.Error
                }
            } catch (e: Exception) {
                e.printStackTrace()
                RickAndMortyUiState.Error
            }
        }
    }

    fun getLocations(query: String? = null) {
        viewModelScope.launch {
            rickAndMortyUiState = RickAndMortyUiState.Loading
            rickAndMortyUiState = try {
                val listResult = RickAndMortyApi.retrofitService.getLocations(name = query)
                val displayableList = listResult.results.map { location ->
                    DisplayableItem(
                        name = location.name,
                        imageUrl = null, // locations don't bring images from API
                        firstLabel = "Type",
                        firstValue = location.type ?: "Unknown",
                        secondLabel = "Dimension",
                        secondValue = location.dimension ?: "Unknown"
                    )
                }
                if (displayableList.isNotEmpty()) {
                    RickAndMortyUiState.Success(
                        item = displayableList.random(),
                        totalCount = listResult.info.count,
                        currentViewCount = displayableList.size,
                        items = displayableList,
                        currentCategory = Category.LOCATION
                    )
                } else {
                    RickAndMortyUiState.Error
                }
            } catch (e: Exception) {
                e.printStackTrace()
                RickAndMortyUiState.Error
            }
        }
    }

    fun rollRandomItem() {
        val currentState = rickAndMortyUiState
        if (currentState is RickAndMortyUiState.Success && currentState.items.isNotEmpty()) {
            rickAndMortyUiState = currentState.copy(
                item = currentState.items.random()
            )
        }
    }
}