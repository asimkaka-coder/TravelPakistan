package com.tp.travelpakistan.ui.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.home.ui.model.listOfTours

const val dummayImage =
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeA1kKetFu4-juMU8uTbAkuI1D0TwupV2NA8fDzAslg7ERnVxa_6gnQ0JxaDWeBDIIpYM&usqp=CAU"


@Composable
fun PopularTourItem(
    tourPackage: TourPackage,
    onClick:((TourPackage)->Unit)?=null
) {
    Card(modifier = Modifier
        .width(136.dp)
        .height(136.dp)
        .clickable { onClick?.invoke(tourPackage) },
        colors = CardDefaults.elevatedCardColors(
            contentColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp)
    )
    {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart,
        ) {
            GlideImage(
                imageModel = { tourPackage.images[0] },
                modifier = Modifier
                    .fillMaxSize(),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = .3f))
                    .padding(
                        bottom = 12.dp,
                        start = 10.dp
                    )
            ) {
                Text(
                    text = tourPackage.tourDestination,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Default,
                        color = Color.White
                    )
                )
                Text(
                    text = "${tourPackage.durationDays} trip",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Default,
                        color = Color.White
                    )
                )
                Text(
                    text = tourPackage.pricePerPersion,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Default,
                        color = Color.White
                    )
                )

            }

        }
    }
}

@Preview
@Composable
fun PopularTourItemPreview() {
    PopularTourItem(listOfTours[0])
}