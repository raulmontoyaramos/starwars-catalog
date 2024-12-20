package com.example.practicar_android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.practicar_android.domain.model.Film
import com.example.practicar_android.domain.model.repositories.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmDetailsViewModel (
    private val navController: NavController,
    private val filmId: String,
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    val viewState = MutableStateFlow(
        FilmDetailsViewState(
            film = null
        )
    )

    init {
        fetchFilm()
    }

    private fun fetchFilm() {
        viewModelScope.launch {
            try {
                val film = withContext(Dispatchers.IO){
                    filmsRepository.getFilm(filmId)
                }

//                val film: Film? = withContext(Dispatchers.IO){
//                    println("Films in repository: ${filmRepository.films.value}")
//                    filmRepository.getFilmById(filmId)
//                }
                println("FilmDetailsViewModel - Film = $film")
                viewState.update {
                    it.copy(
                        film = film
                    )
                }
            } catch (exception: Exception) {
                println("FilmDetailsViewModel - FilmDetails = Error Loading Film")
            }
        }
    }

    fun onBackButtonClicked() = navController.navigateUp()
}


data class FilmDetailsViewState(
    val film: Film?
)