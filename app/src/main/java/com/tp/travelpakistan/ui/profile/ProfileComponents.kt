package com.tp.travelpakistan.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.tp.travelpakistan.R
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.auth.data.models.UserX
import java.security.MessageDigest


@Composable
fun ProfileSection(
    user: UserX?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
           GlideImage(
                imageModel = { user?.image },
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
               imageOptions = ImageOptions(
                   contentScale = ContentScale.Crop,
                   alignment = Alignment.Center
               )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = user?.username ?: "User Name",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                ProfileUserSection(iconResource = R.drawable.ic_location, label = user?.address?: "Google, Pakistan")
                ProfileUserSection(iconResource = R.drawable.ic_calendar, label = user?.createdAt?: "Joined on 2002")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            onClick = {},
        ) {
            Text("Edit Profile", style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.primary))
        }
    }
}

@Composable
fun ProfileUserSection(
    iconResource: Int,
    label: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(iconResource),
            modifier = Modifier.size(14.dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
        )

        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

    }
}


@Preview
@Composable
fun ProfileSectionPreview() {
    ProfileSection(null)
}