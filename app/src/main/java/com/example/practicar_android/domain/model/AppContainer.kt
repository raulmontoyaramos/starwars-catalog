package com.example.practicar_android.domain.model

import android.content.Context
import com.example.practicar_android.domain.model.repositories.CharactersRepository
import com.example.practicar_android.data.room.repository.OfflineCharactersRepository

interface AppContainer {
    val charactersRepository: CharactersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val charactersRepository: CharactersRepository by lazy {
        OfflineCharactersRepository(StarWarsDatabase.getDatabase(context).characterDao())
    }

}
