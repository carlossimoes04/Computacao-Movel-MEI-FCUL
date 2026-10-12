package cmov_68017.rickandmortyexplorer.ui.viewmodel

import cmov_68017.rickandmortyexplorer.ui.model.DisplayableItem

sealed interface RickAndMortyUiState {
    data class Success(
        val item: DisplayableItem, // item currently being displayed on the screen
        val totalCount: Int, // total number of records on the API for the current category
        val currentViewCount: Int, // number of records displayed on the screen
        val items: List<DisplayableItem>, // list of items to be brought by the response
        val currentCategory: Category = Category.CHARACTER // current category being displayed
    ) : RickAndMortyUiState
    object Error : RickAndMortyUiState
    object Loading : RickAndMortyUiState
}

enum class Category {
    CHARACTER, EPISODE, LOCATION
}