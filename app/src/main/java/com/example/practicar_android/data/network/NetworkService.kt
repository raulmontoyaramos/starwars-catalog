package com.example.practicar_android.data.network

import android.util.Log
import com.example.practicar_android.data.network.model.NetworkCharacter
import com.example.practicar_android.data.network.model.NetworkCharacters
import com.example.practicar_android.data.network.model.NetworkFilm
import com.example.practicar_android.data.network.model.NetworkPlanet
import com.example.practicar_android.domain.model.Character
import com.example.practicar_android.domain.model.Film
import com.example.practicar_android.domain.model.World
import com.example.practicar_android.domain.model.util.extractIdFromUrl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json

private const val TIME_OUT = 6000

interface NetworkService {

    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(characterId: String): Character
    suspend fun getWorld(worldId: String): World
    suspend fun getFilm(filmId: String): Film

    companion object {
        operator fun invoke() = object : NetworkService {

            val client = HttpClient(Android) {
                install(JsonFeature)
                {
                    KotlinxSerializer(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })

                    engine {
                        connectTimeout = TIME_OUT
                        socketTimeout = TIME_OUT
                    }

                    install(ResponseObserver) {
                        onResponse { response ->
                            Log.d("HTTP status:", "${response.status.value}")
                            val responseBody = response.content.toString()
                            Log.d("HTTP Response Body", responseBody)
                        }
                    }

                    install(DefaultRequest) {
                        header(HttpHeaders.ContentType, ContentType.Application.Json)
                    }

                }
            }

            override suspend fun getCharacters(): List<Character> {
                val result: NetworkCharacters =
                    client.get<NetworkCharacters>("https://swapi.dev/api/people/")

                return result.results.map {

                    Character(
                        id = it.url.extractIdFromUrl(),
                        birthYear = it.birthYear,
                        created = it.created,
                        edited = it.edited,
                        eyeColor = it.eyeColor,
                        films = it.films,
                        gender = it.gender,
                        hairColor = it.hairColor,
                        height = it.height,
                        homeworld = it.homeworld,
                        mass = it.mass,
                        name = it.name,
                        skinColor = it.skinColor,
                        species = it.species,
                        starships = it.starships,
                        url = it.url,
                        vehicles = it.vehicles
                    )
                }
            }

            override suspend fun getCharacter(characterId: String): Character {
                val result: NetworkCharacter =
                    client.get<NetworkCharacter>("https://swapi.dev/api/people/$characterId/")

                return Character(
                    id = result.url.extractIdFromUrl(),
                    birthYear = result.birthYear,
                    created = result.created,
                    edited = result.edited,
                    eyeColor = result.eyeColor,
                    films = result.films,
                    gender = result.gender,
                    hairColor = result.hairColor,
                    height = result.height,
                    homeworld = result.homeworld,
                    mass = result.mass,
                    name = result.name,
                    skinColor = result.skinColor,
                    species = result.species,
                    starships = result.starships,
                    url = result.url,
                    vehicles = result.vehicles
                )
            }

            override suspend fun getWorld(worldId: String): World {
                val result: NetworkPlanet =
                    client.get<NetworkPlanet>("https://swapi.dev/api/planets/$worldId/")

                return World(
                    id = result.url.extractIdFromUrl(),
                    name = result.name,
                    rotationPeriod = result.rotationPeriod,
                    orbitalPeriod = result.orbitalPeriod,
                    diameter = result.diameter,
                    climate = result.climate,
                    gravity = result.gravity,
                    terrain = result.terrain,
                    surfaceWater = result.surfaceWater,
                    population = result.population,
                    residents = result.residents,
                    films = result.films,
                    created = result.created,
                    edited = result.edited,
                    url = result.url
                )
            }

            override suspend fun getFilm(filmId: String): Film {
                val result: NetworkFilm =
                    client.get<NetworkFilm>("https://swapi.dev/api/films/$filmId/")

                return Film(
                    id = result.url.extractIdFromUrl(),
                    characters = result.characters,
                    created = result.created,
                    director = result.director,
                    edited = result.edited,
                    episodeId = result.episodeId,
                    openingCrawl = result.openingCrawl,
                    planets = result.planets,
                    producer = result.producer,
                    releaseDate = result.releaseDate,
                    species = result.species,
                    starships = result.starships,
                    title = result.title,
                    url = result.url,
                    vehicles = result.vehicles
                )
            }

        }
    }
}
