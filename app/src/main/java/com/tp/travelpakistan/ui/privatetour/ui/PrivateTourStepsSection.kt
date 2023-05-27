package com.tp.travelpakistan.ui.privatetour.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.R


@Composable
fun PrivateTourStepsSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        StepItem(
            iconDrawable = R.drawable.form_svgrepo_com,
            stepNumber = "Step 1",
            step = "Fill out form"
        )

        StepItem(
            iconDrawable = R.drawable.call_190_svgrepo_com,
            stepNumber = "Step 2",
            step = "Get call back"
        )

        StepItem(
            iconDrawable = R.drawable.ticket_svgrepo_com,
            stepNumber = "Step 3",
            step = "Get your plan"
        )

    }
}

@Composable
fun StepItem(
    iconDrawable: Int = R.drawable.call_190_svgrepo_com,
    stepNumber: String = "Step 1",
    step: String = "Fill out form"
) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = iconDrawable),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .align(
                        Alignment.Center,
                    ),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )
        }
        Text(
            text = stepNumber,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            text = step,
            style = TextStyle(
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )

    }
}

@Preview
@Composable
private fun PrivateTourSectionPreview() {
    PrivateTourStepsSection()
}

@Preview
@Composable
private fun StepItemPreview() {
    StepItem()
}