package cmov_68017.dicerollertutorial.navigation.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cmov_68017.dicerollertutorial.R
import cmov_68017.dicerollertutorial.navigation.Screens
import cmov_68017.dicerollertutorial.ui.theme.Purple20
import cmov_68017.dicerollertutorial.ui.theme.Purple40

@Composable
fun ScreenResult4(navController: NavHostController) {

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
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Dice Result: 4",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple40, // from Color.kt
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Choose a screen to go to:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple20, // from Color.kt
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = { navController.navigate(Screens.ScreenResult1.route) },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(stringResource(R.string.result_screen1), fontSize = 20.sp)
                }
                Button(
                    onClick = { navController.navigate(Screens.ScreenResult2.route) },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(stringResource(R.string.result_screen2), fontSize = 20.sp)
                }
                Button(
                    onClick = { navController.navigate(Screens.ScreenResult3.route) },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(stringResource(R.string.result_screen3), fontSize = 20.sp)
                }
                Button(
                    onClick = { navController.navigate(Screens.ScreenResult4.route) },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(stringResource(R.string.result_screen4), fontSize = 20.sp)
                }
                Button(
                    onClick = { navController.navigate(Screens.ScreenResult5.route) },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(stringResource(R.string.result_screen5), fontSize = 20.sp)
                }
                Button(
                    onClick = { navController.navigate(Screens.ScreenResult6.route) },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(stringResource(R.string.result_screen6), fontSize = 20.sp)
                }
            }
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