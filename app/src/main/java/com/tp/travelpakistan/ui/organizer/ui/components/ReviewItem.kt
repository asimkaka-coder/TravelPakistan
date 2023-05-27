package com.tp.travelpakistan.ui.organizer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.tp.travelpakistan.R
import com.tp.travelpakistan.ui.auth.data.models.UserX
import com.tp.travelpakistan.ui.organizer.data.models.Review
import com.tp.travelpakistan.ui.profile.ProfileUserSection


@Composable
fun ReviewItem(
    review: Review
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)

    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            GlideImage(
                imageModel = { "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png"  },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = review.user.username,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = review.comment,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground,
                    letterSpacing = 0.5.sp,
                    lineHeight = 16.sp
                )

                Text(
                    text = "Rating: ${review.rating} stars",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )


            }
        }
    }
}

@Preview
@Composable
private fun ReviewItemPreview(){
    MaterialTheme() {
//        ReviewItem(review = Review(1,"id","aaaaaaaaaa aaaaaaaaaaasbsahja sdasd jasjkdkjasdja sdasjdjasdjkasdjasjdhas dhmnsabashj dhjasdjk ashjdkasjdjaksdaa","2023-09-09","Hapii Hapii",5,"2023-09-09","null"))

    }
}