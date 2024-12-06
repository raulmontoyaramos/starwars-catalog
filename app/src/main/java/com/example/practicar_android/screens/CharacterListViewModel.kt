package com.example.practicar_android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.practicar_android.CharacterDetails
import com.example.practicar_android.data.network.NetworkService
import com.example.practicar_android.data.network.model.CharacterRepository
import com.example.practicar_android.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(
    private val networkService: NetworkService,
    private val navController: NavController,
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val viewState = MutableStateFlow(
        CharacterListViewState(
            characters = emptyList()
        )
    )

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val characterList = withContext(Dispatchers.IO) {
                    networkService.getCharacters().also { characterRepository.setCharacters(it) }
                }
                println("CharacterListViewModel - CharacterList = $characterList")

                viewState.update {
                    it.copy(
                        characters = characterList
                    )
                }
            } catch (exception: Exception){
                println("CharacterListViewModel - CharacterList = Error Loading Characters")
            }
        }
    }

    fun onCharacterClicked(characterId: String) {
        println("CharacterListViewModel - onCharacterClicked - characterId = $characterId")
        navController.navigate(CharacterDetails(characterId))
    }


}

data class CharacterListViewState(
    val characters: List<Character>
)
