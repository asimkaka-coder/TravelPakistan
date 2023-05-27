package com.tp.travelpakistan.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.tp.travelpakistan.ui.details.model.DetailImageItem
import com.tp.travelpakistan.ui.details.model.images

@Composable
fun DetailImageSlider(
    images: List<DetailImageItem>,
    onImageSelect: (DetailImageItem) -> Unit={}
) {
    val currentSelectedImage = remember {
        mutableStateOf(images.first{it.isSelected})
    }

    val requestManager: RequestManager = LocalContext.current.let { remember(it) { Glide.with(it) } }

    Column(
        modifier = Modifier
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(Color.Black, RoundedCornerShape(28.dp))
        ) {
            GlideImage(
                imageModel = { currentSelectedImage.value.contentUri },
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(28.dp)),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(images) {selectedImage->
                Box(modifier = Modifier
                    .width(76.dp)
                    .height(76.dp)) {
                    GlideImage(
                        imageModel = { selectedImage.contentUri },
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(14.dp))
                            .clickable {
                                currentSelectedImage.value = selectedImage
//                                onImageSelect(it)
                            },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DetailImageSliderPreview() {
    DetailImageSlider(images, { })
}
