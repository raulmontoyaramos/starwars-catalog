package com.example.practicar_android.domain.model

import android.content.Context
import com.example.practicar_android.domain.model.repositories.CharactersRepository
import com.example.practicar_android.data.room.repository.OfflineCharactersRepository
import com.example.practicar_android.data.room.repository.OfflineFilmsRepository
import com.example.practicar_android.data.room.repository.OfflineWorldsRepository
import com.example.practicar_android.domain.model.repositories.FilmsRepository
import com.example.practicar_android.domain.model.repositories.WorldsRepository

interface AppContainer {
    val charactersRepository: CharactersRepository
    val filmsRepository: FilmsRepository
    val worldsRepository: WorldsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val charactersRepository: CharactersRepository by lazy {
        OfflineCharactersRepository(StarWarsDatabase.getDatabase(context).characterDao())
    }

    override val filmsRepository: FilmsRepository by lazy {
        OfflineFilmsRepository(StarWarsDatabase.getDatabase(context).filmDao())
    }

    override val worldsRepository: WorldsRepository by lazy {
        OfflineWorldsRepository(StarWarsDatabase.getDatabase(context).worldDao())
    }
}
