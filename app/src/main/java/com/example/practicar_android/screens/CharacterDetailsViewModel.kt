package com.example.practicar_android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.practicar_android.FilmDetails
import com.example.practicar_android.WorldDetails
import com.example.practicar_android.data.network.NetworkService
import com.example.practicar_android.data.network.model.CharacterRepository
import com.example.practicar_android.domain.model.Character
import com.example.practicar_android.domain.model.Film
import com.example.practicar_android.domain.model.World
import com.example.practicar_android.domain.model.repositories.FilmsRepository
import com.example.practicar_android.domain.model.repositories.WorldsRepository
import com.example.practicar_android.domain.model.util.extractIdFromUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailsViewModel(
    private val networkService: NetworkService,
    private val navController: NavController,
    characterId: String,
    private val characterRepository: CharacterRepository,
    private val filmsRepository: FilmsRepository,
    private val worldsRepository: WorldsRepository
) : ViewModel() {

    val viewState = MutableStateFlow(
        CharacterDetailsViewState(
            character = null,
            world = null,
            films = emptyList()
        )
    )

    init {
        viewModelScope.launch {
            filmsRepository.getAllFilms().collect { films ->
                viewState.update {
                    it.copy(films = films)
                }
            }
        }
        fetchCharacter(characterId)
    }

    private fun fetchCharacter(characterId: String) {
        viewModelScope.launch {
            try {
                val character = withContext(Dispatchers.IO){
                    networkService.getCharacter(characterId).also { characterRepository.setCharacter(it) }
                }
                println("CharacterDetailsViewModel - CharacterDetails = $character")
                character.let {
                    fetchWorld(character.homeworld.extractIdFromUrl())
                    viewState.update {
                        it.copy(
                            character = character
                        )
                    }
                    character.films.map { filmUrl ->
                        println("CharacterDetailsViewModel - filmUlr: $filmUrl")
                        fetchFilm(filmUrl.extractIdFromUrl())
                    }
                }

            } catch (exception: Exception) {
                println("CharacterDetailsViewModel - CharacterDetails = Error Loading Character ${exception.message}")
            }
        }
    }

    private fun fetchFilm(filmId: String) {
        println("CharacterDetailsViewModel - fetchFilm - filmId: $filmId")
        viewModelScope.launch {
            try {
                val film = withContext(Dispatchers.IO) {
                    networkService.getFilm(filmId).also { apiFilm ->
                        filmsRepository.insertFilm(apiFilm) }
                }
                println("CharacterDetailsViewModel - fetchFilm - Film = $film")
//                viewState.update {
//                    it.copy(
//                        films = it.films + film
//                    )
//                }
            } catch(exception: Exception){
                println("CharacterDetailsViewModel - Film = Error Loading Film ${exception.message}")
            }
        }
    }

    private fun fetchWorld(worldId: String) {
        viewModelScope.launch {
            try {
                val world = withContext(Dispatchers.IO) {
                    networkService.getWorld(worldId).also { apiWorld ->
                        worldsRepository.insertWorld(apiWorld) }
                }
                println("CharacterDetailsViewModel - World = $world")
                viewState.update {
                    it.copy(
                        world = world
                    )
                }
            } catch (exception: Exception) {
                println("CharacterDetailsViewModel - World = Error Loading World ${exception.message}")
            }
        }
    }

    fun onBackButtonClicked() = navController.navigateUp()

    fun onFilmClicked(filmId: String) {
        println("CharacterDetailsViewModel - onFilmClicked - filmId = $filmId")
        navController.navigate(FilmDetails(filmId))
    }

    fun onWorldClicked(worldId: String) {
        println("CharacterDetailsViewModel - onWorldClicked - worldId = $worldId")
        navController.navigate(WorldDetails(worldId))
    }
}

data class CharacterDetailsViewState(
    val character: Character?,
    val world: World?,
    val films: List<Film>
)
