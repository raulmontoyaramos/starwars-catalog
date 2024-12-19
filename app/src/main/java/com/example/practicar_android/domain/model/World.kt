package com.example.practicar_android.domain.model

import java.time.Instant

data class World(
    val id: String,
    val climate: String,
    val created: Instant,
    val diameter: String,
    val edited: Instant,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val orbitalPeriod: String,
    val population: String,
    val residents: List<String>,
    val rotationPeriod: String,
    val surfaceWater: String,
    val terrain: String,
    val url: String
)
