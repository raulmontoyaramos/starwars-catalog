package com.example.practicar_android.data.room.repository

import com.example.practicar_android.data.room.dao.FilmDao
import com.example.practicar_android.domain.model.Film
import com.example.practicar_android.domain.model.repositories.FilmsRepository
import com.example.practicar_android.domain.model.toDatabase
import com.example.practicar_android.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class OfflineFilmsRepository(private val filmDao: FilmDao) : FilmsRepository {

    override suspend fun insertFilm(film: Film) =
        filmDao.insertFilm(film.toDatabase())

    override suspend fun insertFilms(films: List<Film>) =
        filmDao.insertFilms(films.map { it.toDatabase() })

    override fun getFilm(filmId: String): Film? =
        filmDao.getFilm(filmId)?.toDomain()

    override fun getAllFilms(): Flow<List<Film>> =
        filmDao.getAllFilms().map { it.map { it.toDomain() } }
}