package com.tp.travelpakistan.ui.details.model

import java.util.UUID

data class DetailImageItem(
    val id:String = UUID.randomUUID().toString(),
    val contentUri:String,
    var isSelected:Boolean = false
)


val images = listOf(
    DetailImageItem(
        contentUri = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/d9/3d/64/kumrat-urdu-is-a-valley.jpg?w=500&h=-1&s=1",
        isSelected = true
    ),
    DetailImageItem(
        contentUri = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/d9/3d/64/kumrat-urdu-is-a-valley.jpg?w=500&h=-1&s=1",
        isSelected = false
    ),
    DetailImageItem(
        contentUri = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/d9/3d/64/kumrat-urdu-is-a-valley.jpg?w=500&h=-1&s=1",
        isSelected = false
    ),
    DetailImageItem(
        contentUri = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/d9/3d/64/kumrat-urdu-is-a-valley.jpg?w=500&h=-1&s=1",
        isSelected = false
    ),
    DetailImageItem(
        contentUri = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/d9/3d/64/kumrat-urdu-is-a-valley.jpg?w=500&h=-1&s=1",
        isSelected = false
    ),
    DetailImageItem(
        contentUri = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/d9/3d/64/kumrat-urdu-is-a-valley.jpg?w=500&h=-1&s=1",
        isSelected = false
    ),
    DetailImageItem(
        contentUri = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/d9/3d/64/kumrat-urdu-is-a-valley.jpg?w=500&h=-1&s=1",
        isSelected = false
    )
)
