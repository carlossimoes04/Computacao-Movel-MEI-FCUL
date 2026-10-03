package cmov_68017.dicerollertutorial.navigation

sealed class Screens (val route: String) {
    object Roll : Screens("roll_screen")
    object DiceResult : Screens("result_screen/{result}")
    object ScreenResult1 : Screens("result_screen1")
    object ScreenResult2 : Screens("result_screen2")
    object ScreenResult3 : Screens("result_screen3")
    object ScreenResult4 : Screens("result_screen4")
    object ScreenResult5 : Screens("result_screen5")
    object ScreenResult6 : Screens("result_screen6")
}