package com.example.practicar_android.domain.model

import java.time.Instant

data class Film(
    val id: String,
    val characters: List<String>,
    val created: Instant,
    val director: String,
    val edited: Instant,
    val episodeId: Int,
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)
