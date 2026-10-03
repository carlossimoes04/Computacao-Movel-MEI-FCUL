package cmov_68017.dicerollertutorial.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import cmov_68017.dicerollertutorial.DiceResult
import cmov_68017.dicerollertutorial.DiceWithButtonAndImage

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
    }
}
