package com.tp.travelpakistan.ui.auth.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue = TextFieldValue(""),
    onValueChange: (TextFieldValue) -> Unit = {},
    placeholder: @Composable (() -> Unit)? = { Text("Placeholder text") },
    isPasswordField: Boolean = false,
) {

    TextField(
        value = value, onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = TextFieldDefaults.filledShape,
        maxLines = 1,
        singleLine = true,
        placeholder = placeholder,
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background),
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None
    )
}


@Preview
@Composable
fun AuthTextFieldPreview() {
    AuthTextField()
}