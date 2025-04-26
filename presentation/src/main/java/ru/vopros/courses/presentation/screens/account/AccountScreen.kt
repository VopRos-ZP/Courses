package ru.vopros.courses.presentation.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.ProgressIndicatorDefaults.drawStopIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable
import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.presentation.R
import ru.vopros.courses.presentation.components.HDivider
import ru.vopros.courses.presentation.components.TopBar
import ru.vopros.courses.presentation.screens.courses.HeaderCourseCard
import ru.vopros.courses.presentation.theme.DarkGrey
import ru.vopros.courses.presentation.theme.LightGrey
import java.util.Date

@Serializable
data object Account

const val PROFILE_SETTINGS_TYPE = 1
const val COURSE_CARD_TYPE = 2

@Composable
fun AccountScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = 4,
            contentType = {
                when (it) {
                    0 -> PROFILE_SETTINGS_TYPE
                    else -> COURSE_CARD_TYPE
                }
            }
        ) { i ->
            when (i) {
                0 -> Column {
                    TopBar("Профиль")
                    SettingsBlock()
                }
                1 -> Column {
                    TopBar("Ваши курсы")
                    ProfileCourseCard()
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileCourseCard()
                }
            }
        }
    }
}

@Composable
fun SettingsBlock() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(DarkGrey, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SettingRow(text = "Написать в поддержку")
        HDivider()
        SettingRow(text = "Настройки")
        HDivider()
        SettingRow(text = "Выйти из аккаунта")
    }
}

@Composable
fun SettingRow(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium.copy(
                letterSpacing = 0.1.sp
            )
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.arrow_right),
            contentDescription = "arrow_right",
        )
    }
}

@Composable
fun ProfileCourseCard() {
    val course = Course(
        id = 1,
        title = "3D-дженералист",
        text = "",
        price = "12 000 ₽",
        rate = "3.9",
        startDate = "10 Сентября 2024",
        hasLike = true,
        publishDate = Date()
    )
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
            HeaderCourseCard(course) {}
            BottomProfileCourseCard(course)
        }
    }
}

@Composable
fun BottomProfileCourseCard(course: Course) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = course.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        LessonProgressBar(
            progress = 0.5f,
            currentLessons = 22,
            totalLessons = 44,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonProgressBar(
    progress: Float,
    currentLessons: Int,
    totalLessons: Int,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${(progress * 100).toInt()}%",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xFF00C853))) {
                        append("$currentLessons")
                    }
                    append("/$totalLessons уроков")
                },
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = LightGrey,
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
            drawStopIndicator = {
                drawStopIndicator(
                    drawScope = this,
                    stopSize = ProgressIndicatorDefaults.LinearTrackStopIndicatorSize,
                    color = LightGrey, // Скрыть drawStopIndicator
                    strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap
                )
            }
        )
    }
}
