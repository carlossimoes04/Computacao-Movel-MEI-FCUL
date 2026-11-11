package cmov_68017.rickandmortyfirebase.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import cmov_68017.rickandmortyfirebase.ui.screens.components.CategorySelector
import cmov_68017.rickandmortyfirebase.ui.screens.components.ItemDisplayCard
import cmov_68017.rickandmortyfirebase.ui.screens.components.SearchFilterBar
import cmov_68017.rickandmortyfirebase.ui.screens.components.StatsCard
import cmov_68017.rickandmortyfirebase.ui.viewmodel.Category
import cmov_68017.rickandmortyfirebase.ui.viewmodel.RickAndMortyUiState
import cmov_68017.rickandmortyfirebase.ui.viewmodel.RickAndMortyViewModel
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: RickAndMortyViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiState = viewModel.rickAndMortyUiState
    var searchQuery by rememberSaveable { mutableStateOf("") }

    var showCamera by remember { mutableStateOf(false) }
    var lastCapturedLocation by remember { mutableStateOf<Location?>(null) }
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    @SuppressLint("MissingPermission")
    fun fetchLocationAndOpenCamera() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                lastCapturedLocation = location
                showCamera = true
            }
            .addOnFailureListener {
                showCamera = true
            }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            fetchLocationAndOpenCamera()
        } else {
            showCamera = true
        }
    }

    fun onLaunchCameraClick() {
        val hasFine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val hasCoarse = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (hasFine || hasCoarse) {
            fetchLocationAndOpenCamera()
        } else {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    // Se a câmara estiver ativa, exibe o ecrã de pré-visualização da câmara a ecrã inteiro
    if (showCamera) {
        CameraScreen(
            onPhotoCaptured = { photoUri ->
                showCamera = false
                viewModel.updateCurrentItemPhotoAndLocation(
                    photoUri = photoUri.toString(),
                    latitude = lastCapturedLocation?.latitude,
                    longitude = lastCapturedLocation?.longitude
                )
            },
            onClose = {
                showCamera = false
            }
        )
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Rick and Morty Explorer (Firebase)",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {
                is RickAndMortyUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is RickAndMortyUiState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Failed to load data or no results found.")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.getCharacter() }) {
                            Text(text = "Retry")
                        }
                    }
                }
                is RickAndMortyUiState.Success -> {
                    SuccessContent(
                        state = uiState,
                        searchQuery = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onCategorySelected = { category ->
                            searchQuery = ""
                            when (category) {
                                Category.CHARACTER -> viewModel.getCharacter()
                                Category.EPISODE -> viewModel.getEpisodes()
                                Category.LOCATION -> viewModel.getLocations()
                            }
                        },
                        onSearch = { category, query ->
                            val filter = query.ifBlank { null }
                            when (category) {
                                Category.CHARACTER -> viewModel.getCharacter(filter)
                                Category.EPISODE -> viewModel.getEpisodes(filter)
                                Category.LOCATION -> viewModel.getLocations(filter)
                            }
                        },
                        onRoll = { viewModel.rollRandomItem() },
                        onSaveItem = { viewModel.saveCurrentItem() },
                        onLoadLast = { viewModel.loadLastSavedItem() },
                        onTakePhoto = { onLaunchCameraClick() }
                    )
                }
            }
        }
    }
}

@Composable
fun SuccessContent(
    state: RickAndMortyUiState.Success,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onCategorySelected: (Category) -> Unit,
    onSearch: (Category, String) -> Unit,
    onRoll: () -> Unit,
    onSaveItem: () -> Unit,
    onLoadLast: () -> Unit,
    onTakePhoto: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CategorySelector(
            selectedCategory = state.currentCategory,
            onCategorySelected = onCategorySelected
        )

        SearchFilterBar(
            query = searchQuery,
            onQueryChange = onQueryChange,
            onSearchTriggered = {
                onSearch(state.currentCategory, searchQuery)
            },
            category = state.currentCategory
        )

        StatsCard(
            category = state.currentCategory,
            totalCount = state.totalCount,
            currentViewCount = state.currentViewCount,
            rollCount = state.rollCount
        )

        ItemDisplayCard(item = state.item)

        if (state.currentCategory == Category.LOCATION) {
            Button(
                onClick = onTakePhoto,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = "Camera Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Take Location Photo (GPS)", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f, fill = false))

        Button(
            onClick = onRoll,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = "Roll Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Roll Random Item", fontSize = 16.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = onSaveItem,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Bookmark, contentDescription = "Save Icon")
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Save Item")
            }

            Button(
                onClick = onLoadLast,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.CloudDownload, contentDescription = "Load Icon")
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Load Last")
            }
        }
    }
}