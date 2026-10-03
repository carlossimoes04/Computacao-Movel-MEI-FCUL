package cmov_68017.dicerollertutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cmov_68017.dicerollertutorial.navigation.NavGraph
import cmov_68017.dicerollertutorial.navigation.Screens
import cmov_68017.dicerollertutorial.ui.theme.DiceRollerTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(navController: NavHostController, modifier: Modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)){
    var result by remember {  mutableStateOf(1) } // it's var because the dice result isn't always the same
    // remember was used to save the state of the result variable

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> {R.drawable.dice_6}
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { result = (1..6).random() }
        ) {
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button (
            onClick = {
                when (result) {
                    1 -> {
                        navController.navigate(Screens.ScreenResult1.route)
                    }
                    2 -> {
                        navController.navigate(Screens.ScreenResult2.route)
                    }
                    3 -> {
                        navController.navigate(Screens.ScreenResult3.route)
                    }
                    4 -> {
                        navController.navigate(Screens.ScreenResult4.route)
                    }
                    5 -> {
                        navController.navigate(Screens.ScreenResult5.route)
                    }
                    else -> {
                        navController.navigate(Screens.ScreenResult6.route)
                    }
                }
            }
        ) {
            Text(stringResource(R.string.result_screen), fontSize = 24.sp)
        }
    }
}