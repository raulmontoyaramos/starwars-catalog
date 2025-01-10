package com.example.practicar_android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.practicar_android.domain.model.World
import com.example.practicar_android.domain.model.repositories.WorldsRepository
import com.example.practicar_android.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorldDetailsViewModel(
    private val navController: NavController,
    worldId: String,
    private val worldsRepository: WorldsRepository
) : ViewModel() {

    val viewState = MutableStateFlow(
        WorldDetailsViewState(
            world = null
        )
    )

    init {
        fetchWorld(worldId)
    }

    private fun fetchWorld(worldId: String) {
        viewModelScope.launch {
            try {
                val world: World? = withContext(Dispatchers.IO) {
                    worldsRepository.getWorld(worldId)
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

class WorldDetailsViewModelFactory(
    private val worldsRepository: WorldsRepository
) {
    internal fun create(
        navController: NavController,
        worldId: String,
    ) = viewModelFactory {
        WorldDetailsViewModel(
            worldsRepository = worldsRepository,
            navController = navController,
            worldId = worldId
        )
    }
}
