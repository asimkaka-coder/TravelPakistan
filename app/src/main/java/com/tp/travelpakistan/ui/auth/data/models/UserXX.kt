package com.tp.travelpakistan.ui.auth.data.models

data class UserXX(
    val __v: Int,
    val _id: String,
    val address: String,
    val cnic: String,
    val createdAt: String,
    val email: String,
    val image: String,
    val password: String,
    val phone: String,
    val updatedAt: String,
    val username: String
){
    fun toUserX() = UserX(
        __v = __v,
        _id = _id,
        address = address,
        cnic = cnic,
        createdAt=createdAt,
        email=email,
        image = image,
        updatedAt=updatedAt,
        phone = phone,
        username = username,
        role = "User"
    )
}