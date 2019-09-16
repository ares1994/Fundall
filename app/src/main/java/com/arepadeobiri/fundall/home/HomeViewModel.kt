package com.arepadeobiri.fundall.home

import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent

class HomeViewModel(private val appComponent: AppComponent) : ViewModel(){



    init {

        appComponent.inject(this)
    }
}