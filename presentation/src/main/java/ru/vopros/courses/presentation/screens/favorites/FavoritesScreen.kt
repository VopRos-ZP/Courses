package ru.vopros.courses.presentation.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import ru.vopros.courses.domain.Utils
import ru.vopros.courses.presentation.screens.courses.CourseCard

@Serializable
data object Favorites

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = koinViewModel()) {
    val courses by viewModel.courses.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Избранное",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
        }
        items(
            items = courses,
            key = { it.id },
            contentType = { Utils.COURSE_CARD_CONTENT_TYPE }
        ) {
            CourseCard(
                course = it,
                onFavoriteClick = { viewModel.onRemoveFavoriteCourseClick(it) }
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.fetchFavoriteCourses()
    }
}