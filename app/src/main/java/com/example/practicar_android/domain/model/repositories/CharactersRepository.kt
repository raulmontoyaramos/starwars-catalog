package com.example.practicar_android.domain.model.repositories

import androidx.room.TypeConverters
import com.example.practicar_android.domain.model.Character
import com.example.practicar_android.domain.model.util.InstantConverter
import com.example.practicar_android.domain.model.util.ListConverter
import kotlinx.coroutines.flow.Flow

@TypeConverters(ListConverter::class, InstantConverter::class)
interface CharactersRepository {

    suspend fun insertCharacter(character: Character)

    suspend fun insertCharacters(characters: List<Character>)

    fun getCharacter(characterId: Int): Character?

    fun getAllCharacters(): Flow<List<Character>>
}
