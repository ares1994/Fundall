package com.arepadeobiri.fundall.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.database.User
import com.arepadeobiri.fundall.database.UserDao
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import kotlinx.coroutines.*
import javax.inject.Inject

class SplashViewModel(appComponent: AppComponent) : ViewModel() {

    @Inject
    lateinit var database: UserDao

    private val job = Job()


    private val scope = CoroutineScope(Dispatchers.Main + job)


    init {
        appComponent.inject(this)
    }

    val currentUser = database.getAll()




    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}