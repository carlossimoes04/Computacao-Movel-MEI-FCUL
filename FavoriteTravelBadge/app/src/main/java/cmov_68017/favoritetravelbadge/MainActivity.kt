package cmov_68017.favoritetravelbadge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmov_68017.favoritetravelbadge.ui.theme.FavoriteTravelBadgeTheme

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FavoriteTravelBadgeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TravelBadgeScreen()
                }
            }
        }
    }
}

@Composable
fun TravelBadgeScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            // source: https://medium.com/@josephajire/how-to-add-background-image-to-your-android-project-with-jetpack-compose-1c5392967fd5
            painter = painterResource(id = R.drawable.background), // sets the background image
            contentDescription = "Background Image", // sets the content description
            modifier = Modifier.fillMaxSize(), // fills the entire screen
            contentScale = ContentScale.Crop, // crops the image to fill the screen
            alpha = 0.5f // applies opacity to the image
        )

        Column( // sets the column layout for all the children components
            modifier = Modifier
                .fillMaxSize() // fills the entire screen
                .padding(24.dp), // this modifier fills the max size of the screen adding a padding of 24dp
            horizontalAlignment = Alignment.CenterHorizontally,
            // aligns the items at the center of the column width
            verticalArrangement = Arrangement.Center
            // aligns the items at the half way of the available height
            // source: https://medium.com/@dhivyakgf/arrangement-vs-alignment-in-jetpack-compose-whats-the-real-difference-b63c9b5dc9f3
            /*
            Arrangement:
            - Row -> Horizontal, Column -> Vertical, controls space between items
            Alignment:
            - Row -> Vertical, Column -> Horizontal, controls position of items inside the layout
            */
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, // aligns the children vertically
                horizontalArrangement = Arrangement.Center, // aligns the children horizontally
                modifier = Modifier.fillMaxWidth() // fills the max size of the screen width
            ) {
                Card(
                    modifier = Modifier.size(140.dp), // size of the card (h and w)
                    shape = RoundedCornerShape(20.dp), // shape of the card
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    // elevation of the card -> the card looks like is floating above the background
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    // colors of the card
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.my_photo),
                            contentDescription = "Travel Badge",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(14.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp)) // adds a space between the children

                Column (
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Carlos Simões",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Dream Destination:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Tokyo",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Image(
                            painter = painterResource(id = R.drawable.japan_flag),
                            contentDescription = "Japan Flag",
                            modifier = Modifier
                                .width(36.dp)
                                .height(24.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Currency",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "¥ JPY (Yen)",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Language",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Japanese",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Time",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        // source:
                        // https://www.geeksforgeeks.org/java/java-program-to-display-time-in-different-country-format/#:~:text=Method%204%3A%20Using%20Locale%20with%20ZonedDateTime
                        val dateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
                        val tokyoTime = dateTime.format(
                            DateTimeFormatter.ofPattern(
                                "HH:mm", Locale.getDefault()
                            )
                        )

                        Text(
                            text = tokyoTime,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Places to visit:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(8.dp)) // Espaço entre o título e os locais

                        Text(
                            text = "• Shibuya\n• Tokyo Skytree\n• Sensoji Temple",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {

                        Spacer(modifier = Modifier.height(26.dp))

                        Text(
                            text = "• Pokémon Center MEGA TOKYO\n• Tokyo Tower\n• Harajuku",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        // weight is used inside row or column layouts to divide space proportionally
                        // source: https://medium.com/@dhivyakgf/weight-in-jetpack-compose-91966cd4e0f8
                        .height(115.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pokemon_tokyo),
                        contentDescription = "Pokémon Center MEGA TOKYO",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Card(
                    modifier = Modifier
                        .weight(1f)
                        // weight is used inside row or column layouts to divide space proportionally
                        .height(115.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sensoji),
                        contentDescription = "Spot 2",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Card(
                    modifier = Modifier
                        .weight(1f)
                        // weight is used inside row or column layouts to divide space proportionally
                        .height(115.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.shibuya),
                        contentDescription = "Spot 3",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

// allows seeing the result on "split" or "design" mode
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TravelBadgePreview() {
    TravelBadgeScreen()
}