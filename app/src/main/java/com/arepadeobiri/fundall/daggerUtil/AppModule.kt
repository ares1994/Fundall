package com.arepadeobiri.fundall.daggerUtil

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.arepadeobiri.fundall.FundallApplication
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.Network.fundallIO
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: FundallApplication) {

    @Provides
    @Singleton
    fun networkInterface(): Fundall {
        return fundallIO
    }

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

}