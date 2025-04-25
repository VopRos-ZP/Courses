package ru.vopros.courses.presentation.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.vopros.courses.presentation.R
import ru.vopros.courses.presentation.theme.Dark
import ru.vopros.courses.presentation.theme.Green
import ru.vopros.courses.presentation.theme.White

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel(),
    onContinueClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(140.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = "Тысячи курсов \nв одном месте",
                color = White,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )
            val imageVector = ImageVector.vectorResource(R.drawable.courses)
            val scrollState = rememberScrollState(
                initial = imageVector.defaultWidth.value.toInt() / 3
            )
            Image(
                imageVector = imageVector,
                contentDescription = imageVector.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.horizontalScroll(scrollState)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            contentPadding = PaddingValues(vertical = 10.dp),
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = White
            ),
            onClick = { viewModel.onContinueClick() },
        ) {
            Text(
                text = "Продолжить"
            )
        }
    }
}