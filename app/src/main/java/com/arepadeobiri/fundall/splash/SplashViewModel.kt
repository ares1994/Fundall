package com.arepadeobiri.fundall.splash

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import kotlinx.coroutines.*
import javax.inject.Inject

class SplashViewModel(appComponent: AppComponent) : ViewModel() {

    @Inject lateinit var pref : SharedPreferences

    private val job = Job()


    private val scope = CoroutineScope(Dispatchers.Main + job)


    init {
        appComponent.inject(this)
    }







    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}