package com.arepadeobiri.fundall.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arepadeobiri.fundall.daggerUtil.AppComponent

class SignUpViewModelFactory(private val appComponent: AppComponent) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(appComponent) as T
        }
        throw IllegalArgumentException("Unknown viewModel Class")
    }
}