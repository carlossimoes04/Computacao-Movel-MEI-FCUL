package cmov_68017.dicerollerdragshake

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cmov_68017.dicerollerdragshake.navigation.NavGraph
import cmov_68017.dicerollerdragshake.navigation.Screens
import cmov_68017.dicerollerdragshake.ui.theme.DiceRollerTheme

// for TP5
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import kotlin.math.sqrt
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

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

    val context = LocalContext.current

    // for dragging button
    var buttonOffsetX by remember { mutableFloatStateOf(0f) }
    var buttonOffsetY by remember { mutableFloatStateOf(0f) }

    // for throwing the dice
    var diceTargetX by remember { mutableFloatStateOf(0f) }
    var diceTargetY by remember { mutableFloatStateOf(0f) }
    val animatedDiceX by animateFloatAsState(targetValue = diceTargetX)
    val animatedDiceY by animateFloatAsState(targetValue = diceTargetY)

    // Accelerometer shake detection
    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        var lastCheckTime = 0L
        var lastShakeTime = 0L
        var lastX = 0f
        var lastY = 0f
        var lastZ = 0f

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val now = System.currentTimeMillis()

                if (now - lastCheckTime > 150) {
                    val deltaX = Math.abs(x - lastX)
                    val deltaY = Math.abs(y - lastY)
                    val deltaZ = Math.abs(z - lastZ)
                    val totalDelta = deltaX + deltaY + deltaZ

                    val gForce = sqrt(x * x + y * y + z * z) / SensorManager.GRAVITY_EARTH

                    if ((totalDelta > 5f || gForce > 1.8f) && (now - lastShakeTime > 500)) {
                        lastShakeTime = now

                        result = (1..6).filter { it != result }.random()
                    }

                    lastCheckTime = now
                    lastX = x
                    lastY = y
                    lastZ = z
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(
            listener,
            accelerometer,
            SensorManager.SENSOR_DELAY_UI
        )

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    /*
    remember is used to preserve this state in memory across recompositions; without it,
    the variable would be re-initialized back to 1 every time the composable function
    is re-executed

    mutableStateOf is used to create a state variable that can be observed by other composables;
    if the value does not change, the composable does not change either
    (it remains static, and images and text do not change)
    */

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> {R.drawable.dice_6}
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString(),
            modifier = Modifier
                .offset { IntOffset(animatedDiceX.roundToInt(), animatedDiceY.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            diceTargetX += dragAmount.x
                            diceTargetY += dragAmount.y
                        },
                        onDragEnd = {

                            if (Math.abs(diceTargetX) > 60 || Math.abs(diceTargetY) > 60) {
                                result = (1..6).filter { it != result }.random()
                            }

                            diceTargetX = 0f
                            diceTargetY = 0f
                        }
                    )
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        buttonOffsetX.roundToInt(),
                        buttonOffsetY.roundToInt()
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        buttonOffsetX += dragAmount.x
                        buttonOffsetY += dragAmount.y
                    }
                }
        ) {
            Button(
                onClick = { result = (1..6).random() }
            ) {
                Text(text = stringResource(R.string.roll), fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                when (result) {
                    1 -> navController.navigate(Screens.ScreenResult1.route)
                    2 -> navController.navigate(Screens.ScreenResult2.route)
                    3 -> navController.navigate(Screens.ScreenResult3.route)
                    4 -> navController.navigate(Screens.ScreenResult4.route)
                    5 -> navController.navigate(Screens.ScreenResult5.route)
                    else -> navController.navigate(Screens.ScreenResult6.route)
                }
            }
        ) {
            Text(stringResource(R.string.result_screen), fontSize = 24.sp)
        }
    }
}