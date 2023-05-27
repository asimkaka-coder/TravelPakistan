@file:OptIn(ExperimentalMaterial3Api::class)

package com.tp.travelpakistan.ui.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.travelpakistan.ui.auth.ui.components.AuthTextField

@Composable
fun SearchBar(
    value: TextFieldValue = TextFieldValue(""),
    placeholder: @Composable (() -> Unit)? = { Text("Search Tours") },
    onValueChange: (TextFieldValue) -> Unit = {},
) {
    TextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().shadow(4.dp),
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        singleLine = true,
        placeholder = placeholder,
        colors = TextFieldDefaults.textFieldColors( containerColor = MaterialTheme.colorScheme.background,
            focusedTextColor = Color(0xFF49454F),
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,),
        leadingIcon = { Icon(Icons.Default.Search, "Search Icon") },
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {
        SearchBar()
    }
}