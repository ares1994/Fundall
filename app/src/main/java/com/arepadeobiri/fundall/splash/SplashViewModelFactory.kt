package com.arepadeobiri.fundall.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arepadeobiri.fundall.daggerUtil.AppComponent

class SplashViewModelFactory(private val appComponent: AppComponent) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(appComponent) as T
        }
        throw IllegalArgumentException("Unknown viewModel Class")
    }
}