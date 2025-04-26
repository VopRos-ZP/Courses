package ru.vopros.courses.presentation.screens.courses

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import ru.vopros.courses.domain.Utils
import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.presentation.R
import ru.vopros.courses.presentation.components.TextField
import ru.vopros.courses.presentation.theme.DarkGrey
import ru.vopros.courses.presentation.theme.Glass

@Serializable data object Courses

@Composable
fun CoursesScreen(viewModel: CoursesViewModel = koinViewModel()) {
    val search by viewModel.search.collectAsState()
    val courses by viewModel.courses.collectAsState()
    val isSorted by viewModel.isSorted.collectAsState()

    val lazyListState = rememberLazyListState()

    LaunchedEffect(isSorted) {
        lazyListState.scrollToItem(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SearchRow(search = search)
        TextButton(
            onClick = { viewModel.onFilterByDateClick() },
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "По дате добавления",
                    style = MaterialTheme.typography.bodyMedium
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_down_up),
                    contentDescription = "arrow_down_up",
                )
            }
        }
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = courses,
                key = { it.id },
                contentType = { Utils.COURSE_CARD_CONTENT_TYPE }
            ) {
                CourseCard(
                    course = it,
                    onFavoriteClick = { viewModel.onFavoriteCourseClick(it) }
                )
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.fetchCourseList()
        viewModel.initFavoriteCourseList()
    }
}

@Composable
fun CourseCard(
    course: Course,
    onFavoriteClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkGrey
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            HeaderCourseCard(course, onFavoriteClick)
            BottomCourseCard(course)
        }
    }
}

@Composable
private fun HeaderCourseCard(
    course: Course,
    onFavoriteClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 12.dp, bottomEnd = 12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.course_1),
            contentDescription = course.title,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier.height(150.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            val (contentColor, iconRes) = when (course.hasLike) {
                true -> MaterialTheme.colorScheme.primary to R.drawable.bookmark_fill
                else -> MaterialTheme.colorScheme.onPrimary to R.drawable.bookmark
            }
            IconButton(
                onClick = { onFavoriteClick() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Glass,
                    contentColor = contentColor,
                )
            ) {
                ImageVector.vectorResource(iconRes).let {
                    Icon(
                        imageVector = it,
                        contentDescription = it.name
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .background(Glass, RoundedCornerShape(12.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.star_fill),
                        contentDescription = "star_fill",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = course.rate,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal,
                            lineHeight = 14.sp,
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .background(Glass, RoundedCornerShape(12.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = course.startDate,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal,
                            lineHeight = 14.sp,
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomCourseCard(course: Course) {
    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = course.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = course.text,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
                Text(
                    text = course.price,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
        Row(
            modifier = Modifier.clickable {/* navigate to details screen */},
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Подробнее",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_right_short_fill),
                contentDescription = "arrow_right_short_fill",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun SearchRow(search: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = search,
            onValueChange = {/* on search change */},
            label = "Search courses...",
            leadingIcon = R.drawable.search,
            containerColor = DarkGrey
        )
        IconButton(
            onClick = {/* on filters click */},
            modifier = Modifier.size(56.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = DarkGrey,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.funnel),
                contentDescription = "funnel",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}