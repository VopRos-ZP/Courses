package ru.vopros.courses.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.vopros.courses.presentation.theme.LightGrey
import ru.vopros.courses.presentation.theme.White

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    leadingIcon: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    containerColor: Color = LightGrey
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        placeholder = if (label != null) {
            {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else null,
        leadingIcon = if (leadingIcon != null) {
            {
                ImageVector.vectorResource(leadingIcon).let {
                    Icon(
                        imageVector = it,
                        contentDescription = it.name
                    )
                }
            }
        } else null,
        visualTransformation = visualTransformation,
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White,
            focusedLeadingIconColor = White,
            unfocusedLeadingIconColor = White,
            focusedPlaceholderColor = White.copy(alpha = 0.5f),
            unfocusedPlaceholderColor = White.copy(alpha = 0.5f),
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
        )
    )
}