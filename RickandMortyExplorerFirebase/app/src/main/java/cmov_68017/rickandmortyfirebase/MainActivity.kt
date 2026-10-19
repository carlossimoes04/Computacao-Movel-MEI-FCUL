package cmov_68017.rickandmortyfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import cmov_68017.rickandmortyfirebase.ui.screens.HomeScreen
import cmov_68017.rickandmortyfirebase.ui.theme.RickAndMortyExplorerTheme
import cmov_68017.rickandmortyfirebase.ui.viewmodel.RickAndMortyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: RickAndMortyViewModel = viewModel()
            HomeScreen(viewModel = viewModel)
        }
    }
}