package com.example.practicar_android.domain.model

data class Film(
    val id: String,
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
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
