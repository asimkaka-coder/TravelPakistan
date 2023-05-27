package com.tp.travelpakistan.ui.organizer.data.models

data class Organizer(
    val __v: Int,
    val _id: String,
    val address: String,
    val certificates: List<String>,
    val coverImage: String,
    val createdAt: String,
    val description: String,
    val displayPicture: String,
    val email: String,
    val images: List<String>,
    val name: String,
    val owner: String,
    val password: String,
    val phone: String,
    val specialities: List<String>,
    val tagline: String,
    val tripsCompleted: Int,
    val updatedAt: String,
    val verified: Boolean
)