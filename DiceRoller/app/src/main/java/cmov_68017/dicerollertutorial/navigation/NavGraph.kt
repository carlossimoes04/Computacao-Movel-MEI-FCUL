package cmov_68017.dicerollertutorial.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import cmov_68017.dicerollertutorial.DiceResult
import cmov_68017.dicerollertutorial.DiceWithButtonAndImage
import cmov_68017.dicerollertutorial.navigation.screens.*

@Composable
fun NavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Roll.route
    )
    {
        composable(route = Screens.Roll.route) {
            DiceWithButtonAndImage(navController = navController)
        }
        composable(route = Screens.DiceResult.route + "?result={result}") { navBackStack ->
            var resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceResult(navController = navController, resultShow = resultShow)
        }
        composable(route = Screens.ScreenResult1.route) {
            ScreenResult1(navController = navController)
        }
        composable(route = Screens.ScreenResult2.route) {
            ScreenResult2(navController = navController)
        }
        composable(route = Screens.ScreenResult3.route) {
            ScreenResult3(navController = navController)
        }
        composable(route = Screens.ScreenResult4.route) {
            ScreenResult4(navController = navController)
        }
        composable(route = Screens.ScreenResult5.route) {
            ScreenResult5(navController = navController)
        }
        composable(route = Screens.ScreenResult6.route) {
            ScreenResult6(navController = navController)
        }
    }
}
