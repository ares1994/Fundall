package com.arepadeobiri.fundall.daggerUtil

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.arepadeobiri.fundall.BuildConfig
import com.arepadeobiri.fundall.FundallApplication
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.loginDataModels.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: FundallApplication) {


    @Provides
    @Singleton
    fun getConnectivityManager(): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun getSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences("fundall", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun getPicassoInstance(): Picasso {
        return Picasso.get()
    }

    @Provides
    fun provideAuthRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://test.fundall.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    fun provideAuthClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authenticationInterceptor: AuthenticationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingLevel = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        return HttpLoggingInterceptor().setLevel(loggingLevel)
    }


    @Provides
    fun provideAuthenticationInterceptor(
        sharedPreferences: SharedPreferences
    ): AuthenticationInterceptor {
        return AuthenticationInterceptor(sharedPreferences)
    }


    @Provides
    @Singleton
    fun provideFundall(retrofit: Retrofit): Fundall{
        return retrofit.create(Fundall::class.java)
    }

}