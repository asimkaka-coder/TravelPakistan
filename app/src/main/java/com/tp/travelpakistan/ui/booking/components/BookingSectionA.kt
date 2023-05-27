package com.tp.travelpakistan.ui.booking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.ui.booking.data.models.BookingUiModel
import com.tp.travelpakistan.ui.booking.models.cardOptions
import com.tp.travelpakistan.ui.home.ui.components.TourItem
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.home.ui.model.listOfTours
import com.tp.travelpakistan.ui.theme.surfaceColor

enum class PassengerType {
    CHILD, ADULT
}

@Composable
fun BookingSectionA(
    tourPackage: TourPackage,
    bookingUiModel: BookingUiModel,
    onDateSelection: () -> Unit = {},
    onLocationSelection: () -> Unit = {},
    onTravellerChange: (PassengerType, Int) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.elevatedCardElevation()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {


                TourItem(
                    tourPackage,
                    onTourClick = {}
                )
                Spacer(modifier = Modifier.height(8.dp))

                TravelPakistanTextFieldV2(
                    value = bookingUiModel.bookingDate,
                    clickableField = true,
                    isIconVisible = true,
                    leadingIcon = Icons.Default.EditCalendar,
                    placeholder = { Text(text = "dd-mm-yyyy") },
                    onTextFieldClick = { onDateSelection() },
                    keyboardType = KeyboardType.Decimal
                ) {
                }
                Spacer(modifier = Modifier.height(8.dp))


                TravelPakistanTextFieldV2(
                    value = bookingUiModel.adultPassengers.toString(),
                    isLabelVisible = true,
                    label = "Number Of Tavellers",
                    placeholder = { Text(text = "0") },
                    keyboardType = KeyboardType.Decimal
                ) {
                    if (it.isNotEmpty() && it.toIntOrNull()!=null) {
                        onTravellerChange(PassengerType.ADULT, it.toInt())
                    } else {
                        onTravellerChange(PassengerType.ADULT, bookingUiModel.adultPassengers)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                TravelPakistanTextFieldV2(
                    value = bookingUiModel.childPassengers.toString(),
                    isLabelVisible = true,
                    label = "Number Of Children",
                    placeholder = { Text(text = "0") },
                    keyboardType = KeyboardType.Decimal
                ) {
                    if (it.isNotEmpty() && it.toIntOrNull()!=null) {
                        onTravellerChange(PassengerType.CHILD, it.toInt())
                    } else {
                        onTravellerChange(PassengerType.CHILD, bookingUiModel.childPassengers)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))


                TravelPakistanTextFieldV2(
                    value = bookingUiModel.pickupLocation,
                    clickableField = true,
                    placeholder = { Text(text = "Select Pickup") },
                    onTextFieldClick = {
                        onLocationSelection()
                    }

                ) {

                }

                Spacer(modifier = Modifier.height(16.dp))

                SummarySection(summaryUiModel = bookingUiModel.summaryUiModel)
            }
            Row(
                modifier = Modifier
                    .height(62.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Amount",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )

                Text(
                    text = "PKR ${bookingUiModel.summaryUiModel.subTotal}",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )


            }
        }
    }
}

@Preview
@Composable
fun BookingSectionAPreview() {
    MaterialTheme()
    {
//        BookingSectionA(listOfTours[0])
    }
}