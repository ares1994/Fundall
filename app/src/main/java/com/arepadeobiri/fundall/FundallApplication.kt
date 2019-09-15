package com.arepadeobiri.fundall

import android.app.Application
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.daggerUtil.AppModule
import com.arepadeobiri.fundall.daggerUtil.DaggerAppComponent


class FundallApplication : Application() {


    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()


        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()


    }


    fun getAppComponent(): AppComponent {
        return appComponent
    }
}