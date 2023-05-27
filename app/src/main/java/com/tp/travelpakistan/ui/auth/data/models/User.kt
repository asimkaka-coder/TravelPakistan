package com.tp.travelpakistan.ui.auth.data.models

data class User(
    val name:String,
    val emailAddress:String,
    val password:String
){
    fun isValidUser():Boolean{
        return this.name.isNotEmpty() && this.emailAddress.isNotEmpty() && this.password.isNotEmpty()
    }

    fun toSignInRequestBody():SignInRequestBody{
        return SignInRequestBody(
            username = emailAddress,
            password = password
        )
    }

    fun toSignUpRequestBody():SignUpRequestBody{
        return SignUpRequestBody(
            username = emailAddress,
            email = "$emailAddress.@email.com",
            password = password,
            address = "Address",
            phone = "0300000000",
            cnic = "XXXXXXXXXXXXXX",
            image = "imagetest"
        )
    }
}


