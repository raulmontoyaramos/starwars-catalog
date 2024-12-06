package com.example.practicar_android.data.network.model

import com.example.practicar_android.domain.model.Film
import kotlinx.coroutines.flow.MutableStateFlow

class FilmRepository {

    val films = MutableStateFlow<Map<String, Film>>(emptyMap())

    suspend fun setFilm(film: Film){
        this.films.value = films.value.plus(film.id to film)
    }

    suspend fun getFilmById(filmId: String): Film? = films.value[filmId]
}
