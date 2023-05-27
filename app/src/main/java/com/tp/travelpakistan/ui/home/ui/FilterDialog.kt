package com.tp.travelpakistan.ui.home.ui

import android.util.Range
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tp.travelpakistan.ui.home.ui.model.FilterCheckBox
import com.tp.travelpakistan.ui.home.ui.model.filterChecks
import com.tp.travelpakistan.ui.home.ui.model.listOfFilters

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FilterDialog(
    sliderRange: ClosedFloatingPointRange<Float>,
    sliderValue: ClosedFloatingPointRange<Float>,
    onValueSlide: (ClosedFloatingPointRange<Float>) -> Unit,
    onDismiss:(()->Unit)?=null
) {
    Dialog(
        onDismissRequest = {
            onDismiss?.invoke()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            usePlatformDefaultWidth = true,
            decorFitsSystemWindows = true,
            dismissOnClickOutside = true
        )
    ) {
    Box(
        modifier = Modifier.fillMaxWidth().background(
            MaterialTheme.colorScheme.background,
            RoundedCornerShape(28.dp)
        ).padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Filter By"
                )
                IconButton(modifier = Modifier.size(24.dp),
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close_icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Price Range",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            RangeSlider(
                modifier = Modifier.fillMaxWidth(),
                value = sliderValue,
                valueRange = sliderRange,
                onValueChange = {
                    onValueSlide(it)
                },
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("PKR ${sliderValue.start.toInt()}")
                Text("PKR ${sliderValue.endInclusive.toInt()}")
            }
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(4.dp))
            FilterDurationSection(
                items = filterChecks,
                onCheckedListener = {

                }
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                OutlinedButton(
                    modifier = Modifier.width(100.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    onClick = {},
                ) {
                    Text("Reset", style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.primary))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                              onDismiss?.invoke()
                    },
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Apply", style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary))
                }
            }
        }
    }
}}

@Composable
fun FilterDurationSection(
    items: List<FilterCheckBox>,
    onCheckedListener: (FilterCheckBox) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Duration")
        LazyColumn {
            items(items) { filterItem ->
                FilterCheckBox(filterItem,
                    onCheckedListener = {
                        onCheckedListener(it)
                    })
            }
        }
    }
}

@Composable
fun FilterCheckBox(
    filterCheckBox: FilterCheckBox,
    onCheckedListener: (FilterCheckBox) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(36.dp)
            .toggleable(
                value = filterCheckBox.isChecked,
                onValueChange = { onCheckedListener(filterCheckBox) },
                role = Role.Checkbox
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = filterCheckBox.isChecked,
            onCheckedChange = null // null recommended for accessibility with screenreaders
        )
        Text(
            text = filterCheckBox.label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun FilterDialogPreview() {
    FilterDialog(
        0f..1000f,
        0f..1000f,
        onValueSlide = {}
    )
}