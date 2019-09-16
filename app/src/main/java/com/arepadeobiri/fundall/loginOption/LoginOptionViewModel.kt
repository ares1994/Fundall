package com.arepadeobiri.fundall.loginOption

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import javax.inject.Inject

class LoginOptionViewModel(appComponent: AppComponent) : ViewModel() {


    @Inject
    lateinit var pref: SharedPreferences


    init {
        appComponent.inject(this)
    }

}