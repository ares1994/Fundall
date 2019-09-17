package com.arepadeobiri.fundall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arepadeobiri.fundall.login.LoginViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.home.HomeViewModel
import com.arepadeobiri.fundall.loginOption.LoginOptionViewModel
import com.arepadeobiri.fundall.signUp.SignUpViewModel
import com.arepadeobiri.fundall.splash.SplashViewModel

class GenericViewModelFactory(private val appComponent: AppComponent) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(appComponent) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(appComponent) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(appComponent) as T
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(appComponent) as T
            modelClass.isAssignableFrom(LoginOptionViewModel::class.java)-> LoginOptionViewModel(appComponent) as T
            else -> throw IllegalArgumentException("Unknown viewModel Class")
        }
    }
}