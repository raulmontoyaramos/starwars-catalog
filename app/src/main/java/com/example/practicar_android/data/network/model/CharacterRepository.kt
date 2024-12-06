package com.example.practicar_android.data.network.model

import com.example.practicar_android.domain.model.Character
import kotlinx.coroutines.flow.MutableStateFlow

class CharacterRepository {

    private val characters = MutableStateFlow<Map<String, Character>>(emptyMap())

    suspend fun setCharacters(characters: List<Character>) {
        this.characters.value = characters.associateBy { it.id }
    }
    suspend fun setCharacter(character: Character) {
        this.characters.value = characters.value.plus(character.id to character)
    }

}
