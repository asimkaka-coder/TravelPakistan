package com.tp.travelpakistan.ui.auth.data.models

sealed class AuthResponse(val message:String?=null){
    object Success:AuthResponse()
    class Failed(message: String):AuthResponse(message=message)
}
