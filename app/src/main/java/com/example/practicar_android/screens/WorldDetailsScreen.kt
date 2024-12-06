package com.example.practicar_android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practicar_android.domain.model.World

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldDetailsScreen(
    viewModel: WorldDetailsViewModel
) {
    val viewState: WorldDetailsViewState = viewModel.viewState.collectAsState().value
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = viewState.world?.name?.let { "$it Details" } ?: "Homeworld Details")
            },
            navigationIcon = {
                IconButton(onClick = viewModel::onBackButtonClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            viewState.world?.let {
                WorldDetails(
                    world = it,
                    innerPadding)
            }

        }
    }
}

@Composable
fun WorldDetails(
    world: World,
    innerPadding: PaddingValues
) {
    Card(
        modifier = Modifier
            .padding(innerPadding)
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = world.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = 12.dp, bottom = 8.dp
                )
            )
            Text(
                text = world.rotationPeriod,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            Text(
                text = world.orbitalPeriod,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.diameter.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.climate,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.gravity,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.terrain,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.surfaceWater,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.population,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.residents.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.films.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.created,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = world.edited,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
        }
    }
}
