package com.example.practicar_android.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicar_android.domain.model.Character
import com.example.practicar_android.ui.theme.PracticarAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel
) {
    val viewState: CharacterListViewState = viewModel.viewState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Stars Wars App") },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            CharacterList(
                characters = viewState.characters,
                onCharacterClick = viewModel::onCharacterClicked
            )
        }
    }
}

@Composable
fun CharacterList(
    characters: List<Character>,
    onCharacterClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = "Star Wars Characters:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Click to see the full info about the character",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        items(characters, key = { character -> character.id }) { character ->
            CharacterListItem(
                character = character,
                selected = false,
                onCardClick = { onCharacterClick(character.id) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListItem(
    character: Character,
    selected: Boolean,
    onCardClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        ),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = 12.dp, bottom = 8.dp
                )
            )
            Text(
                text = "Gender: ${character.gender}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Eye color: ${character.eyeColor}",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Hair color: ${character.hairColor}",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterListScreenPreview() {
    PracticarAndroidTheme {
        CharacterList(
            characters = listOf(
                Character(
                    id = "2",
                    birthYear = "2004",
                    created = "2",
                    edited = "2",
                    eyeColor = "Blue",
                    films = listOf("2", ""),
                    gender = "Male",
                    hairColor = "Brown",
                    height = "2",
                    homeworld = "2",
                    mass = "2",
                    name = "Luke Skywalker",
                    skinColor = "2",
                    species = listOf("2", ""),
                    starships = listOf("2", ""),
                    url = "2",
                    vehicles = listOf("2", "")
                )
            ),
            onCharacterClick = {}
        )
    }
}
