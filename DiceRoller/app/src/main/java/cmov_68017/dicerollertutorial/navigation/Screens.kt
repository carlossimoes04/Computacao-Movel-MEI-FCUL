package cmov_68017.dicerollertutorial.navigation

/*
Screens is a sealed class because it defines a closed, restricted hierarchy of all possible
navigation routes

This ensures type safety and enables exhaustive `when` statements, preventing typos or
invalid screen destinations

According to https://kotlinlang.org/docs/sealed-classes.html:
"Sealed classes are best used for scenarios when:
Limited class inheritance is desired: You have a predefined, finite set of subclasses
that extend a class, all of which are known at compile time." which is this case
 */
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