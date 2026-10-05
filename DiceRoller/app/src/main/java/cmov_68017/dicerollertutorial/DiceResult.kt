package cmov_68017.dicerollertutorial

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cmov_68017.dicerollertutorial.navigation.Screens

@Composable
fun DiceResult(navController: NavHostController, resultShow: Int, modifier: Modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
    var result by remember {  mutableStateOf(resultShow) } // it's var because the dice result isn't always the same
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

        Button (onClick = { navController.navigate(Screens.Roll.route) }
        ){
            Text(stringResource(R.string.back), fontSize = 24.sp)
        }
    }
}