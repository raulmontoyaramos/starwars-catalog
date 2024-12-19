package com.example.practicar_android.data.room.repository

import androidx.room.TypeConverters
import com.example.practicar_android.data.room.dao.CharacterDao
import com.example.practicar_android.domain.model.Character
import com.example.practicar_android.domain.model.repositories.CharactersRepository
import com.example.practicar_android.domain.model.toDatabase
import com.example.practicar_android.domain.model.toDomain
import com.example.practicar_android.domain.model.util.InstantConverter
import com.example.practicar_android.domain.model.util.ListConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@TypeConverters(ListConverter::class, InstantConverter::class)
class OfflineCharactersRepository(private val characterDao: CharacterDao) : CharactersRepository {

    override suspend fun insertCharacter(character: Character) =
        characterDao.insertCharacter(character.toDatabase())

    override suspend fun insertCharacters(characters: List<Character>) =
        characterDao.insertCharacters(characters.map { it.toDatabase() })

    override fun getCharacter(characterId: Int): Character? =
        characterDao.getCharacter(characterId)?.toDomain()

    override fun getAllCharacters(): Flow<List<Character>> =
        characterDao.getAllCharacters().map { it.map { it.toDomain() } }
}
