package com.tp.travelpakistan.ui.booking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.travelpakistan.ui.booking.models.RadioItem

@Composable
fun TravelRadioButton(
    radioItem: RadioItem,
    onItemClick: (RadioItem) -> Unit
) {
    Column(
        modifier = Modifier.width(113.dp).background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.width(113.dp).height(21.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RadioButton(
                modifier = Modifier.size(21.dp),
                selected = radioItem.isSelected,
                onClick = { onItemClick(radioItem) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                text = radioItem.label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        Row(
            modifier = Modifier.padding(start = 28.dp)
        ) {
            radioItem.icons.forEach {
                Image(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.size(width = 30.dp, height = 25.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }
        }
    }


}


@Preview
@Composable
fun TravelRadioButtonPreview() {
    TravelRadioButton(
        RadioItem(
            "Credit Card",
            icons = listOf(Icons.Default.CreditCard),
            false
        ),
        onItemClick = {

        })
}