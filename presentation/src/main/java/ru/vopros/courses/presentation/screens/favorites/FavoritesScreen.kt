package ru.vopros.courses.presentation.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import ru.vopros.courses.domain.Utils
import ru.vopros.courses.presentation.components.Loader
import ru.vopros.courses.presentation.components.TopBar
import ru.vopros.courses.presentation.screens.courses.CourseCard

@Serializable
data object Favorites

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar("Избранное")
            Loader()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { TopBar("Избранное") }
            items(
                items = state.courses,
                key = { it.id },
                contentType = { Utils.COURSE_CARD_CONTENT_TYPE }
            ) {
                CourseCard(
                    course = it,
                    onFavoriteClick = { viewModel.onRemoveFavoriteCourseClick(it) }
                )
            }
        }
    }
}