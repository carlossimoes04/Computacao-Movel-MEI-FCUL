package cmov_68017.rickandmortyfirebase.data.model

import cmov_68017.rickandmortyfirebase.ui.viewmodel.Category

data class SavedItem(
    val name: String = "",
    val imageUrl: String? = null,
    val firstLabel: String = "",
    val firstValue: String = "",
    val secondLabel: String = "",
    val secondValue: String = "",
    val category: Category = Category.CHARACTER,
    val timestamp: Long = System.currentTimeMillis()
)
