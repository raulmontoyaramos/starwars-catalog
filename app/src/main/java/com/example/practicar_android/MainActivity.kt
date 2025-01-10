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
import com.example.practicar_android.di.AppComponent
import com.example.practicar_android.screens.CharacterDetailsScreen
import com.example.practicar_android.screens.CharacterDetailsViewModel
import com.example.practicar_android.screens.CharacterDetailsViewModelFactory
import com.example.practicar_android.screens.CharacterListScreen
import com.example.practicar_android.screens.CharacterListViewModel
import com.example.practicar_android.screens.CharacterListViewModelFactory
import com.example.practicar_android.screens.FilmDetailsScreen
import com.example.practicar_android.screens.FilmDetailsViewModel
import com.example.practicar_android.screens.FilmDetailsViewModelFactory
import com.example.practicar_android.screens.WorldDetailsScreen
import com.example.practicar_android.screens.WorldDetailsViewModel
import com.example.practicar_android.screens.WorldDetailsViewModelFactory
import com.example.practicar_android.ui.theme.PracticarAndroidTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var characterListViewModelFactory: CharacterListViewModelFactory
    @Inject
    lateinit var characterDetailsViewModelFactory: CharacterDetailsViewModelFactory
    @Inject
    lateinit var filmDetailsViewModelFactory: FilmDetailsViewModelFactory
    @Inject
    lateinit var worldDetailsViewModelFactory: WorldDetailsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (application as StarWarsApplication).appComponent
        appComponent.inject(this)
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
                                    viewModel<CharacterListViewModel>(
                                        factory = characterListViewModelFactory.create(
                                            navController = navController
                                        )
                                    )
                                CharacterListScreen(
                                    viewModel
                                )
                            }
                            composable<CharacterDetails> {
                                val characterId = it.toRoute<CharacterDetails>().characterId
                                println("CharacterDetails - characterId = $characterId")

                                val viewModel =
                                    viewModel<CharacterDetailsViewModel>(
                                        factory = characterDetailsViewModelFactory.create(
                                            navController = navController,
                                            characterId = characterId,
                                        ) )
                                CharacterDetailsScreen(
                                    viewModel
                                )
                            }
                            composable<WorldDetails> {
                                val worldId = it.toRoute<WorldDetails>().worldId
                                println("WorldDetails - worldId = $worldId")

                                val viewModel =
                                    viewModel<WorldDetailsViewModel>(
                                        factory = worldDetailsViewModelFactory.create(
                                            navController = navController,
                                            worldId = worldId
                                        ))
                                WorldDetailsScreen(
                                    viewModel
                                )
                            }
                            composable<FilmDetails> {
                                val filmId = it.toRoute<FilmDetails>().filmId

                                println("FilmDetails - filmId = $filmId")
                                val viewModel =
                                    viewModel<FilmDetailsViewModel>(
                                        factory = filmDetailsViewModelFactory.create(
                                            navController = navController,
                                            filmId = filmId
                                        ) )
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

//@Suppress("UNCHECKED_CAST")
//fun <VM : ViewModel> viewModelFactory(createViewModel: () -> VM)
//        : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T = createViewModel() as T
//}

fun viewModelFactory(create: () -> ViewModel): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = create() as T
    }
}
