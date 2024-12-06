package com.example.practicar_android.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkFilm(
    @SerialName("title")
    val title: String,
    @SerialName("episode_id")
    val episodeId: Int,
    @SerialName("opening_crawl")
    val openingCrawl: String,
    @SerialName("director")
    val director: String,
    @SerialName("producer")
    val producer: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("characters")
    val characters: List<String>,
    @SerialName("planets")
    val planets: List<String>,
    @SerialName("starships")
    val starships: List<String>,
    @SerialName("vehicles")
    val vehicles: List<String>,
    @SerialName("species")
    val species: List<String>,
    @SerialName("created")
    val created: String,
    @SerialName("edited")
    val edited: String,
    @SerialName("url")
    val url: String
)
