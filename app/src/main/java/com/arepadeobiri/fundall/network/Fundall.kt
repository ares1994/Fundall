package com.arepadeobiri.fundall.network

import com.arepadeobiri.fundall.network.loginDataModels.LoginResponse
import com.arepadeobiri.fundall.network.signUpDataModels.Response
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Fundall {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Authorization: Bearer 'API_TOKEN'"
    )

    @FormUrlEncoded
    @POST("/api/v1/register")
    fun registerUserAsync(
        @Field("firstname") firstName: String,
        @Field("lastname") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String
    ): Deferred<Response>


    @FormUrlEncoded
    @POST("/api/v1/login")
    fun logInAsync(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Deferred<LoginResponse>


}


/**
 * Main entry point for network access. Call like `Network.fundallIO.registerUserAsync`
 */
object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://test.fundall.io")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val fundallIO: Fundall = retrofit.create(Fundall::class.java)
}