package com.example.practicar_android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.practicar_android.data.network.model.WorldRepository
import com.example.practicar_android.domain.model.World
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorldDetailsViewModel(
    private val navController: NavController,
    private val worldId: String,
    private val worldRepository: WorldRepository
) : ViewModel() {

    val viewState = MutableStateFlow(
        WorldDetailsViewState(
            world = null
        )
    )

    init {
        fetchWorld()
    }

    private fun fetchWorld() {
        viewModelScope.launch {
            try {
                val world: World? = withContext(Dispatchers.IO){
                    println("Worlds in repository: ${worldRepository.worlds.value}")
                    worldRepository.getWorldById(worldId)
                }
                println("WorldDetailsViewModel - WorldDetails = $world")
                viewState.update {
                    it.copy(
                        world = world
                    )
                }
            } catch (exception: Exception) {
                println("WorldDetailsViewModel - WorldDetails = Error Loading Homeworld")
            }
        }
    }

    fun onBackButtonClicked() = navController.navigateUp()

}

data class WorldDetailsViewState(
    val world: World?
)
