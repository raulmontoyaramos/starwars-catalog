package com.example.practicar_android.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicar_android.data.room.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilm(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM films WHERE filmId = :filmId")
    fun getFilm(filmId: String): FilmEntity?

    @Query("SELECT * FROM films ORDER BY title ASC")
    fun getAllFilms(): Flow<List<FilmEntity>>
}