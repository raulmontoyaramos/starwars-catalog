package com.example.practicar_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.practicar_android.data.network.NetworkService
import com.example.practicar_android.data.network.model.CharacterRepository
import com.example.practicar_android.domain.model.StarWarsDatabase
import com.example.practicar_android.data.room.repository.OfflineCharactersRepository
import com.example.practicar_android.data.room.repository.OfflineFilmsRepository
import com.example.practicar_android.data.room.repository.OfflineWorldsRepository
import com.example.practicar_android.screens.CharacterDetailsScreen
import com.example.practicar_android.screens.CharacterDetailsViewModel
import com.example.practicar_android.screens.CharacterListScreen
import com.example.practicar_android.screens.CharacterListViewModel
import com.example.practicar_android.screens.FilmDetailsScreen
import com.example.practicar_android.screens.FilmDetailsViewModel
import com.example.practicar_android.screens.WorldDetailsScreen
import com.example.practicar_android.screens.WorldDetailsViewModel
import com.example.practicar_android.ui.theme.PracticarAndroidTheme

class MainActivity : ComponentActivity() {

    private val networkService = NetworkService()
    private val characterRepository = CharacterRepository()

    private val database by lazy { StarWarsDatabase.getDatabase(this) }
    private val offlineCharactersRepository by lazy { OfflineCharactersRepository(database.characterDao()) }
    private val offlineFilmsRepository by lazy { OfflineFilmsRepository(database.filmDao()) }
    private val offlineWorldsRepository by lazy { OfflineWorldsRepository(database.worldDao())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticarAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    Scaffold { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = CharacterList,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable<CharacterList> {
                                val viewModel =
                                    viewModel<CharacterListViewModel>(factory = viewModelFactory {
                                        CharacterListViewModel(
                                            navController = navController,
                                            networkService = networkService,
                                            characterRepository = characterRepository,
                                            charactersRepository = offlineCharactersRepository
                                        )
                                    })
                                CharacterListScreen(
                                    viewModel
                                )
                            }
                            composable<CharacterDetails> {
                                val characterId = it.toRoute<CharacterDetails>().characterId

                                println("CharacterDetails - characterId = $characterId")
                                val viewModel: CharacterDetailsViewModel =
                                    viewModel<CharacterDetailsViewModel>(factory = viewModelFactory {
                                        CharacterDetailsViewModel(
                                            navController = navController,
                                            networkService = networkService,
                                            characterRepository = characterRepository,
                                            characterId = characterId,
                                            filmsRepository = offlineFilmsRepository,
                                            worldsRepository = offlineWorldsRepository
                                        )
                                    })
                                CharacterDetailsScreen(
                                    viewModel
                                )
                            }
                            composable<WorldDetails> {
                                val worldId = it.toRoute<WorldDetails>().worldId

                                println("WorldDetails - worldId = $worldId")
                                val viewModel: WorldDetailsViewModel =
                                    viewModel<WorldDetailsViewModel>(factory = viewModelFactory {
                                        WorldDetailsViewModel(
                                            navController = navController,
                                            worldId = worldId,
                                            worldsRepository = offlineWorldsRepository
                                        )
                                    })
                                WorldDetailsScreen(
                                    viewModel
                                )
                            }
                            composable<FilmDetails> {
                                val filmId = it.toRoute<FilmDetails>().filmId

                                println("FilmDetails - filmId = $filmId")
                                val viewModel: FilmDetailsViewModel =
                                    viewModel<FilmDetailsViewModel>(factory = viewModelFactory {
                                        FilmDetailsViewModel(
                                            navController = navController,
                                            filmId = filmId,
                                            filmsRepository = offlineFilmsRepository
                                        )
                                    })
                                FilmDetailsScreen(
                                    viewModel
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <VM : ViewModel> viewModelFactory(createViewModel: () -> VM)
        : ViewModelProvider.Factory {

    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return createViewModel() as T
        }
    }
}
