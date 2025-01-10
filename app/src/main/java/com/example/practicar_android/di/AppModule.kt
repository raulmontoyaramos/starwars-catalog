package com.example.practicar_android.di

import androidx.room.Room
import com.example.practicar_android.StarWarsApplication
import com.example.practicar_android.data.network.NetworkService
import com.example.practicar_android.data.network.model.CharacterRepository
import com.example.practicar_android.data.room.repository.OfflineCharactersRepository
import com.example.practicar_android.data.room.repository.OfflineFilmsRepository
import com.example.practicar_android.data.room.repository.OfflineWorldsRepository
import com.example.practicar_android.domain.model.StarWarsDatabase
import com.example.practicar_android.domain.model.repositories.CharactersRepository
import com.example.practicar_android.domain.model.repositories.FilmsRepository
import com.example.practicar_android.domain.model.repositories.WorldsRepository
import com.example.practicar_android.screens.CharacterDetailsViewModelFactory
import com.example.practicar_android.screens.CharacterListViewModelFactory
import com.example.practicar_android.screens.FilmDetailsViewModelFactory
import com.example.practicar_android.screens.WorldDetailsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: StarWarsApplication) {

    @Provides
    fun providesNetworkService(): NetworkService {
        return NetworkService.invoke()
    }

    @Provides
    fun providesCharactersRepository(
        database: StarWarsDatabase
    ): CharactersRepository {
        return OfflineCharactersRepository(database.characterDao())
    }

    @Provides
    @Singleton
    fun providesStarWarsDatabase(
    ): StarWarsDatabase {
        return Room.databaseBuilder(application, StarWarsDatabase::class.java, "starwars_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesCharacterRepository(): CharacterRepository {
        return CharacterRepository()
    }

    @Provides
    fun providesFilmsRepository(
        database: StarWarsDatabase
    ): FilmsRepository {
        return OfflineFilmsRepository(database.filmDao())
    }

    @Provides
    fun providesWorldsRepository(
        database: StarWarsDatabase
    ): WorldsRepository {
        return OfflineWorldsRepository(database.worldDao())
    }

    @Provides
    fun providesCharacterListViewModelFactory(
        networkService: NetworkService,
        charactersRepository: CharactersRepository
    ) = CharacterListViewModelFactory(
        networkService = networkService,
        charactersRepository = charactersRepository
    )

    @Provides
    fun providesCharacterDetailsViewModelFactory(
        networkService: NetworkService,
        filmsRepository: FilmsRepository,
        worldsRepository: WorldsRepository,
        characterRepository: CharacterRepository
    ) = CharacterDetailsViewModelFactory(
        networkService = networkService,
        filmsRepository = filmsRepository,
        worldsRepository = worldsRepository,
        characterRepository = characterRepository
    )

    @Provides
    fun providesFilmDetailsViewModelFactory(
        filmsRepository: FilmsRepository
    ) = FilmDetailsViewModelFactory(
        filmsRepository = filmsRepository
    )

    @Provides
    fun providesWorldDetailsViewModelFactory(
        worldsRepository: WorldsRepository
    ) = WorldDetailsViewModelFactory(
        worldsRepository = worldsRepository
    )

}
