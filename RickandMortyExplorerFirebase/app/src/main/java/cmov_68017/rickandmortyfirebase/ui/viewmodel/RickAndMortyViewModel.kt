package cmov_68017.rickandmortyfirebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmov_68017.rickandmortyfirebase.data.model.SavedItem
import cmov_68017.rickandmortyfirebase.data.network.FirebaseRepository
import cmov_68017.rickandmortyfirebase.data.network.RickAndMortyApi
import cmov_68017.rickandmortyfirebase.ui.model.DisplayableItem
import kotlinx.coroutines.launch

class RickAndMortyViewModel : ViewModel() {

    private val firebaseRepository = FirebaseRepository()

    /*
    Holds the observable screen state. When this value changes, Jetpack Compose
    automatically recomposes the associated visual elements
     */
    var rickAndMortyUiState: RickAndMortyUiState by mutableStateOf(RickAndMortyUiState.Loading)
        private set

    private var currentSyncedRolls: Int = 0

    init {
        firebaseRepository.observeRollCount { count ->
            currentSyncedRolls = count
            val current = rickAndMortyUiState
            if (current is RickAndMortyUiState.Success) {
                rickAndMortyUiState = current.copy(rollCount = count)
            }
        }
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
            firebaseRepository.incrementRollCount()
            rickAndMortyUiState = currentState.copy(
                item = currentState.items.random()
            )
        }
    }

    fun saveCurrentItem() {
        val currentState = rickAndMortyUiState as? RickAndMortyUiState.Success ?: return
        viewModelScope.launch {
            val itemToSave = SavedItem(
                name = currentState.item.name,
                imageUrl = currentState.item.imageUrl,
                firstLabel = currentState.item.firstLabel,
                firstValue = currentState.item.firstValue,
                secondLabel = currentState.item.secondLabel,
                secondValue = currentState.item.secondValue,
                category = currentState.currentCategory
            )
            firebaseRepository.saveItem(itemToSave)
        }
    }

    fun loadLastSavedItem() {
        viewModelScope.launch {
            val lastSaved = firebaseRepository.getLastSavedItem() ?: return@launch

            val loadedItem = DisplayableItem(
                name = lastSaved.name,
                imageUrl = lastSaved.imageUrl,
                firstLabel = lastSaved.firstLabel,
                firstValue = lastSaved.firstValue,
                secondLabel = lastSaved.secondLabel,
                secondValue = lastSaved.secondValue
            )

            val (categoryList, total) = try {
                when (lastSaved.category) {
                    Category.CHARACTER -> {
                        val res = RickAndMortyApi.retrofitService.getCharacters()
                        Pair(
                            res.results.map {
                                DisplayableItem(it.name, it.image, "Status", it.status, "Species", it.species)
                            },
                            res.info.count
                        )
                    }
                    Category.EPISODE -> {
                        val res = RickAndMortyApi.retrofitService.getEpisodes()
                        Pair(
                            res.results.map {
                                DisplayableItem(it.name, null, "Episode", it.episode, "Air Date", it.airDate)
                            },
                            res.info.count
                        )
                    }
                    Category.LOCATION -> {
                        val res = RickAndMortyApi.retrofitService.getLocations()
                        Pair(
                            res.results.map {
                                DisplayableItem(it.name, null, "Type", it.type ?: "Unknown", "Dimension", it.dimension ?: "Unknown")
                            },
                            res.info.count
                        )
                    }
                }
            } catch (e: Exception) {
                Pair(listOf(loadedItem), 1)
            }

            rickAndMortyUiState = RickAndMortyUiState.Success(
                item = loadedItem,
                totalCount = total,
                currentViewCount = categoryList.size,
                items = categoryList,
                currentCategory = lastSaved.category,
                rollCount = currentSyncedRolls
            )
        }
    }
}