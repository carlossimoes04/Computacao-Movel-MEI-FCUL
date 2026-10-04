package cmov_68017.dicerollertutorial.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cmov_68017.dicerollertutorial.DiceResult
import cmov_68017.dicerollertutorial.R
import cmov_68017.dicerollertutorial.ui.theme.Purple40

@Composable
fun ScreenResult2(navController: NavHostController) {

    var diceValue by remember {mutableStateOf(2)}

    val imageResource = when (diceValue) {
        2 -> R.drawable.dice_2
        4 -> R.drawable.dice_4
        else -> {R.drawable.dice_6}
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        )
        {
            Text(
                text = "Dice Result: 2",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40, // from Color.kt
                modifier = Modifier.padding(12.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = diceValue.toString()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (diceValue < 6) diceValue += 2 },
            // dices only have 6 sides, so the max value is 6
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        ){
            Text(text = "Increase dice value by 2", fontSize = 22.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        )
        {
            Text(
                text = "Current dice value: $diceValue",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40, // from Color.kt
                modifier = Modifier.padding(12.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("roll_screen") },
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        ){
            Text(text = "Back to Dice", fontSize = 22.sp)
        }
    }
}