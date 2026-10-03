package cmov_68017.dicerollertutorial.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cmov_68017.dicerollertutorial.ui.theme.*

@Composable
fun ScreenResult1(navController: NavHostController, modifier: Modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
    Column(modifier = modifier)
    {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        )
        {
            Text(
                text = "Dice Result: 1",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40, // from Color.kt
                modifier = Modifier.padding(12.dp)
            )
        }
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        )
        {
            Text(
                text = "Favorite Travel Destination"
            )
        }
    }
}