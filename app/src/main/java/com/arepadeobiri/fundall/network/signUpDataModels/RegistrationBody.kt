package com.arepadeobiri.fundall.network.signUpDataModels

data class RegistrationBody(

    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val password_confirmation: String

)