package com.tp.travelpakistan.ui.booking.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.tp.travelpakistan.ui.auth.ui.components.AuthTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelPakistanTextFieldV2(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: @Composable (() -> Unit)? = { Text("Placeholder text") },
    label: String = "",
    clickableField:Boolean = false,
    leadingIcon: ImageVector = Icons.Default.Info,
    isLabelVisible: Boolean = false,
    isIconVisible: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextFieldClick:()->Unit={},
    onValueChange: (String) -> Unit = {},
    ) {

    val leadingIconView = @Composable {
        IconButton(
            onClick = {

            },
        ) {
            Icon(
                leadingIcon,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
    Box(
        modifier = modifier.height(65.dp).fillMaxWidth().clickable {
            if(clickableField) onTextFieldClick()
        },
    ) {
        if (isLabelVisible) {
            Card(
                modifier = Modifier.height(16.dp).wrapContentWidth().zIndex(2f).graphicsLayer {
                    translationX = 50f
                    translationY = -0f
                }.align(Alignment.TopStart),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    label,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 3.dp,
                    vertical = 1.5.dp)
                )
            }
        }
        TextField(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomStart),
            value = value,
            onValueChange = { onValueChange(it) },
            shape = RoundedCornerShape(8.dp),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = placeholder,
            leadingIcon = if(isIconVisible) leadingIconView else null,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            enabled = !clickableField
        )
    }
}

@Preview
@Composable
fun TPTextFieldPreviewV2() {
    MaterialTheme() {
        Box(modifier = Modifier.fillMaxWidth().padding(16.dp))
        TravelPakistanTextFieldV2(clickableField = true)
    }
}