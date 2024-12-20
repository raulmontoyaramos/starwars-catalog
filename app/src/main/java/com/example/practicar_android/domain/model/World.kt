package com.example.practicar_android.domain.model

import java.time.Instant

data class World(
    val id: String,
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    val created: Instant,
    val edited: Instant,
    val url: String
)
