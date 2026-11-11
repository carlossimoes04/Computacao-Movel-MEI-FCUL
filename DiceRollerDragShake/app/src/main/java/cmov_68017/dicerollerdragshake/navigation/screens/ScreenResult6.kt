package cmov_68017.dicerollertutorial.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cmov_68017.dicerollertutorial.R
import cmov_68017.dicerollertutorial.ui.theme.Purple40

@Composable
fun ScreenResult6(navController: NavHostController) {

    var diceValue by remember {mutableStateOf(1)}

    val imageResource = when (diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> {R.drawable.dice_6}
    }

    var hasRolled by remember { mutableStateOf(false) }

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
                text = "Dice Result: 6 (Dice Game)",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40, // from Color.kt
                modifier = Modifier.padding(12.dp),
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.weight(1f)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.dice_6),
                    contentDescription = "6"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.weight(1f)
            )
            {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = "New roll $diceValue"
                )
            }
        }
        if (hasRolled) {
            if (diceValue >= 6) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    )
                    {
                        Text(
                            text = "You won! ($diceValue ≥ 6)",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Purple40, // from Color.kt
                            modifier = Modifier.padding(12.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("result_screen$diceValue") },
                    // result_screen$diceValue is the route to any screen with the dice result
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                    modifier = Modifier.height(52.dp)
                ) {
                    Text(text = "Go To Result", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    )
                    {
                        Text(
                            text = "You lost! ($diceValue ≥ 6)",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            modifier = Modifier.padding(12.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Button(
            onClick = {
                diceValue = (1..6).random()
                hasRolled = true
                },
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        ) {
            Text(text = "Roll Second Dice", fontSize = 22.sp)
        }
        Button(
            onClick = { navController.navigate("roll_screen") },
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        ) {
            Text(text = "Back", fontSize = 22.sp)
        }
    }
}