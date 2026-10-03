package cmov_68017.dicerollertutorial.navigation.screens

import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cmov_68017.dicerollertutorial.ui.theme.*

@Composable
fun ScreenResult1(navController: NavHostController) {
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
                text = "Dice Result: 1",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40, // from Color.kt
                modifier = Modifier.padding(12.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        )
        {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Favorite Travel Destination",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleGrey40,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Tokyo, Japan",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleGrey40,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )

                var mockTemp by remember { mutableStateOf(0) }

                Text(
                    text = "Mock Temperature: $mockTemp°C",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleGrey40,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        mockTemp = (15..35).random()
                    }
                ){
                    Text(text = "Refresh Temperature", fontSize = 22.sp)
                }
            }
        }
    }
}