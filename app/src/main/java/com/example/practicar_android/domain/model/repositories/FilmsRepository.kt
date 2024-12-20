package com.example.practicar_android.domain.model.repositories

import com.example.practicar_android.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    suspend fun insertFilm(film: Film)

    suspend fun insertFilms(films: List<Film>)

    fun getFilm(filmId: String): Film?

    fun getAllFilms(): Flow<List<Film>>
}