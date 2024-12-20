package com.example.practicar_android.domain.model

import com.example.practicar_android.data.room.entity.FilmEntity
import java.time.Instant

data class Film(
    val id: String,
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
    val created: Instant,
    val edited: Instant,
    val url: String
)

fun Film.toDatabase() =
    FilmEntity(
        filmId = id,
        title = title,
        episodeId = episodeId,
        openingCrawl = openingCrawl,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
        characters = characters,
        planets = planets,
        starships = starships,
        vehicles = vehicles,
        species = species,
        created = created,
        edited = edited,
        url = url
    )

fun FilmEntity.toDomain() =
    Film(
        id = filmId,
        title = title,
        episodeId = episodeId,
        openingCrawl = openingCrawl,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
        characters = characters,
        planets = planets,
        starships = starships,
        vehicles = vehicles,
        species = species,
        created = created,
        edited = edited,
        url = url
    )
