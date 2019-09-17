package com.arepadeobiri.fundall.splash

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import javax.inject.Inject

class SplashViewModel(appComponent: AppComponent) : ViewModel() {

    @Inject
    lateinit var pref: SharedPreferences


    init {
        appComponent.inject(this)
    }


}