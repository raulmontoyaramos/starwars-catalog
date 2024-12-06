package com.example.practicar_android.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicar_android.domain.model.Character
import com.example.practicar_android.domain.model.Film
import com.example.practicar_android.domain.model.World
import com.example.practicar_android.ui.theme.PracticarAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel
) {
    val viewState: CharacterDetailsViewState = viewModel.viewState.collectAsState().value
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = viewState.character?.name?.let { "$it Details" } ?: "Character Details")
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
        CharacterDetailsContent(
            innerPadding,
            viewState,
            onWorldClicked = viewModel::onWorldClicked,
            onFilmClicked = viewModel::onFilmClicked
        )
    }
}

@Composable
private fun CharacterDetailsContent(
    innerPadding: PaddingValues,
    viewState: CharacterDetailsViewState,
    onWorldClicked: (String) -> Unit,
    onFilmClicked: (String) -> Unit
) {
    val horizontalScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(12.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        viewState.character?.let {
            CharacterDetails(
                character = it
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "- Homeworld: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(8.dp)
        )
        viewState.world?.let {
            WorldDetailsCard(
                world = it,
                onWorldClicked = onWorldClicked
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "- Films: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(8.dp)
        )

        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(horizontalScrollState)
            ) {
                viewState.films.forEach { film ->
                    FilmDetails(
                        film = film,
                        onFilmClicked = onFilmClicked
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

    }
}

@Composable
fun CharacterDetails(
    character: Character,
) {
    CharacterListItem(character = character, selected = true, onCardClick = {})
    Spacer(modifier = Modifier.height(4.dp))

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "More info about ${character.name}:",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        bottom = 8.dp
                    ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Birth year: ${character.birthYear}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Height: ${character.height}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Mass: ${character.mass}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Skin color: ${character.skinColor}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldDetailsCard(
    world: World,
    onWorldClicked: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = { onWorldClicked(world.id) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Name: ${world.name}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        bottom = 8.dp
                    ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Homeworld name: ${world.name}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Rotation period: ${world.rotationPeriod}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Diameter: ${world.diameter}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Details",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
                    .size(12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetails(
    film: Film,
    onFilmClicked: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp),
        onClick = { onFilmClicked(film.id) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Title: ${film.title}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        bottom = 8.dp
                    ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Release date: ${film.releaseDate}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Producer: ${film.producer}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Text(
                text = "Director: ${film.director}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Arrow icon",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
                    .size(12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterDetailsPreview() {
    PracticarAndroidTheme {
        CharacterDetailsContent(
            innerPadding = PaddingValues(0.dp),
            viewState = CharacterDetailsViewState(
                character = Character(
                    id = "2",
                    birthYear = "19BBY",
                    created = "2",
                    edited = "2",
                    eyeColor = "Blue",
                    films = listOf("2", ""),
                    gender = "Male",
                    hairColor = "Brown",
                    height = "1,72 m",
                    homeworld = "2",
                    mass = "77 kg",
                    name = "Luke Skywalker",
                    skinColor = "Fair",
                    species = listOf("2", ""),
                    starships = listOf("2", ""),
                    url = "2",
                    vehicles = listOf("2", "")
                ),
                world = World(
                    id = "1",
                    climate = "2",
                    created = ": String",
                    diameter = "10.465",
                    edited = "String",
                    films = listOf("dwf", "fdsf"),
                    gravity = "String",
                    name = "Tatooine",
                    orbitalPeriod = "String",
                    population = "String",
                    residents = listOf("fodf", "dswfd"),
                    rotationPeriod = "23",
                    surfaceWater = "String",
                    terrain = "String",
                    url = "String"
                ),
                films = listOf(
                    Film(
                        id = "1",
                        characters = listOf("Luke Skywalker", "Darth Vader"),
                        created = "2024-10-27",
                        director = "George Lucas",
                        edited = "2024-10-27",
                        episodeId = 4,
                        openingCrawl = "A long time ago in a galaxy far, far away...",
                        planets = listOf("Tatooine", "Yavin IV"),
                        producer = "Gary Kurtz",
                        releaseDate = "1977-05-25",
                        species = listOf("Human", "Droid"),
                        starships = listOf("X-wing", "TIE Fighter"),
                        title = "A New Hope",
                        url = "http://swapi.dev/api/films/1/",
                        vehicles = listOf("Sand Crawler", "T-16 Skyhopper")
                    ),
                    Film(
                        id = "2",
                        characters = listOf("Luke Skywalker", "Leia Organa"),
                        created = "2024-10-27",
                        director = "Irvin Kershner",
                        edited = "2024-10-27",
                        episodeId = 4,
                        openingCrawl = "It is a dark time for the Rebellion...",
                        planets = listOf("Hoth", "Dagobah"),
                        producer = "Gary Kurtz",
                        releaseDate = "1980-05-21",
                        species = listOf("Wookiee", "Human"),
                        starships = listOf("Millennium Falcon", "Star Destroyer"),
                        title = "The Empire Strikes Back",
                        url = "http://swapi.dev/api/films/2/",
                        vehicles = listOf("AT-AT", "Snowspeeder")
                    )
                )
            ),
            onWorldClicked = {},
            onFilmClicked = {}
        )
    }
}
