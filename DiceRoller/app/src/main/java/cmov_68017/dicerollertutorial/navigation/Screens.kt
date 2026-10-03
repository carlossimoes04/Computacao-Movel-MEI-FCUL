package cmov_68017.dicerollertutorial.navigation

sealed class Screens (val route: String) {
    object Roll : Screens("roll_screen")
    object DiceResult : Screens("result_screen/{result}")
}