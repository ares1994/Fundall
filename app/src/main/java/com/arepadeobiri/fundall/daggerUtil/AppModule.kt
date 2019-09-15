package com.arepadeobiri.fundall.daggerUtil

import android.content.Context
import android.net.ConnectivityManager
import com.arepadeobiri.fundall.database.AppDatabase
import com.arepadeobiri.fundall.database.UserDao
import com.arepadeobiri.fundall.FundallApplication
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.Network.fundallIO
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
    fun getUserDatabase(): UserDao {
        return AppDatabase.getInstance(application).userDao
    }


}