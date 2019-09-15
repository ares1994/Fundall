package com.arepadeobiri.fundall.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.signUp.SignUpViewModel

class LoginViewModelFactory(private val appComponent: AppComponent) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(appComponent) as T
        }
        throw IllegalArgumentException("Unknown viewModel Class")
    }
}