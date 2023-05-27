package com.tp.travelpakistan.ui.organizer.data.models

import com.tp.travelpakistan.ui.auth.data.models.UserX

data class Review(
    val __v: Int,
    val _id: String,
    val comment: String,
    val createdAt: String,
    val organizer: String,
    val rating: Int,
    val updatedAt: String,
    val user: UserX
)