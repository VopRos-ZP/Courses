package ru.vopros.courses.presentation.screens.login

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.vopros.courses.presentation.R
import ru.vopros.courses.presentation.components.TextField
import ru.vopros.courses.presentation.theme.Dark
import ru.vopros.courses.presentation.theme.Green
import ru.vopros.courses.presentation.theme.OK
import ru.vopros.courses.presentation.theme.Stroke
import ru.vopros.courses.presentation.theme.VK
import ru.vopros.courses.presentation.theme.White

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isValid by viewModel.isValid.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark),
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(140.dp))
        Text(
            text = "Вход",
            color = White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(28.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Email",
                    color = White,
                    style = MaterialTheme.typography.titleMedium
                )
                TextField(
                    value = email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = "example@gmail.com",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                    ),
                )
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Пароль",
                    color = White,
                    style = MaterialTheme.typography.titleMedium
                )
                TextField(
                    value = password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = "Введите пароль",
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { viewModel.onLoginClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            contentPadding = PaddingValues(vertical = 10.dp),
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = White,
                disabledContainerColor = Green.copy(alpha = 0.5f),
                disabledContentColor = White.copy(alpha = 0.5f)
            ),
            enabled = isValid,
        ) {
            Text(
                text = "Вход"
            )
        }
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BasicText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = White)) {
                        append("Нет аккаунта? ")
                        withStyle(style = SpanStyle(color = Green)) {
                            withLink(LinkAnnotation.Clickable("registration") {}) {
                                append("Регистрация")
                            }
                        }
                    }
                },
                style = MaterialTheme.typography.labelMedium
            )
            BasicText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Green)) {
                        withLink(LinkAnnotation.Clickable("forgot_password") {}) {
                            append("Забыл пароль")
                        }
                    }
                },
                style = MaterialTheme.typography.labelMedium
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Stroke
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = White,
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .background(VK, RoundedCornerShape(30.dp))
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.vk),
                    contentDescription = "vk",
                )
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = White,
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .background(OK, RoundedCornerShape(30.dp))
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ok),
                    contentDescription = "ok",
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}